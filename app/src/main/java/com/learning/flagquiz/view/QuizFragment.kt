package com.learning.flagquiz.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.learning.flagquiz.R
import com.learning.flagquiz.database.FlagsDao
import com.learning.flagquiz.databinding.FragmentQuizBinding
import com.learning.flagquiz.model.FlagsModel
import com.techmania.flagquizwithsqlitedemo.DatabaseCopyHelper

class QuizFragment : Fragment() {

    lateinit var fragQuizBinding: FragmentQuizBinding
    var flagList = ArrayList<FlagsModel>()
    var wrongFlags = ArrayList<FlagsModel>()

    var incorrectNum = 0
    var correctNum = 0
    var skippedNum = 0
    var question = 0
    var optionClicked = false

    lateinit var correctFlag : FlagsModel
    val dao = FlagsDao()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragQuizBinding = FragmentQuizBinding.inflate(inflater,container,false)

        flagList = dao.randomFiveRecords(DatabaseCopyHelper(requireActivity()))

        showData()

        fragQuizBinding.option1.setOnClickListener {
            answerControl(fragQuizBinding.option1)
        }

        fragQuizBinding.option2.setOnClickListener {
            answerControl(fragQuizBinding.option2)
        }

        fragQuizBinding.option3.setOnClickListener {
            answerControl(fragQuizBinding.option3)
        }

        fragQuizBinding.option4.setOnClickListener {
            answerControl(fragQuizBinding.option4)
        }

        fragQuizBinding.buttonNext.setOnClickListener{
            question++
            if(question>4){ //when quiz is finished
                if(!optionClicked){
                    skippedNum++
                }
//                Toast.makeText(requireActivity(),"Quiz Finished",Toast.LENGTH_SHORT).show()
//                val direction = QuizFragmentDirections.actionQuizFragmentToResultFragment().apply {
//                    correct = correctNum
//                    incorrect = incorrectNum
//                    skipped = skippedNum
//                }
//                this.findNavController().navigate(
//                    R.id.action_quizFragment_to_resultFragment,
//                    direction.arguments,
//                    NavOptions.Builder().setPopUpTo(R.id.homeFragment,false).build())

                val bundle = Bundle().apply {
                    putInt("correct",correctNum)
                    putInt("incorrect",incorrectNum)
                    putInt("skipped",skippedNum)
                }


                this.findNavController().navigate(
                    R.id.action_quizFragment_to_resultFragment,
                    bundle,
                    NavOptions.Builder().setPopUpTo(R.id.homeFragment,false).build())

            }

            else{
                showData()
                if(!optionClicked){
                    skippedNum++
                    fragQuizBinding.tvSkipped.text = skippedNum.toString()
                }
                else{
                    setOptionToDefault()
                }
            }
            optionClicked = false

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

    private fun answerControl(button: Button){
        val clickedOption = button.text.toString()
        val correctAns = correctFlag.countryName

        if(clickedOption == correctAns){
            correctNum++
            fragQuizBinding.tvCorrect.text = correctNum.toString()
            button.setBackgroundColor(resources.getColor(R.color.correct,requireActivity().theme))
        }
        else{
            incorrectNum++
            fragQuizBinding.tvIncorrect.text = incorrectNum.toString()
            button.setBackgroundColor(resources.getColor(R.color.incorrect,requireActivity().theme))

            when(correctAns){

                fragQuizBinding.option1.text -> fragQuizBinding.option1.setBackgroundColor(resources.getColor(R.color.correct,requireActivity().theme))
                fragQuizBinding.option2.text -> fragQuizBinding.option2.setBackgroundColor(resources.getColor(R.color.correct,requireActivity().theme))
                fragQuizBinding.option3.text -> fragQuizBinding.option3.setBackgroundColor(resources.getColor(R.color.correct,requireActivity().theme))
                fragQuizBinding.option4.text -> fragQuizBinding.option4.setBackgroundColor(resources.getColor(R.color.correct,requireActivity().theme))

            }
        }

        fragQuizBinding.option1.isClickable = false
        fragQuizBinding.option2.isClickable = false
        fragQuizBinding.option3.isClickable = false
        fragQuizBinding.option4.isClickable = false
        optionClicked = true
    }

    private fun setOptionToDefault(){
        fragQuizBinding.option1.apply {
            setBackgroundColor(resources.getColor(R.color.slateBlue,requireActivity().theme))
            isClickable = true
        }
        fragQuizBinding.option2.apply {
            setBackgroundColor(resources.getColor(R.color.slateBlue,requireActivity().theme))
            isClickable = true
        }
        fragQuizBinding.option3.apply {
            setBackgroundColor(resources.getColor(R.color.slateBlue,requireActivity().theme))
            isClickable = true
        }
        fragQuizBinding.option4.apply {
            setBackgroundColor(resources.getColor(R.color.slateBlue,requireActivity().theme))
            isClickable = true
        }
    }

}