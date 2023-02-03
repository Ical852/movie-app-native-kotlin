package com.egp.bwamov.checkout

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.egp.bwamov.R
import com.egp.bwamov.home.ticket.TicketActivity
import com.egp.bwamov.model.Checkout
import com.egp.bwamov.model.Film
import com.egp.bwamov.util.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    private var total:Int = 0
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>
        val data = intent.getParcelableExtra<Film>("datas")

        for (a in dataList.indices){
            total += dataList[a].harga!!.toInt()
        }

        dataList.add(Checkout("Total Harus Dibayar", total.toString()))

        rv_checkout.layoutManager = LinearLayoutManager(this)
        rv_checkout.adapter = CheckoutAdapter(dataList) {

        }

        btn_batal_checkout.setOnClickListener {
            finish()
        }

        btn_bayar_sekarang.setOnClickListener {
            var intent = Intent(this, CheckoutSuccessActivity::class.java)
            startActivity(intent)

//            showNotif(data!!)
        }
    }

//    private fun showNotif(datas: Film) {
//        val NOTIFICATION_CHANNEL_ID = "bwa move notif"
//        val context = this.applicationContext
//        var notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            val channelName = "BWAMOV Notif Channel"
//            val importance = NotificationManager.IMPORTANCE_HIGH
//
//            val mChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance)
//            notificationManager.createNotificationChannel(mChannel)
//        }
//
//        val mIntent = Intent(this, TicketActivity::class.java)
//        val bundle = Bundle()
//        bundle.putParcelable("data", datas)
//        mIntent.putExtras(bundle)
//
//        val pendingIntent =
//                PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)
//
//        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
//        builder.setContentIntent(pendingIntent)
//                .setSmallIcon(R.drawable.logo_mov)
//                .setLargeIcon(
//                        BitmapFactory.decodeResource(
//                                this.resources,
//                                R.drawable.logo_notification
//                        )
//                )
//                .setTicker("notif bwa starting")
//                .setAutoCancel(true)
//                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
//                .setLights(Color.RED, 3000, 3000)
//                .setDefaults(Notification.DEFAULT_SOUND)
//                .setContentTitle("Sukses Terbeli")
//                .setContentText("Tiket "+datas.judul+" berhasil kamu dapatkan. Enjoy the movie!")
//
//        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.notify(115, builder.build())
//    }
}