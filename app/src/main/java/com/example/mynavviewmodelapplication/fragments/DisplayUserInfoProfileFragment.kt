package com.example.mynavviewmodelapplication.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mynavviewmodelapplication.R
import com.example.mynavviewmodelapplication.databinding.FragmentDisplayUserInfoProfileBinding


/**
 * A simple [Fragment] subclass.
 * Use the [DisplayUserInfoProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DisplayUserInfoProfileFragment : Fragment() {

    private lateinit var binding: FragmentDisplayUserInfoProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   val inflater = TransitionInflater.from(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_display_user_info_profile, container, false)
       binding = FragmentDisplayUserInfoProfileBinding.inflate(inflater, container, false)
        val view =binding.root

        binding.buttonEdit.setOnClickListener {
            val trans = requireActivity().supportFragmentManager.beginTransaction()
            trans.replace(R.id.fragmentContainerView, EditUserInfoProfileFragment()).commit()
        }


        return view


    }


}