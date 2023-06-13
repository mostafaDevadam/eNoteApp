package com.example.mynavviewmodelapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynavviewmodelapplication.fragments.DisplayUserInfoProfileFragment

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val trans = supportFragmentManager.beginTransaction()
        val displayUserInfoProfile = DisplayUserInfoProfileFragment()
        trans.add(R.id.fragmentContainerView, displayUserInfoProfile).commit()
    }
}