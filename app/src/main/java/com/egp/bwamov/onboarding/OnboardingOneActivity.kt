package com.egp.bwamov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.egp.bwamov.R
import com.egp.bwamov.sign.signin.SignInActivity
import com.egp.bwamov.util.Preferences

class OnboardingOneActivity : AppCompatActivity() {

    private lateinit var preferences : Preferences

    private lateinit var btnLewatkan : Button
    private lateinit var btnLanjutkan : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_one)

        preferences = Preferences(this)
        if (preferences.getValues("onboarding").equals("1")) {
            finishAffinity()
            var intent = Intent(this@OnboardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        }

        btnLewatkan = findViewById(R.id.btn_lewatkanOne)
        btnLanjutkan = findViewById(R.id.btn_lanjutkanOne)
        btnLewatkan.setOnClickListener {
            finishAffinity()
            var intent = Intent(this@OnboardingOneActivity, SignInActivity::class.java)
            startActivity(intent)
        }

        btnLanjutkan.setOnClickListener {
            var intent = Intent(this@OnboardingOneActivity, OnboardingTwoActivity::class.java)
            startActivity(intent)
        }
    }
}