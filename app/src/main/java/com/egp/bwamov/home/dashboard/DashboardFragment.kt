package com.egp.bwamov.home.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.egp.bwamov.DetailActivity
import com.egp.bwamov.R
import com.egp.bwamov.model.Film
import com.egp.bwamov.util.Preferences
import com.google.firebase.database.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {
    private lateinit var preferences : Preferences
    private lateinit var mDatabase : DatabaseReference
    private lateinit var tvNama : TextView
    private lateinit var tvSaldo : TextView
    private lateinit var ivProfile : ImageView
    private lateinit var rvNowPlaying : RecyclerView
    private lateinit var rvComingSoon : RecyclerView

    private var dataList = ArrayList<Film>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity?.applicationContext!!)
        mDatabase = FirebaseDatabase.getInstance("https://bwa-mov-ebc05-default-rtdb.firebaseio.com/").getReference("Film")

        tvNama = view?.findViewById(R.id.tv_nama)!!
        tvSaldo = view?.findViewById(R.id.tv_saldo)!!
        ivProfile = view?.findViewById(R.id.iv_profile)!!
        rvNowPlaying = view?.findViewById(R.id.rv_now_playing)!!
        rvComingSoon = view?.findViewById(R.id.rv_coming_soon)!!

        tvNama.setText(preferences.getValues("nama"))
        if (preferences.getValues("saldo").equals("")) {
            currency(preferences.getValues("saldo")!!.toDouble(), tvSaldo)
        } else {
            currency(preferences.getValues("saldo")!!.toDouble(), tvSaldo)
        }

        Glide.with(this)
                .load(preferences.getValues("url"))
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfile)

        rvNowPlaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvComingSoon.layoutManager = LinearLayoutManager(context)

        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()
                for (getDataSnapshot in dataSnapshot.children) {
                    var film = getDataSnapshot.getValue(Film::class.java)
                    dataList.add(film!!)
                }

                rvNowPlaying.adapter = NowPlayingAdapter(dataList) {
                    var intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

                rvComingSoon.adapter = ComingSoonAdapter(dataList) {
                    var intent = Intent(context, DetailActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun currency(harga : Double, textView: TextView) {
        val localID = Locale("id", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)
        textView.setText(format.format(harga))
    }
}