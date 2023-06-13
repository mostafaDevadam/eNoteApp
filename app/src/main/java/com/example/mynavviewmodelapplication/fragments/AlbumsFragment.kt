package com.example.mynavviewmodelapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.example.mynavviewmodelapplication.R
import com.example.mynavviewmodelapplication.databinding.FragmentAlbumsBinding
import com.example.mynavviewmodelapplication.databinding.FragmentTodosBinding
import com.example.mynavviewmodelapplication.viewmodels.AlbumViewModel
import com.example.mynavviewmodelapplication.viewmodels.AlbumViewModelFactory
import com.example.mynavviewmodelapplication.viewmodels.TodoViewModel
import com.example.mynavviewmodelapplication.viewmodels.TodoViewModelFactory
import com.android.volley.toolbox.Volley
import com.example.mynavviewmodelapplication.AlbumActivity
import com.example.mynavviewmodelapplication.adapters.AlbumsGridViewAdapter
import com.example.mynavviewmodelapplication.adapters.AlbumsRecyclerAdapter


class AlbumsFragment : Fragment() {

    private lateinit var albumViewModel: AlbumViewModel
    private lateinit var albumViewModelFactory: AlbumViewModelFactory
    private lateinit var mRequestQueue: RequestQueue


    lateinit var recyclerViewAlbums: RecyclerView

    lateinit var gridViewAlbums: GridView

    private lateinit var binding: FragmentAlbumsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRequestQueue = Volley.newRequestQueue(layoutInflater.context.applicationContext)
        albumViewModelFactory = AlbumViewModelFactory(mRequestQueue)
        albumViewModel = ViewModelProvider(this, albumViewModelFactory)
            .get(AlbumViewModel::class.java)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAlbumsBinding.bind(view)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_albums, container, false)

        gridViewAlbums = view.findViewById(R.id.grid_view_albums)
       // recyclerViewAlbums = view.findViewById(R.id.recyclerViewAlbums)

        //recyclerViewAlbums.layoutManager = LinearLayoutManager(view.context)

        albumViewModel.onlineAlbums.observeForever{
            if(it.isEmpty()){
                 Toast.makeText(view.context, "Album Error $it", Toast.LENGTH_LONG).show()
            }else{
                val gridAdapter = AlbumsGridViewAdapter(view.context,it)

                gridViewAlbums.adapter = gridAdapter

                gridViewAlbums.setOnItemClickListener { parent, view, position, id ->
                    val intent = Intent(view.context, AlbumActivity::class.java)
                    intent.putExtra("albumId", "${it[position].id}")
                    startActivity(intent)
                }

                /*val albumsRecyclerAdapter = AlbumsRecyclerAdapter(it)
                recyclerViewAlbums.adapter = albumsRecyclerAdapter

                albumsRecyclerAdapter.onItemClick = {
                    // intent
                    val intent = Intent(view.context, AlbumActivity::class.java)
                    intent.putExtra("albumId", "${it.id}")
                    startActivity(intent)
                }*/

            }
        }














        return view
    }

}