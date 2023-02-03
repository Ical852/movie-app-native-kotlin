package com.egp.bwamov.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.egp.bwamov.R
import com.egp.bwamov.util.Preferences
import com.egp.bwamov.wallet.adapter.WalletAdapter
import com.egp.bwamov.wallet.model.Wallet
import kotlinx.android.synthetic.main.activity_my_wallet.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class MyWalletActivity : AppCompatActivity() {

    private lateinit var preferences: Preferences
    private var datalist = ArrayList<Wallet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_wallet)

        datalist.add(
            Wallet(
                "Avengers Returns",
                "Sabtu 12 Jan, 2022",
                70000.0,
                "0"
            )
        )

        datalist.add(
            Wallet(
                "Top Up",
                "Sabtu 12 Jan, 2022",
                170000.0,
                "1"
            )
        )

        datalist.add(
            Wallet(
                "Avengers Home Coming",
                "Sabtu 12 Des, 2021",
                70000.0,
                "0"
            )
        )

        rv_daftar_transaksi.layoutManager = LinearLayoutManager(this)
        rv_daftar_transaksi.adapter = WalletAdapter(datalist) {

        }

        preferences = Preferences(this)

        btn_back_top_up.setOnClickListener {
            finish()
        }

        btn_top_up_saldo.setOnClickListener {
            var intent = Intent(this@MyWalletActivity, MyWalletTopUpActivity::class.java)
            startActivity(intent)
        }

        if (preferences.getValues("saldo").equals("")) {
            mySaldo(preferences.getValues("saldo")!!.toDouble(), tv_saldo_top_up)
        } else {
            mySaldo(preferences.getValues("saldo")!!.toDouble(), tv_saldo_top_up)
        }
    }

    private fun mySaldo(harga : Double, textView: TextView) {
        val localID = Locale("id", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)
        textView.setText(format.format(harga))
    }
}