package com.learning.flagquiz.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learning.flagquiz.R
import com.learning.flagquiz.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    lateinit var fragResultBinding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragResultBinding = FragmentResultBinding.inflate(inflater,container,false)

        fragResultBinding.buttonAgain.setOnClickListener{

        }

        fragResultBinding.buttonExit.setOnClickListener {

        }

        return fragResultBinding.root
    }

}