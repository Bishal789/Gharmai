package com.example.gharmai.AdminUI

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import com.example.gharmai.R

import com.example.gharmai.entity.ServiceEntity

import com.example.gharmai.repository.ServiceRepository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class add_services : AppCompatActivity() {

    private lateinit var addServiceName: EditText
    private lateinit var addServicePrice: EditText
    private lateinit var addServiceAbout: EditText
    private lateinit var addServiceButton: Button
    private lateinit var ServiceSpinner: Spinner
    private lateinit var serviceImage: ImageView

    val ServiceType = arrayOf("Salon for Women","Salon fro Men","Plumber","Electrician","Cleaning and Disinfection","Carpenter","Men Therapy","Women Therapy")
    var service : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_services)

        addServiceAbout = findViewById(R.id.ADMIN_ETABOUT_SERVICE)
        addServiceName = findViewById(R.id.ADMIN_ETSERVICENAME)
        addServicePrice = findViewById(R.id.ADMIN_ETSERVICE_PRICE)
        addServiceButton = findViewById(R.id.ADMIN_ADD_SERVICE)
        serviceImage = findViewById(R.id.ADMIN_SERVICE_IMAGE)
        ServiceSpinner = findViewById(R.id.ServiceSpinner)


        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ServiceType)
        ServiceSpinner.adapter = adapter
        ServiceSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    service= parent?.getItemAtPosition(position).toString()
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
//                    TODO("Not yet implemented")
                }
            }


        if (!CheckRuntimePermission()) {
            Askpermission()
        }

        serviceImage.setOnClickListener {
            loadpopupmenu()
        }



        addServiceButton.setOnClickListener {
            serviceAdd()
//            Toast.makeText(this, "Service Added", Toast.LENGTH_SHORT).show()
        }
    }
    private fun serviceAdd(){
        val serviceName = addServiceName.text.toString()
        val servicePrice = addServicePrice.text.toString()
        val serviceAbout = addServiceAbout.text.toString()


        if (isValid()){
            val user = ServiceEntity(
                serviceName = serviceName,
                servicePrice = servicePrice,
                serviceDetails = serviceAbout,
                serviceCategory = service
                )
            CoroutineScope(Dispatchers.IO).launch {
                //StudentDB(this@UserRegister).getUserDAO().registerUser(user)
                try{
                    val serviceRepository = ServiceRepository()
                    val serviceResponse = serviceRepository.registerUser(user)

                    if (serviceResponse.success ==true){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@add_services, "Service added", Toast.LENGTH_SHORT).show()
                        }
//                        startActivity(Intent(this@UserRegister, LoginActivity::class.java))
                    }
                }catch (ex:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@add_services, ex.toString(), Toast.LENGTH_SHORT).show()
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
            addServiceName.text.isEmpty() -> {
                addServiceName.error="Service Name cannot be Empty"
                addServiceName.requestFocus()
                return false
            }
            addServicePrice.text.isEmpty() -> {
                addServicePrice.error="Service Price cannot be Empty"
                addServicePrice.requestFocus()
                return false
            }
            addServiceAbout.text.isEmpty() -> {
                addServiceAbout.error="Please tell about service"
                addServiceAbout.requestFocus()
                return false
            }
        }
        return true
    }



    private val permission = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )

    private fun CheckRuntimePermission(): Boolean {
        var haspermission = true
        for (permission in permission) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                haspermission = false
                break
            }
        }
        return haspermission
    }
    private fun Askpermission() {
        ActivityCompat.requestPermissions(this@add_services, permission, 1)


    }

    private fun loadpopupmenu() {
        val popMenu = PopupMenu(this, serviceImage)
        popMenu.menuInflater.inflate(R.menu.camerapopup, popMenu.menu)
        popMenu.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.CameraOpen) {
                openCamera()
//                Toast.makeText(this, "Camera clicked", Toast.LENGTH_SHORT)
//                    .show()
            } else if (item.itemId == R.id.GalleryOpen) {
                openGallery()
//                Toast.makeText(this, "Gallery clicked", Toast.LENGTH_SHORT)
//                    .show()
            }
            true
        }
        popMenu.show()
    }

    private val CAMER_COE = 1
    private val GALLERY_CODE = 0
//    private val MUSIC_CODE = 2

    private fun openGallery() {
        val galleryOpen = Intent(Intent.ACTION_PICK)
        galleryOpen.type = "image/*"
        startActivityForResult(galleryOpen, GALLERY_CODE)

    }

    private fun openCamera() {

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMER_COE)

    }



    private var imageUrl = ""
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //Gallery ko image Image view ma dekhauni
        if(resultCode == Activity.RESULT_OK){
            if (requestCode == GALLERY_CODE && data!=null){
                val selectedImage = data.data
                val filepathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = contentResolver
                val cursor = contentResolver.query(selectedImage!!,filepathColumn,null,null,null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filepathColumn[0])
                imageUrl = cursor.getString(columnIndex)
                serviceImage.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
                cursor.close()
            }
            else if (requestCode == CAMER_COE && data!= null){
                val imageBitmap = data.extras?.get("data")as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapTofile(imageBitmap, "$timeStamp.png")
                imageUrl = file!!.absolutePath
                serviceImage.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
            }
        }
    }
    private fun bitmapTofile(
        bitmap: Bitmap,
        fileNametoSave: String
    ): File?{
        var file: File?=null
        return try {
            file = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + File.separator + fileNametoSave
            )
            file.createNewFile()
            //Convert bitmap to byte Array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,100,bos)
            val bitMapData = bos.toByteArray()
            //write the byte in file
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file // it will return null
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            file
        }
    }
}