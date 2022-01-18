package com.example.gharmai.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.gharmai.AdminUI.admin_splashscreen
import com.example.gharmai.R
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.entity.UserEntity
import com.example.gharmai.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var DonthaveAccount: TextView
    private lateinit var loginButton: Button
    private lateinit var emailLogin: EditText
    private lateinit var passwordLogin: EditText
    private lateinit var linearLayout: ConstraintLayout
    private lateinit var loginLinear: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        DonthaveAccount = findViewById(R.id.dnthave)
        loginButton = findViewById(R.id.button2)
        emailLogin = findViewById(R.id.emailLogin)
        passwordLogin = findViewById(R.id.passwordLogin)
        linearLayout = findViewById(R.id.linearLayout)
        loginLinear = findViewById(R.id.linearLayout5)


        DonthaveAccount.setOnClickListener {
            startActivity(Intent(this@LoginActivity, UserRegister::class.java))
        }
        loginButton.setOnClickListener {
            login()
            emailLogin.setText("")
            passwordLogin.setText("")
        }

//        loginLinear.setAlpha(0.5f)
    }

    private fun login() {

        val email = emailLogin.text.toString()
        val password = passwordLogin.text.toString()

        if (email == "admin" && password == "admin") {
            startActivity(Intent(this@LoginActivity, admin_splashscreen::class.java))
        } else {

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val repository = UserRepository()
                    val response = repository.login(email, password)
                    if (response.success == true) {
                        // Save token

                        ServiceBuilder.token = "Bearer ${response.token}"
                        ServiceBuilder.userId = response.userId
                        //Save username and password in shared preferences
                        // saveUsernamePassword()

                        startActivity(
                            Intent(
                                this@LoginActivity, Dashboard::class.java
                            )
                        )
//                    Toast.makeText(this@LoginActivity, "Login Success" , Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        withContext(Dispatchers.Main) {
                            val snack =
                                Snackbar.make(
                                    linearLayout,
                                    "Invalid credentials",
                                    Snackbar.LENGTH_LONG
                                )
                            snack.setAction("OK", View.OnClickListener {
                                snack.dismiss()
                            })
                            snack.show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.d("grshgi", ex.toString())

                        Toast.makeText(
                            this@LoginActivity,
                            ex.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}