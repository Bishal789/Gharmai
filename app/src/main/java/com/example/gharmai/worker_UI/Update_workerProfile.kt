package com.example.gharmai.worker_UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.gharmai.R
import com.example.gharmai.UI.Dashboard
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.entity.UserEntity
import com.example.gharmai.entity.WorkerEntity
import com.example.gharmai.repository.UserRepository
import com.example.gharmai.repository.WorkerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Update_workerProfile : AppCompatActivity() {

    private lateinit var nameWorker: EditText
    private lateinit var emailWorker: EditText
    private lateinit var addressWorker: EditText
    private lateinit var phoneWorker: EditText
    private lateinit var workerUpdate: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_worker_profile)

        nameWorker = findViewById(R.id.eetWorkername)
        emailWorker = findViewById(R.id.eetEmail_worker)
        addressWorker = findViewById(R.id.eetAddress_worker)
        phoneWorker = findViewById(R.id.eetPhone_worker)
        workerUpdate = findViewById(R.id.btnUpdate_worker)


        workerUpdate.setOnClickListener {
            workerProfileUpdate()
            Toast.makeText(this, "Worker Update", Toast.LENGTH_SHORT).show()
        }
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val repository = WorkerRepository()
                val response = repository.getCurrentWorker(ServiceBuilder.userId!!)


                if (response.success == true) {

                    withContext(Dispatchers.Main) {
                        nameWorker.setText(response.data?.workerName)
                        emailWorker.setText(response.data?.workerEmail)
                        addressWorker.setText(response.data?.workerAddress)
                        phoneWorker.setText(response.data?.workerContactNo)

//                        val imageUrl = ServiceBuilder.BASE_URL + response.data?.profile_picUser
//                        this.let {
//                            Glide.with(this@User_editProfile)
//                                .asBitmap()
//                                .load(imageUrl)
//                                .into(cameraPopup)
//
//                        }


                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Log.d("error", ex.printStackTrace().toString())
                    Toast.makeText(this@Update_workerProfile, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun workerProfileUpdate() {
        val workerName = nameWorker.text.toString()
        val workerEmail = emailWorker.text.toString()
        val workerAddress = addressWorker.text.toString()
        val workerPhone = phoneWorker.text.toString()

        val data = WorkerEntity(
            workerName = workerName, workerEmail = workerEmail, workerAddress = workerAddress, workerContactNo = workerPhone
        )

        Log.d("dataaaaa", data.toString())

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val workerRepo = WorkerRepository()
                val workerRes = workerRepo.updateWorker(ServiceBuilder.userId!!, data)

                if (workerRes.success == true) {


                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@Update_workerProfile, "Worker Updated", Toast.LENGTH_SHORT)
                            .show()
                    }
//                    Updateimage(ServiceBuilder.userId!!)

                    startActivity(Intent(this@Update_workerProfile, Update_workerProfile::class.java))
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@Update_workerProfile, ex.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}