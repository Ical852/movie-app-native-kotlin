package com.egp.bwamov.wallet.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.egp.bwamov.R
import com.egp.bwamov.wallet.model.Wallet
import java.text.NumberFormat
import java.util.*

class WalletAdapter(private var data: List<Wallet>,
                    private val listener:(Wallet) -> Unit) :
    RecyclerView.Adapter<WalletAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_transaction, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: WalletAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tv_movie_name_wallet)
        private val tvDate: TextView = view.findViewById(R.id.tv_movie_date_wallet)
        private val tvPrice: TextView = view.findViewById(R.id.tv_price_movie_wallet)

        fun bindItem(data:Wallet, listener: (Wallet) -> Unit, context: Context) {
            tvTitle.text = data.title
            tvDate.text = data.date

            val localID = Locale("id", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localID)

            if (data.status.equals("0")) {
                tvPrice.text = "-"+formatRupiah.format(data.money)
            } else {
                tvPrice.text = "+"+formatRupiah.format(data.money)
                tvPrice.setTextColor(Color.GREEN)
            }

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

    override fun getItemCount(): Int = data.size

}
