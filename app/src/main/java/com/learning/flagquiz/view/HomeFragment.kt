package com.learning.flagquiz.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.learning.flagquiz.R
import com.learning.flagquiz.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var fragHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragHomeBinding = FragmentHomeBinding.inflate(inflater,container,false)

        fragHomeBinding.buttonStart.setOnClickListener{
            val fm: FragmentManager = requireActivity().supportFragmentManager
            val ft: FragmentTransaction = fm.beginTransaction()
            val quizFrag = QuizFragment()
            ft.replace(R.id.mainFrame,quizFrag)
            ft.commit()
        }

        return fragHomeBinding.root
    }

}