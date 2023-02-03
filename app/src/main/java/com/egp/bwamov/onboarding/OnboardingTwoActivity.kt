package com.egp.bwamov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.egp.bwamov.R
import com.egp.bwamov.sign.signin.SignInActivity

class OnboardingTwoActivity : AppCompatActivity() {
    private lateinit var btnLanjutkan : Button
    private lateinit var btnLewatkan : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_two)

        btnLanjutkan = findViewById(R.id.btn_lanjutkanTwo)
        btnLewatkan = findViewById(R.id.btn_lewatkanTwo)
        btnLanjutkan.setOnClickListener {
            var intent = Intent(this@OnboardingTwoActivity, OnboardingThreeActivity::class.java)
            startActivity(intent)
        }
        btnLewatkan.setOnClickListener {
            finishAffinity()
            var intent = Intent(this@OnboardingTwoActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}