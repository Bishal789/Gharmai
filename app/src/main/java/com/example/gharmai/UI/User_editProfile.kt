package com.example.gharmai.UI

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.gharmai.Fragment.ProfileFragment
import com.example.gharmai.R
import com.example.gharmai.api.ServiceBuilder
import com.example.gharmai.entity.UserEntity
import com.example.gharmai.repository.UserRepository
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody


class User_editProfile : AppCompatActivity() {

    private lateinit var btnUpdate: ImageView
    private lateinit var cameraPopup: ImageView
    private lateinit var upImage: ImageView
    private lateinit var eetUsername: EditText
    private lateinit var eetAddress: EditText
    private lateinit var eetPhone: EditText
    private lateinit var eetEmail: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_edit_profile)

        if (!CheckRuntimePermission()) {
            Askpermission()
        }

        btnUpdate = findViewById(R.id.btnUpdate)
        cameraPopup = findViewById(R.id.upImage)
        upImage = findViewById(R.id.upImage)
        eetUsername = findViewById(R.id.eetUsername)
        eetAddress = findViewById(R.id.eetAddress)
        eetPhone = findViewById(R.id.eetPhone)
        eetEmail = findViewById(R.id.eetEmail)

        cameraPopup.setOnClickListener {
            loadpopupCameramenu()
        }


        btnUpdate.setOnClickListener {
            updateUser()
//            Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show()
        }
        CoroutineScope(Dispatchers.IO).launch {

            try{
                val repository = UserRepository()
                val response = repository.getCurrentUserAPI(ServiceBuilder.userId!!)


                if(response.success==true){

                    withContext(Dispatchers.Main) {
                        eetUsername.setText(response.data?.username)
                        eetEmail.setText(response.data?.emailUser)
                        eetAddress.setText(response.data?.addressUser)
                        eetPhone.setText(response.data?.phoneUser)


                    }
                }
            }catch(ex : Exception){
                withContext(Dispatchers.Main){
                    Log.d("error", ex.printStackTrace().toString())
                    Toast.makeText(this@User_editProfile, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    // --------------------------- Updating user profile --------------------------


    // -------------- end of updating user update -----------------------

    private fun loadpopupCameramenu() {
        val popMenu = PopupMenu(this, cameraPopup)
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
        ActivityCompat.requestPermissions(this@User_editProfile, permission, 1)


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
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY_CODE && data != null) {
                val selectedImage = data.data
                val filepathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = contentResolver
                val cursor =
                    contentResolver.query(selectedImage!!, filepathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filepathColumn[0])
                imageUrl = cursor.getString(columnIndex)
                cameraPopup.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
                cursor.close()
            } else if (requestCode == CAMER_COE && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapTofile(imageBitmap, "$timeStamp.png")
                imageUrl = file!!.absolutePath
                cameraPopup.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
            }
        }
    }

    private fun bitmapTofile(
        bitmap: Bitmap,
        fileNametoSave: String
    ): File? {
        var file: File? = null
        return try {
            file = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + File.separator + fileNametoSave
            )
            file.createNewFile()
            //Convert bitmap to byte Array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
            val bitMapData = bos.toByteArray()
            //write the byte in file
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file // it will return null
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file
        }
    }


    private fun updateUser() {
        val Username = eetUsername.text.toString()
        val Email = eetEmail.text.toString()
        val Address = eetAddress.text.toString()
        val Phone = eetPhone.text.toString()

        val data = UserEntity(
            username = Username, emailUser = Email, addressUser = Address, phoneUser = Phone
        )

        Log.d("dataaaaa",data.toString())

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userrepo = UserRepository()
                val userres = userrepo.updateuser(ServiceBuilder.userId!!, data)

                if (userres.success == true) {


                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@User_editProfile, "User Updated", Toast.LENGTH_SHORT)
                            .show()
                    }
                    Updateimage(ServiceBuilder.userId!!)

                    startActivity(Intent(this@User_editProfile, Dashboard::class.java))
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@User_editProfile, ex.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun Updateimage(userId: String) {
        val file = File(imageUrl)
        Log.d("phoarwto", file.toString())
        val mimeType = getMimeType(file)
        val reqFile = RequestBody.create(MediaType.parse(mimeType), file)
        val body = MultipartBody.Part.createFormData("file", file.name, reqFile)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userrepo = UserRepository()
                Log.d("Userrerpo", userrepo.toString())
                val response = userrepo.updateimage(userId, body)
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@User_editProfile,
                            "Update successful",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Log.d("Mero Error ", ex.localizedMessage)
                    Toast.makeText(
                        this@User_editProfile,
                        ex.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun getMimeType(file: File): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(file.path)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }
}