package com.learning.flagquiz.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learning.flagquiz.R
import com.learning.flagquiz.database.FlagsDao
import com.learning.flagquiz.databinding.FragmentQuizBinding
import com.learning.flagquiz.model.FlagsModel
import com.techmania.flagquizwithsqlitedemo.DatabaseCopyHelper
import kotlin.math.log

class QuizFragment : Fragment() {

    lateinit var fragQuizBinding: FragmentQuizBinding
    var flagList = ArrayList<FlagsModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragQuizBinding = FragmentQuizBinding.inflate(inflater,container,false)
        val dao = FlagsDao()
        flagList = dao.randomFiveRecords(DatabaseCopyHelper(requireActivity()))
        for(flags in flagList){
            Log.d("flags",flags.id.toString())
            Log.d("flags",flags.countryName.toString())
            Log.d("flags",flags.flagName.toString())
            Log.d("flags","**********************")
        }


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