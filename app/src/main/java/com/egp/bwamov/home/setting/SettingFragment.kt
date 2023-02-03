package com.egp.bwamov.home.setting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.egp.bwamov.EditProfileActivity
import com.egp.bwamov.R
import com.egp.bwamov.util.Preferences
import com.egp.bwamov.wallet.MyWalletActivity
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : Fragment() {

    lateinit var preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_my_wallet_setting.setOnClickListener {
            var intent = Intent(context, MyWalletActivity::class.java)
            startActivity(intent)
        }

        tv_edit_profile_setting.setOnClickListener {
            var intent = Intent(context, EditProfileActivity::class.java)
            startActivity(intent)
        }

        preferences = Preferences(activity?.applicationContext!!)

        tv_profile_name_setting.text = preferences.getValues("nama")
        tv_email_setting.text = preferences.getValues("email")

        Glide.with(this)
                .load(preferences.getValues("url"))
                .apply(RequestOptions.circleCropTransform())
                .into(iv_profile_setting)
    }

}