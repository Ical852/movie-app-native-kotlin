package com.egp.bwamov.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.egp.bwamov.R
import com.egp.bwamov.home.HomeActivity
import kotlinx.android.synthetic.main.activity_top_up_success.*

class TopUpSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up_success)

        btn_lihat_wallet.setOnClickListener {
            startActivity(Intent(this@TopUpSuccessActivity, MyWalletActivity::class.java))
        }

        btn_home_wallet.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this@TopUpSuccessActivity, HomeActivity::class.java))
        }
    }
}