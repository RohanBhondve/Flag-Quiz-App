package com.learning.flagquiz.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.learning.flagquiz.R
import com.learning.flagquiz.database.FlagsDao
import com.learning.flagquiz.databinding.FragmentQuizBinding
import com.learning.flagquiz.model.FlagsModel
import com.techmania.flagquizwithsqlitedemo.DatabaseCopyHelper
import kotlin.math.log

class QuizFragment : Fragment() {

    lateinit var fragQuizBinding: FragmentQuizBinding
    var flagList = ArrayList<FlagsModel>()
    var wrongFlags = ArrayList<FlagsModel>()

    var incorrect = 0
    var correct = 0
    var skipped = 0
    var question = 0
    lateinit var correctFlag : FlagsModel
    val dao = FlagsDao()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragQuizBinding = FragmentQuizBinding.inflate(inflater,container,false)

        flagList = dao.randomFiveRecords(DatabaseCopyHelper(requireActivity()))

        for(flags in flagList){
            Log.d("flags",flags.id.toString())
            Log.d("flags",flags.countryName.toString())
            Log.d("flags",flags.flagName.toString())
            Log.d("flags","**********************")
        }

        showData()

        fragQuizBinding.option1.setOnClickListener {

        }

        fragQuizBinding.option2.setOnClickListener {

        }

        fragQuizBinding.option3.setOnClickListener {

        }

        fragQuizBinding.option4.setOnClickListener {

        }

        fragQuizBinding.buttonNext.setOnClickListener{
            question++
            showData()
        }

        return fragQuizBinding.root
    }

    private fun showData(){
        fragQuizBinding.question.text = resources.getString(R.string.question_num).plus(" ").plus(question+1)
        correctFlag = flagList[question]

        val context = requireContext()
        val resId = context.resources.getIdentifier(correctFlag.flagName, "drawable", context.packageName)
        val drawable = ResourcesCompat.getDrawable(context.resources, resId, context.theme)
        fragQuizBinding.imageFlag.setImageDrawable(drawable)

        wrongFlags = dao.randomThreeRecords(DatabaseCopyHelper(requireActivity()),correctFlag.id)

        var mixOption = HashSet<FlagsModel>()
        mixOption.clear()
        mixOption.add(correctFlag)
        mixOption.add(wrongFlags[0])
        mixOption.add(wrongFlags[1])
        mixOption.add(wrongFlags[2])

        var options = ArrayList<FlagsModel>()
        options.clear()
        for(flag in mixOption){
            options.add(flag)
        }

        fragQuizBinding.option1.text = options[0].countryName
        fragQuizBinding.option2.text = options[1].countryName
        fragQuizBinding.option3.text = options[2].countryName
        fragQuizBinding.option4.text = options[3].countryName

    }

}