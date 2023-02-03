package com.egp.bwamov.home.ticket

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egp.bwamov.R
import com.egp.bwamov.home.dashboard.ComingSoonAdapter
import com.egp.bwamov.model.Film
import com.egp.bwamov.util.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_ticket.*

class TicketFragment : Fragment() {

    private lateinit var preferences: Preferences
    private lateinit var mDatabase : DatabaseReference
    private var datalist = ArrayList<Film>()
    private lateinit var rvTotalMovies : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity?.applicationContext!!)
        mDatabase = FirebaseDatabase
            .getInstance("https://bwa-mov-ebc05-default-rtdb.firebaseio.com/")
            .getReference("Film")

        rvTotalMovies = view?.findViewById(R.id.rv_total_movies)!!
        rvTotalMovies.layoutManager = LinearLayoutManager(context)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                datalist.clear()
                for (getDataSnapshot in dataSnapshot.children) {
                    val film = getDataSnapshot.getValue(Film::class.java)
                    datalist.add(film!!)
                }

                rvTotalMovies.adapter = ComingSoonAdapter(datalist) {
                    var intent = Intent(context, TicketActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

                tv_total_movies.setText("${datalist.size} Movies")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}