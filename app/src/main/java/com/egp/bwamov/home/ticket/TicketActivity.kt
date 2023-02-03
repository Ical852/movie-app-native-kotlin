package com.egp.bwamov.home.ticket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.egp.bwamov.R
import com.egp.bwamov.model.Checkout
import com.egp.bwamov.model.Film
import kotlinx.android.synthetic.main.activity_ticket.*

class TicketActivity : AppCompatActivity() {

    private var datalist = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        var data = intent.getParcelableExtra<Film>("data")

        tv_title_ticket.text = data?.judul
        tv_genre_ticket.text = data?.genre
        tv_rate_ticket.text = data?.rating

        Glide.with(this)
            .load(data?.poster)
            .into(iv_poster_ticket)

        rv_checkout_ticket.layoutManager = LinearLayoutManager(this)
        datalist.add(Checkout("C1", ""))
        datalist.add(Checkout("C2", ""))

        rv_checkout_ticket.adapter = TicketAdapter(datalist) {

        }
    }
}