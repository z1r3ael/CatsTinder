package com.example.week8catsnavigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week8catsnavigation.R
import com.example.week8catsnavigation.databinding.FragmentAboutAppBinding
import com.example.week8catsnavigation.fragments.contract.HasCustomTitle


class AboutAppFragment : Fragment(), HasCustomTitle {

    private lateinit var binding: FragmentAboutAppBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getTitleRes(): Int = R.string.about_title
}