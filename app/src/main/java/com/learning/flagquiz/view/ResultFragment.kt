package com.learning.flagquiz.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.learning.flagquiz.R
import com.learning.flagquiz.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    lateinit var fragResultBinding: FragmentResultBinding
    var correctNum = 0F
    var incorrectNum = 0F
    var skippedNum = 0F

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragResultBinding = FragmentResultBinding.inflate(inflater,container,false)

        val args = arguments?.let {
            ResultFragmentArgs.fromBundle(it)
        }
        args?.let {
            correctNum = it.correct.toFloat()
            incorrectNum = it.incorrect.toFloat()
            skippedNum = it.skipped.toFloat()
        }

        var barEntriesCorrect = ArrayList<BarEntry>()
        var barEntriesIncorrect = ArrayList<BarEntry>()
        var barEntriesSkipped = ArrayList<BarEntry>()

        barEntriesCorrect.add(BarEntry(0F,correctNum))
        barEntriesIncorrect.add(BarEntry(1F,incorrectNum))
        barEntriesSkipped.add(BarEntry(2F,skippedNum))

        val barDatasetCorrect = BarDataSet(barEntriesCorrect,"Correct Answers").apply {
            color = ContextCompat.getColor(requireContext(), R.color.correct)
            valueTextSize = 16F
            setValueTextColors(arrayListOf(Color.BLACK))
        }
        val barDatasetIncorrect = BarDataSet(barEntriesIncorrect,"Incorrect Answers").apply {
            color = ContextCompat.getColor(requireContext(), R.color.incorrect)
            valueTextSize = 16F
            setValueTextColors(arrayListOf(Color.BLACK))
        }
        val barDatasetSkipped = BarDataSet(barEntriesSkipped,"Skipped Answers").apply {
            color = ContextCompat.getColor(requireContext(), R.color.slateBlue)
            valueTextSize = 16F
            setValueTextColors(arrayListOf(Color.BLACK))
        }
        val barData = BarData(barDatasetCorrect,barDatasetIncorrect,barDatasetSkipped)

        fragResultBinding.chart.data = barData

        fragResultBinding.buttonAgain.setOnClickListener{
            this.findNavController().popBackStack(R.id.homeFragment, inclusive = false)
        }

        fragResultBinding.buttonExit.setOnClickListener {
            requireActivity().finish()
        }

        return fragResultBinding.root
    }

}