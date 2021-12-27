package com.example.fbros.ui.stop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fbros.R
import com.example.fbros.databinding.FragmentStopsBinding
import com.example.forage.ui.adapter.StopListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StopsFragment : Fragment() {

    private var _binding : FragmentStopsBinding? = null
            val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStopsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StopListAdapter { stop ->
            val action = StopsFragmentDirections.actionStopListFragmentToStopDetailFragment()
            findNavController().navigate(action)
        }

        view.findViewById<FloatingActionButton>(R.id.fab_add_stop).setOnClickListener {
            openAddStop()
        }

        binding.apply {
            recyclerStops.adapter = adapter
        }

    }

    fun openAddStop(){
        val action = StopsFragmentDirections.actionStopListFragmentToAddEditStopFragment(getString(
            R.string.add_stop))
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        //TODO Destroy the binding reference
    }


}