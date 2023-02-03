package com.egp.bwamov.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.egp.bwamov.R
import com.egp.bwamov.home.dashboard.DashboardFragment
import com.egp.bwamov.home.setting.SettingFragment
import com.egp.bwamov.home.ticket.TicketFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var ivHome : ImageView
    private lateinit var ivTicket : ImageView
    private lateinit var ivSetting : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentHome = DashboardFragment()
        val fragmentTicket = TicketFragment()
        val settingFragment = SettingFragment()
        setFragment(fragmentHome)

        ivHome = findViewById(R.id.iv_menu1)
        ivTicket = findViewById(R.id.iv_menu2)
        ivSetting = findViewById(R.id.iv_menu3)

        changeIcon(ivHome, R.drawable.ic_home_active)

        ivHome.setOnClickListener {
            setFragment(fragmentHome)
            changeIcon(ivHome, R.drawable.ic_home_active)
            changeIcon(ivTicket, R.drawable.ic_tiket)
            changeIcon(ivSetting, R.drawable.ic_profile)
        }

        ivTicket.setOnClickListener {
            setFragment(fragmentTicket)
            changeIcon(ivHome, R.drawable.ic_home)
            changeIcon(ivTicket, R.drawable.ic_tiket_active)
            changeIcon(ivSetting, R.drawable.ic_profile)
        }

        ivSetting.setOnClickListener {
            setFragment(settingFragment)
            changeIcon(ivHome, R.drawable.ic_home)
            changeIcon(ivTicket, R.drawable.ic_tiket)
            changeIcon(ivSetting, R.drawable.ic_profile_active)
        }
    }

    private fun setFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int) {
        imageView.setImageResource(int)
    }
}