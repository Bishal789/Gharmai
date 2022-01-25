package com.example.gharmai.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.gharmai.AdminUI.admin_splashscreen
import com.example.gharmai.R
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.repository.UserRepository
import com.example.gharmai.repository.WorkerRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class worker_login : AppCompatActivity() {

    private lateinit var register: TextView
    private lateinit var workerLoginEmail: TextView
    private lateinit var workerLoginPassword: TextView
    private lateinit var workerLoginButton: Button
    private lateinit var linearLayout: ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_login)

        register = findViewById(R.id.dnthave_worker)
        workerLoginEmail = findViewById(R.id.emailLogin_worker)
        workerLoginPassword = findViewById(R.id.passwordLogin_worker)
        workerLoginButton = findViewById(R.id.worker_login)
        linearLayout = findViewById(R.id.linearLayoutWorker)

        workerLoginButton.setOnClickListener {
            workerLogin()
        }

        register.setOnClickListener {
            startActivity(Intent(this@worker_login,worker_register::class.java))
        }
    }

    private fun workerLogin() {
        val email = workerLoginEmail.text.toString()
        val password = workerLoginPassword.text.toString()

        if (email == "admin" && password == "admin") {
            startActivity(Intent(this@worker_login, admin_splashscreen::class.java))
        }
        else{

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val repository = WorkerRepository()
                    val response = repository.loginWorker(email, password)
                    if (response.success == true) {
                        // Save token

                        ServiceBuilder.token = "Bearer ${response.token}"
                        ServiceBuilder.userId = response.userId
                        //Save username and password in shared preferences
                        // saveUsernamePassword()

                        startActivity(
                            Intent(
                                this@worker_login, Dashboard::class.java
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
                            this@worker_login,
                            ex.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}