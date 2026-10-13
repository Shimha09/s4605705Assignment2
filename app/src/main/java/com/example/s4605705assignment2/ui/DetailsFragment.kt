package com.example.s4605705assignment2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.s4605705assignment2.R

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(
            R.layout.fragment_details,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val tvName =
            view.findViewById<TextView>(R.id.tvDetailsName)

        val tvArchitect =
            view.findViewById<TextView>(R.id.tvDetailsArchitect)

        val tvLocation =
            view.findViewById<TextView>(R.id.tvDetailsLocation)

        val tvYear =
            view.findViewById<TextView>(R.id.tvDetailsYear)

        val tvStyle =
            view.findViewById<TextView>(R.id.tvDetailsStyle)

        val tvHeight =
            view.findViewById<TextView>(R.id.tvDetailsHeight)

        val tvDescription =
            view.findViewById<TextView>(R.id.tvDetailsDescription)

        val name = arguments?.getString("name") ?: ""
        val architect = arguments?.getString("architect") ?: ""
        val location = arguments?.getString("location") ?: ""
        val yearCompleted = arguments?.getInt("yearCompleted") ?: 0
        val style = arguments?.getString("style") ?: ""
        val height = arguments?.getInt("height") ?: 0
        val description = arguments?.getString("description") ?: ""

        tvName.text = name
        tvArchitect.text = "Architect: $architect"
        tvLocation.text = "Location: $location"
        tvYear.text = "Year Completed: $yearCompleted"
        tvStyle.text = "Style: $style"
        tvHeight.text = "Height: $height m"
        tvDescription.text = description
    }
}