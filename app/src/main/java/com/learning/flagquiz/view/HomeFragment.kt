package com.learning.flagquiz.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.learning.flagquiz.R
import com.learning.flagquiz.databinding.FragmentHomeBinding
import com.techmania.flagquizwithsqlitedemo.DatabaseCopyHelper

class HomeFragment : Fragment() {

    lateinit var fragHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragHomeBinding = FragmentHomeBinding.inflate(inflater,container,false)
        createAndOpenDB()

        fragHomeBinding.buttonStart.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToQuizFragment()
            this.findNavController().navigate(direction)

        }

        return fragHomeBinding.root
    }

    private fun createAndOpenDB(){
        try {
            val helper = DatabaseCopyHelper(requireActivity())
            helper.createDataBase()
            helper.openDataBase()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}