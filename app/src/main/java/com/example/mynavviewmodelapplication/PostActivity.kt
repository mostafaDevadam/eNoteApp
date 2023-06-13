package com.example.mynavviewmodelapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.mynavviewmodelapplication.adapters.CommentsRecyclerAdapter
import com.example.mynavviewmodelapplication.viewmodels.*

class PostActivity : AppCompatActivity() {

    private lateinit var mRequestQueue: RequestQueue

    private lateinit var postViewModel: PostViewModel
    private lateinit var postViewModelFactory: PostViewModelFactory

    private lateinit var commentViewModelFactory: CommentViewModelFactory
    private lateinit var commentViewModel: CommentViewModel

    private lateinit var userViewModelFactory: UserViewModelFactory
    private lateinit var userModel: UserViewModel

    private lateinit var textViewUserName: TextView
    private lateinit var textViewPostTitle: TextView
    private lateinit var textViewPostBody: TextView
    private lateinit var textViewComments: TextView

    private lateinit var recyclerViewComments: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        textViewUserName = findViewById(R.id.textViewUserNameInPost)
        textViewPostTitle = findViewById(R.id.textViewPostTitleInPost)
        textViewPostBody = findViewById(R.id.textViewPostBodyInPost)
       // textViewComments = findViewById(R.id.textViewCommentsInPost)

        recyclerViewComments = findViewById(R.id.recyclerviewComments)
        recyclerViewComments.layoutManager = LinearLayoutManager(this)

        val postId = intent.getStringExtra("postId")
        //val postId = intent
        println("postId in post $intent")

        // RequestQueue
        mRequestQueue = Volley.newRequestQueue(layoutInflater.context.applicationContext)

        // postViewModel
        postViewModelFactory = PostViewModelFactory(mRequestQueue)
        postViewModel = ViewModelProvider(this, postViewModelFactory).get(PostViewModel::class.java)

        // commentViewModel
        commentViewModelFactory = CommentViewModelFactory((mRequestQueue))
        commentViewModel = ViewModelProvider(this, commentViewModelFactory).get(CommentViewModel::class.java)
        // userModel
        userViewModelFactory = UserViewModelFactory((mRequestQueue))
        userModel = ViewModelProvider(this, userViewModelFactory).get(UserViewModel::class.java)


        // post
        postViewModel.getOnePostByID(postId.toString())

        postViewModel.onlinePost.observeForever{ it ->
            Toast.makeText(this@PostActivity, "post** $it", Toast.LENGTH_LONG).show()
            textViewPostTitle.text = it.title
            textViewPostBody.text = it.body
            // get user by id and display username in post
            userModel.getOneUserId(it.userId)
            userModel.onlineUser.observeForever{ user->
                textViewUserName.text = user.name
            }

        }
        // comment
        commentViewModel.getAllCommentsByPostId("$postId")
        commentViewModel.onlineComments.observeForever{
            Log.d("comments in post", it.toString())
            println("comments in post**: $it")
           // textViewComments.text = it.toString()

            val commentsAdapter = CommentsRecyclerAdapter(it)
            recyclerViewComments.adapter = commentsAdapter

            commentsAdapter.onItemclick = {
               Toast.makeText(this, "comment: $it" , Toast.LENGTH_LONG).show()
            }

        }


    }
}