package com.egp.bwamov.sign.signup

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.egp.bwamov.home.HomeActivity
import com.egp.bwamov.R
import com.egp.bwamov.util.Preferences
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener
import java.util.*

class SignUpPhotoscreenActivity : AppCompatActivity() /*, PermissionListener*/ {
    private lateinit var tvHello : TextView
    private lateinit var ivAdd : ImageView
    private lateinit var ivProfile : ImageView
    private lateinit var btnSave : Button
    private lateinit var btnUploadNanti : Button

    var statusAdd:Boolean = false
    lateinit var filePath:Uri

    lateinit var storage:FirebaseStorage
    lateinit var storageReference:StorageReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photoscreen)

//        preferences = Preferences(this)
//        storage = FirebaseStorage.getInstance("https://bwa-mov-ebc05-default-rtdb.firebaseio.com/")
//        storageReference = storage.getReference("gs://bwa-mov-ebc05.appspot.com")
//
//        tvHello = findViewById(R.id.tv_hello)
//        tvHello.text = "Selamat Datang \n"+intent.getStringExtra("nama")
//
//        btnSave = findViewById(R.id.btn_save_photo)
//        ivProfile = findViewById(R.id.iv_profile)
//
//        ivAdd = findViewById(R.id.iv_add)
//        ivAdd.setOnClickListener {
//            if (statusAdd) {
//                statusAdd = false
//
//                btnSave.visibility = View.VISIBLE
//
//                ivAdd.setImageResource(R.drawable.ic_add)
//
//                ivProfile.setImageResource(R.drawable.user_pic)
//            } else {
//                ImagePicker.with(this)
//                    .cameraOnly()
//                    .start()
//            }
//        }

        btnUploadNanti = findViewById(R.id.btn_upload_nanti)
        btnUploadNanti.setOnClickListener {
            finishAffinity()
            var goHome = Intent(this@SignUpPhotoscreenActivity, HomeActivity::class.java)
            startActivity(goHome)
        }

//        btnSave.setOnClickListener {
//            if (filePath != null) {
//                var progressDialog = ProgressDialog(this)
//                progressDialog.setTitle("Uploading...")
//                progressDialog.show()
//
//                var ref = storageReference.child("images/"+UUID.randomUUID().toString())
//                ref.putFile(filePath)
//                    .addOnSuccessListener {
//                        progressDialog.dismiss()
//                        Toast.makeText(this, "Berhasil Upload Foto", Toast.LENGTH_SHORT).show()
//
//                        ref.downloadUrl.addOnSuccessListener {
//                            preferences.setValues("url", it.toString())
//                        }
//
//                        finishAffinity()
//                        var goHome = Intent(this@SignUpPhotoscreenActivity, HomeActivity::class.java)
//                        startActivity(goHome)
//                    }
//                    .addOnFailureListener {
//                        progressDialog.dismiss()
//                        Toast.makeText(this, "Gagal Upload Foto", Toast.LENGTH_SHORT).show()
//                    }
//                    .addOnProgressListener {
//                        taskSnapshot -> var progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
//                        progressDialog.setMessage("Upload Foto " + progress.toInt()+" %")
//                    }
//            } else {
//
//            }
//        }
    }
//
//    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
//        ImagePicker.with(this)
//            .cameraOnly()
//            .start()
//    }
//
//    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
//        Toast.makeText(this, "Tidak dapat menambahkan foto profile", Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onPermissionRationaleShouldBeShown(
//        permission: PermissionRequest?,
//        token: PermissionToken?
//    ) {
//
//    }
//
//    override fun onBackPressed() {
//        Toast.makeText(this, "Tergesah? klik tombol upload nanti aja", Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == Activity.RESULT_OK){
//            statusAdd = true
//            filePath = data?.data!!
//            Glide.with(this)
//                .load(filePath)
//                .apply(RequestOptions.circleCropTransform())
//                .into(ivProfile)
//
//            btnSave.visibility = View.VISIBLE
//            ivAdd.setImageResource(R.drawable.ic_btn_delete)
//        } else if(resultCode == ImagePicker.RESULT_ERROR) {
//            Toast.makeText(this,ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
//        }
//    }

}