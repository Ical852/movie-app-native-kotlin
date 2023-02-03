package com.egp.bwamov.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.egp.bwamov.R
import com.egp.bwamov.sign.signin.User
import com.google.firebase.database.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var btnLanjutRegis:Button

    private lateinit var etUsername : EditText
    private lateinit var etPassword : EditText
    private lateinit var etName : EditText
    private lateinit var etEmail : EditText

    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mFirebaseInstance : FirebaseDatabase
    private lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance("https://bwa-mov-ebc05-default-rtdb.firebaseio.com/")
        mDatabaseReference = mFirebaseInstance.getReference("User")

        btnLanjutRegis = findViewById(R.id.btn_lanjut_regis)
        btnLanjutRegis.setOnClickListener {
            etUsername = findViewById(R.id.et_username_regis)
            etPassword = findViewById(R.id.et_password_regis)
            etName = findViewById(R.id.et_name_regis)
            etEmail = findViewById(R.id.et_email_regis)

            var username = etUsername.text.toString()
            var password = etPassword.text.toString()
            var name = etName.text.toString()
            var email = etEmail.text.toString()

            if (username.equals("")) {
                etUsername.error = "Isi username Terlebih Dahulu"
                etUsername.requestFocus()
            } else if (password.equals("")) {
                etPassword.error = "Isi Password Terlebih Dahulu"
                etPassword.requestFocus()
            } else if (name.equals("")) {
                etName.error = "Isi Nama Terlebih Dahulu"
                etName.requestFocus()
            } else if (email.equals("")) {
                etEmail.error = "Isi Email Terlebih Dahulu"
                etEmail.requestFocus()
            } else {
                saveUsername(username, password, name, email)
            }
        }
    }

    private fun saveUsername(username: String, password: String, name: String, email: String) {
        var user = User()
        user.username = username
        user.password = password
        user.nama = name
        user.email = email

        if (username != null) {
            checkingUsername(username, user)
        }
    }

    private fun checkingUsername(username: String, data: User) {
        mDatabaseReference.child(username).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    mDatabaseReference.child(username).setValue(data)

                    var goSignUpPhotoscreen = Intent(this@SignUpActivity, SignUpPhotoscreenActivity::class.java).putExtra("nama", data.nama)
                    startActivity(goSignUpPhotoscreen)
                } else {
                    Toast.makeText(this@SignUpActivity, "Username sudah ada",
                            Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity, ""+databaseError.message,
                        Toast.LENGTH_LONG).show()
            }
        })
    }
}