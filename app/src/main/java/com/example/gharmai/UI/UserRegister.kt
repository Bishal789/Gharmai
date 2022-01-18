package com.example.gharmai.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.gharmai.R
import com.example.gharmai.entity.UserEntity
import com.example.gharmai.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRegister : AppCompatActivity() {

    private lateinit var GenderSpinner:Spinner
    private lateinit var signUpUser: Button
    private lateinit var username: EditText
    private lateinit var userPassword: EditText
    private lateinit var userEmail: EditText
    private lateinit var userAddress: EditText
    private lateinit var userPhone: EditText

    val genderType = arrayOf("Male","Female","Other")
    var gender : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)

        GenderSpinner = findViewById(R.id.genderSpinner)
        userAddress = findViewById(R.id.userAddress)
        signUpUser = findViewById(R.id.userSignUp)
        username = findViewById(R.id.username)
        userPassword = findViewById(R.id.userPassword)
        userEmail = findViewById(R.id.userEmail)
        userPhone = findViewById(R.id.userPhone)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, genderType)
        GenderSpinner.adapter = adapter
        GenderSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    gender= parent?.getItemAtPosition(position).toString()
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    TODO("Not yet implemented")
                }
            }
        signUpUser.setOnClickListener {
            userRegister()
            userAddress.setText("")
            username.setText("")
            userPassword.setText("")
            userPhone.setText("")
            userEmail.setText("")
        }
    }
    private fun userRegister(){
        val username = username.text.toString()
        val address = userAddress.text.toString()
        val contact = userPhone.text.toString()
        val email = userEmail.text.toString()
        val password = userPassword.text.toString()

        if (isValid()){
            val user = UserEntity(
                username = username,
                addressUser = address,
                phoneUser = contact,
                emailUser = email,
                passwordUser = password,
                genderUser = gender)

            CoroutineScope(Dispatchers.IO).launch {
                //StudentDB(this@UserRegister).getUserDAO().registerUser(user)
                try{
                    val repository = UserRepository()
                    val response = repository.registerUser(user)
                    if (response.success ==true){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@UserRegister, "Registered", Toast.LENGTH_SHORT).show()
                        }
                        startActivity(Intent(this@UserRegister, LoginActivity::class.java))
                    }
                }catch (ex:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@UserRegister, ex.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        else{
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
        }
    }
    private fun isValid():Boolean{
        when{
            username.text.isEmpty() -> {
                username.error="Name cannot be Empty"
                username.requestFocus()
                return false
            }
            userEmail.text.isEmpty() -> {
                userEmail.error="Email cannot be Empty"
                userEmail.requestFocus()
                return false
            }
            userPassword.text.isEmpty() -> {
                userPassword.error="Password cannot be Empty"
                userPassword.requestFocus()
                return false
            }
            userAddress.text.isEmpty() -> {
                userAddress.error="Address cannot be Empty"
                userAddress.requestFocus()
                return false
            }
            userPhone.text.isEmpty() -> {
                userPhone.error="Contact cannot be Empty"
                userPhone.requestFocus()
                return false
            }
        }
        return true
    }
}