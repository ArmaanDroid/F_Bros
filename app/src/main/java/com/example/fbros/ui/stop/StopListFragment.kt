package com.example.fbros.ui.stop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fbros.R
import com.example.fbros.databinding.FragmentStopListBinding
import com.example.fbros.ui.route.RouteListFragmentDirections
import com.example.forage.ui.adapter.StopListAdapter

class StopListFragment : Fragment() {

    private var _binding : FragmentStopListBinding? = null
            val binding = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStopListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StopListAdapter { stop ->
            val action = StopListFragmentDirections.actionStopListFragmentToStopDetailFragment()
            findNavController().navigate(action)
        }

        binding.apply {
            fragment = this@StopListFragment
            recyclerStops.adapter = adapter
        }

    }

    fun openAddStop(){
        val action = StopDetailFragmentDirections.actionStopDetailFragmentToAddEditStopFragment(getString(
            R.string.add_stop))
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        //TODO Destroy the binding reference
    }


}