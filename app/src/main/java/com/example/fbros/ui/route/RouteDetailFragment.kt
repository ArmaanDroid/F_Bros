package com.example.fbros.ui.route

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fbros.FBrosApplication
import com.example.fbros.R
import com.example.fbros.databinding.FragmentRouteDetailBinding


class RouteDetailFragment : Fragment() {
    // Arguments
    private val navArgs : RouteDetailFragmentArgs by navArgs()

    // Binding
    private var _binding : FragmentRouteDetailBinding? = null
    private val binding get() = _binding!!

    //ViewModel
    private val viewModel : ViewModelRoutes by viewModels {
        RoutesViewModelFactory((activity?.application as FBrosApplication ).db.getRouteDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragmen
        _binding = FragmentRouteDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val routeId = navArgs.routeId

        viewModel.getRouteById(routeId).observe(viewLifecycleOwner){
            binding.route = it
        }

        binding.editRouteFab.setOnClickListener {

            val action = RouteDetailFragmentDirections
                .actionRouteDetailFragmentToAddEditRouteFragment(
                    title = getString(R.string.title_edit_route),routeId = routeId)

            findNavController().navigate(action)
        }

    }


}