package com.egp.bwamov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.egp.bwamov.R
import com.egp.bwamov.sign.signin.SignInActivity

class OnboardingThreeActivity : AppCompatActivity() {
    private lateinit var btnLanjutkan : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_three)

        btnLanjutkan = findViewById(R.id.btn_lanjutkanThree)
        btnLanjutkan.setOnClickListener {
            finishAffinity()
            var intent = Intent(this@OnboardingThreeActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}