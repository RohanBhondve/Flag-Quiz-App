package com.learning.flagquiz.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learning.flagquiz.R
import com.learning.flagquiz.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    lateinit var fragQuizBinding: FragmentQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragQuizBinding = FragmentQuizBinding.inflate(inflater,container,false)

        fragQuizBinding.option1.setOnClickListener {

        }

        fragQuizBinding.option2.setOnClickListener {

        }

        fragQuizBinding.option3.setOnClickListener {

        }

        fragQuizBinding.option4.setOnClickListener {

        }

        return fragQuizBinding.root
    }

}