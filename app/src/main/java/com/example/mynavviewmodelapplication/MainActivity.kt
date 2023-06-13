package com.example.mynavviewmodelapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.whenCreated
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.mynavviewmodelapplication.databinding.ActivityMainBinding
import com.example.mynavviewmodelapplication.fragments.AlbumsFragment
import com.example.mynavviewmodelapplication.fragments.FeedsFragment
import com.example.mynavviewmodelapplication.fragments.TodosFragment
import com.example.mynavviewmodelapplication.viewmodels.PostViewModel
import com.example.mynavviewmodelapplication.viewmodels.PostViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var postViewModel: PostViewModel
    private lateinit var postViewModelFactory: PostViewModelFactory

    private lateinit var mRequestQueue: RequestQueue




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.txtViewToolbarTitle.text = "Main"
        binding.btnProfile.setOnClickListener {
            val intent = Intent(this@MainActivity, UserProfileActivity::class.java)
            startActivity(intent)
        }


        //
        replaceFragment((FeedsFragment()))
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.feeds -> replaceFragment(FeedsFragment())
                R.id.albums -> replaceFragment(AlbumsFragment())
                R.id.todos -> replaceFragment(TodosFragment())

                else -> replaceFragment(FeedsFragment())
            }

            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()

    }
}