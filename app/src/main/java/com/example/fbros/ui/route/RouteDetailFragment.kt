package com.example.fbros.ui.route

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.fbros.R
import com.example.fbros.databinding.FragmentRouteDetailBinding


class RouteDetailFragment : Fragment() {
    // Arguments
    private val navArgs : RouteDetailFragmentArgs by navArgs()

    // Binding
    private var _binding : FragmentRouteDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragmen
        _binding = FragmentRouteDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val routeId = navArgs.routeId

    }


}