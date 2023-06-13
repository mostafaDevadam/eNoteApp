package com.example.mynavviewmodelapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.example.mynavviewmodelapplication.R
import com.example.mynavviewmodelapplication.databinding.FragmentFeedsBinding
import com.android.volley.toolbox.Volley
import com.example.mynavviewmodelapplication.PostActivity
import com.example.mynavviewmodelapplication.adapters.PostsListViewAdapter
import com.example.mynavviewmodelapplication.adapters.PostsRecyclerViewAdapter
import com.example.mynavviewmodelapplication.models.Post
import com.example.mynavviewmodelapplication.viewmodels.*


class FeedsFragment : Fragment() {

    private lateinit var feedsViewModel: FeedsViewModel
    private lateinit var postViewModel: PostViewModel
    private lateinit var postViewModelFactory: PostViewModelFactory

    private lateinit var mRequestQueue: RequestQueue


    lateinit var textViewFeeds2: TextView
    lateinit var editTextFeedsTitle: EditText

    //    lateinit var  buttonFeeds1: Button
    lateinit var textViewFeedContent: TextView

    lateinit var listViewFeeds: ListView

    lateinit var recyclerViewFeeds: RecyclerView

    private lateinit var userViewModelFactory: UserViewModelFactory
    private lateinit var userModel: UserViewModel


    private lateinit var _binding: FragmentFeedsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding = ActivityMainBinding.inflate(layoutInflater)

        feedsViewModel = ViewModelProvider(this).get(FeedsViewModel::class.java)



        mRequestQueue = Volley.newRequestQueue(layoutInflater.context.applicationContext)

        postViewModelFactory = PostViewModelFactory(mRequestQueue)
        postViewModel = ViewModelProvider(this, postViewModelFactory).get(PostViewModel::class.java)

        userViewModelFactory = UserViewModelFactory((mRequestQueue))
        userModel = ViewModelProvider(this, userViewModelFactory).get(UserViewModel::class.java)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFeedsBinding.bind(view)
        //_binding.feedsMsg = "my binding in feeds"
        _binding.feedsviewmodel = feedsViewModel


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feeds, container, false)


        //listViewFeeds = view.findViewById(R.id.listViewFeeds)
        recyclerViewFeeds = view.findViewById(R.id.recyclerViewFeeds)
        recyclerViewFeeds.layoutManager = LinearLayoutManager(view.context)

        /*  textViewFeeds2 = view.findViewById(R.id.textViewFeeds2)
          editTextFeedsTitle = view.findViewById(R.id.editTextFeedsTitle)
          buttonFeeds1 = view.findViewById(R.id.buttonFeeds1)
          textViewFeedContent = view.findViewById(R.id.textViewFeedsContent)*/

        // textViewFeeds2.text ="start..."


        /* feedsViewModel.title.observe(viewLifecycleOwner, Observer {
             textViewFeeds2.text = it.toString()
         })*/

        feedsViewModel.title.observeForever {
            // textViewFeeds2.text = it.toString()
        }

        /*buttonFeeds1.setOnClickListener{
           // feedsViewModel.title.value = editTextFeedsTitle.text.trim().toString()
        }*/

        postViewModel.postTitle.observeForever {
            // textViewFeedContent.text = it.toString()
        }


        // postViewModel.getOnePostByID("1")
        /*postViewModel.getOnePostByID("1")

        postViewModel.onlinePost.observeForever {
            textViewFeedContent.text = it.toString()
            Log.d("response post in feeds", "$it")
            Log.d(" post in feeds", "$it")
        }*/
        postViewModel.onlinePosts.observeForever {
            if (it.isEmpty()) {
                Log.d("Online Posts Data Error", "No Online Posts")

            } else {
                Log.d("Online Posts Data", it.toString())
                println(it.toString())

                //   val adapter1 =
                ArrayAdapter<Post>(view.context, android.R.layout.simple_list_item_1, it)
                // val adapter = PostsListViewAdapter(view.context, it)
                //  listViewFeeds.adapter = adapter
                val recyclerAdapter = PostsRecyclerViewAdapter(it, userModel)
                recyclerViewFeeds.adapter = recyclerAdapter

                recyclerAdapter.onItemClick = {
                    Log.d("Post clicked", "clicked")
                    Toast.makeText(view.context, it.toString(), Toast.LENGTH_LONG).show()
                    // intent
                    val intent = Intent(view.context, PostActivity::class.java)
                    intent.putExtra("postId", "${it.id}")
                    startActivity(intent)

                }
            }
        }


        /*listViewFeeds.onItemClickListener =
            OnItemClickListener { parent, view, position,
                                  id ->
                println("selected!  $id $position " )

                // intent
                val intent = Intent(view.context, PostActivity::class.java)
                intent.putExtra("postId", "$id")
                startActivity(intent)


            }*/













        return view
    }

}