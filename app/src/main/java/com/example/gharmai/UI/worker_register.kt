package com.example.gharmai.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.gharmai.R
import com.example.gharmai.entity.WorkerEntity
import com.example.gharmai.repository.WorkerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class worker_register : AppCompatActivity() {

    private lateinit var GenderSpinner: Spinner
    private lateinit var workerName: EditText
    private lateinit var workerEmail: EditText
    private lateinit var workerPassword: EditText
    private lateinit var workerConPassword: EditText
    private lateinit var workerAddress: EditText
    private lateinit var workerContact: EditText
    private lateinit var workerSignUp: Button


    val genderType = arrayOf("Male","Female","Other")
    var workerGender : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_worker_register)


        workerName = findViewById(R.id.workername)
        workerEmail = findViewById(R.id.workerEmail)
        workerPassword = findViewById(R.id.workerPassword)
        workerConPassword = findViewById(R.id.confirmPassword_worker)
        workerAddress = findViewById(R.id.workerAddress)
        workerContact = findViewById(R.id.workerPhone)
        workerSignUp = findViewById(R.id.workerSignUp)

        GenderSpinner = findViewById(R.id.genderSpinner_worker)

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
                    workerGender= parent?.getItemAtPosition(position).toString()
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    TODO("Not yet implemented")
                }
            }
        workerSignUp.setOnClickListener {
            workerRegister()
            Toast.makeText(this, "Next toast", Toast.LENGTH_SHORT).show()
        }

    }
    private fun workerRegister(){
        val username = workerName.text.toString()
        val address = workerAddress.text.toString()
        val contact = workerContact.text.toString()
        val email = workerEmail.text.toString()
        val password = workerPassword.text.toString()
        val conPassword = workerConPassword.text.toString()

        if (password != conPassword){
            Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show()
        }
        else if (isValid()){
            val worker = WorkerEntity(
                workerName = username,
                workerAddress = address,
                workerContactNo = contact,
                workerEmail = email,
                workerPassword = password,
                workerGender = workerGender
            )

            CoroutineScope(Dispatchers.IO).launch {
                //StudentDB(this@UserRegister).getUserDAO().registerUser(user)
                try{
                    val repository = WorkerRepository()
                    val response = repository.registerWorker(worker)
                    if (response.success ==true){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@worker_register, "Registered as Worker", Toast.LENGTH_SHORT).show()
                        }
                        startActivity(Intent(this@worker_register, worker_login::class.java))
                    }
                }catch (ex:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@worker_register, ex.toString(), Toast.LENGTH_SHORT).show()
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
            workerName.text.isEmpty() -> {
                workerName.error="Name cannot be Empty"
                workerName.requestFocus()
                return false
            }
            workerEmail.text.isEmpty() -> {
                workerEmail.error="Email cannot be Empty"
                workerEmail.requestFocus()
                return false
            }
            workerPassword.text.isEmpty() -> {
                workerPassword.error="Password cannot be Empty"
                workerPassword.requestFocus()
                return false
            }
            workerConPassword.text.isEmpty() -> {
                workerConPassword.error="Password cannot be Empty"
                workerConPassword.requestFocus()
                return false
            }
            workerAddress.text.isEmpty() -> {
                workerAddress.error="Address cannot be Empty"
                workerAddress.requestFocus()
                return false
            }
            workerContact.text.isEmpty() -> {
                workerContact.error="Contact cannot be Empty"
                workerContact.requestFocus()
                return false
            }
        }
        return true
    }
}