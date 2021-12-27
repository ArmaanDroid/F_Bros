package com.example.fbros.ui.route

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fbros.FBrosApplication
import com.example.fbros.R
import com.example.fbros.databinding.FragmentAddEditRouteBinding
import com.example.fbros.model.Route
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class AddEditRouteFragment : Fragment() {
    // Arguments defined in nav graph
    private val navArgs : AddEditRouteFragmentArgs by navArgs()

    // Bindings
    private var _binding : FragmentAddEditRouteBinding? = null
    private val binding get() = _binding!!

    private var currentRoute: Route? = null

    // ViewModel
    private  val  viewModel: ViewModelRoutes by viewModels {
        RoutesViewModelFactory((activity?.application as FBrosApplication).db.getRouteDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddEditRouteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val routeId = navArgs.routeId

        if(routeId<=0){
            binding.saveBtn.setOnClickListener {
                addRoute()
            }

        }else{

            viewModel.getRouteById(routeId).observe(viewLifecycleOwner){ route ->
                bindData(route)
            }

            binding.deleteBtn.visibility = View.VISIBLE

            binding.deleteBtn.setOnClickListener {
                showDeleteConfirmationDialog()
            }

            binding.saveBtn.text = getString(R.string.update)
            binding.saveBtn.setOnClickListener {
                updateRoute()
            }
        }
    }

    private fun showDeleteConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete Route")
            .setMessage("Are you sure to delete ${currentRoute?.name} Stop from record?")
            .setPositiveButton("YES") { _, _ ->
                deleteRoute()
            }
            .setNegativeButton("NO"){ _,_->

            }.show()
    }

    private fun updateRoute() {
        val name = binding.etName.text.toString()
        val city = binding.etCity.text.toString()
        if(viewModel.validateEntries(name,city)) {
            viewModel.updateRoute(navArgs.routeId,name,city)
            findNavController().navigate(R.id.action_addEditRouteFragment_to_routeListFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill all details",Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteRoute() {
        viewModel.deleteRoute(currentRoute!!)
    }

    private fun addRoute() {
        val name = binding.etName.text.toString()
        val city = binding.etCity.text.toString()
        if(viewModel.validateEntries(name,city)){
            viewModel.addRoute(name,city)
            findNavController().navigate(R.id.action_addEditRouteFragment_to_routeListFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill all details", Toast.LENGTH_LONG).show()
        }
    }


    private fun bindData(route: Route) {
        binding.etName.setText(route.name)
        binding.etCity.setText(route.city)
    }


}