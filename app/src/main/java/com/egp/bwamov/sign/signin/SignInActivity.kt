package com.egp.bwamov.sign.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.egp.bwamov.home.HomeActivity
import com.egp.bwamov.R
import com.egp.bwamov.sign.signup.SignUpActivity
import com.egp.bwamov.util.Preferences
import com.google.firebase.database.*


class SignInActivity : AppCompatActivity() {
    private lateinit var btnLogin : Button
    private lateinit var btnRegis : Button

    private lateinit var etUser : EditText
    private lateinit var etPassword : EditText

    private lateinit var mDatabase : DatabaseReference
    private lateinit var preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        preference = Preferences(this)

        preference.setValues("onboarding", "1")
        if (preference.getValues("status").equals("1")) {
            finishAffinity()
            var intent = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(intent)
        }

        mDatabase = FirebaseDatabase
                .getInstance("https://bwa-mov-ebc05-default-rtdb.firebaseio.com/")
                .getReference("User")

        btnRegis = findViewById(R.id.btn_regis)
        btnRegis.setOnClickListener {
            var intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        btnLogin = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener {
            etUser = findViewById(R.id.et_username)
            etPassword = findViewById(R.id.et_password)
            var iUsername = etUser.text.toString()
            var iPassword = etPassword.text.toString()

            if (iUsername.equals("") && iPassword.equals("")) {
                etUser.error = "isi username terlebih dahulu"
                etPassword.error = "isi password terlebih dahulu"
                etPassword.requestFocus()
                etUser.requestFocus()
            } else if(iUsername.equals("")) {
                etUser.error = "isi username terlebih dahulu"
                etUser.requestFocus()
            } else if (iPassword.equals("")) {
                etPassword.error = "isi password terlebih dahulu"
                etPassword.requestFocus()
            } else {
                pushLogin(iUsername, iPassword)
            }
        }
    }

    private fun pushLogin(iUsername : String, iPassword : String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(this@SignInActivity, "Username tidak ditemukan",
                            Toast.LENGTH_LONG).show()
                } else {
                    if (user.password.equals(iPassword)) {

                        preference.setValues("nama", user.nama.toString())
                        preference.setValues("email", user.email.toString())
                        preference.setValues("password", user.password.toString())
                        preference.setValues("saldo", user.saldo.toString())
                        preference.setValues("url", user.url.toString())
                        preference.setValues("username", user.username.toString())
                        preference.setValues("status", "1")

                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@SignInActivity, "Password salah",
                                Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message,
                        Toast.LENGTH_LONG).show()
            }
        })
    }
}