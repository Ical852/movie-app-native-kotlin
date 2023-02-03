package com.egp.bwamov.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.egp.bwamov.R
import com.egp.bwamov.util.Preferences
import kotlinx.android.synthetic.main.activity_my_wallet_top_up.*

class MyWalletTopUpActivity : AppCompatActivity() {

    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet_top_up)

        preferences = Preferences(this)

        tv_saldo_top_up.text = preferences.getValues("saldo")

        btn_back_top_up.setOnClickListener {
            finish()
        }

        btn_top_up.setOnClickListener {
            startActivity(Intent(this@MyWalletTopUpActivity, TopUpSuccessActivity::class.java))
        }
    }
}