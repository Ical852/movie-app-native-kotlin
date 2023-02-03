package com.egp.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.egp.bwamov.checkout.PilihBangkuActivity
import com.egp.bwamov.home.dashboard.PlaysAdapter
import com.egp.bwamov.model.Film
import com.egp.bwamov.model.Plays
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var mDatabase : DatabaseReference
    private var dataList = ArrayList<Plays>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<Film>("data")

        mDatabase = FirebaseDatabase
                .getInstance("https://bwa-mov-ebc05-default-rtdb.firebaseio.com/")
                .getReference("Film")
                .child(data?.judul.toString())
                .child("play")

        tv_kursi_detail.text = data?.judul
        tv_genre_detail.text = data?.genre
        tv_desc_detail.text = data?.desc
        tv_rate_detail.text = data?.rating

        Glide.with(this)
                .load(data?.poster)
                .into(iv_poster_detail)

        rv_who_play.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()

        btn_pilih_bangku.setOnClickListener {
            var intent = Intent(this@DetailActivity, PilihBangkuActivity::class.java)
                    .putExtra("data", data)
            startActivity(intent)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()
                for (getDataSnapshot in dataSnapshot.children) {
                    var plays = getDataSnapshot.getValue(Plays::class.java)
                    dataList.add(plays!!)
                }

                rv_who_play.adapter = PlaysAdapter(dataList){

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@DetailActivity, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}