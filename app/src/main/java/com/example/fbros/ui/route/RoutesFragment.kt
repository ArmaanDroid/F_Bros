package com.example.fbros.ui.route

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fbros.FBrosApplication
import com.example.fbros.R
import com.example.fbros.databinding.FragmentRoutesBinding
import com.example.forage.ui.adapter.RouteListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class RoutesFragment : Fragment() {

     //Bindings
    private var _binding : FragmentRoutesBinding? = null
    private val binding get()= _binding!!

    //ViewModel
    private val viewModel: ViewModelRoutes by viewModels {
        RoutesViewModelFactory((activity?.application as FBrosApplication).db.getRouteDao())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//         Inflate the layout for this fragment
        _binding = FragmentRoutesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RouteListAdapter{
            val action = RoutesFragmentDirections.actionRouteListFragmentToRouteDetailFragment(it.id)
            findNavController().navigate(action)
        }

        binding.apply {
            recyclerRoutes.adapter = adapter
        }

        viewModel.routeList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        view.findViewById<FloatingActionButton>(R.id.fab_add_route).setOnClickListener {
            openAddRoute()
        }

    }

    fun openAddRoute(){
        val action = RoutesFragmentDirections.actionRouteListFragmentToAddEditRouteFragment(getString(
                    R.string.add_route))
        findNavController().navigate(action)
    }

}