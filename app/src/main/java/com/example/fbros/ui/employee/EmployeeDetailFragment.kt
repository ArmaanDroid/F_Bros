package com.example.fbros.ui.employee

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
import com.example.fbros.databinding.FragmentEmployeeDetailBinding

class EmployeeDetailFragment : Fragment() {

    private val navArgs : EmployeeDetailFragmentArgs by navArgs()

    //Binding
    private var _binding: FragmentEmployeeDetailBinding?= null
    private val binding get()  = _binding!!

    //ViewModel
    private val viewModel : ViewModelEmployee by viewModels {
        EmployeeViewModelFactory((activity?.application as FBrosApplication).db.getEmployeeDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEmployeeDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navArgs.employeeId

        viewModel.getEmployeeById(id).observe(viewLifecycleOwner){
            binding.employee = it
        }
//
        binding.editEmployeeFab.setOnClickListener {
            val action = EmployeeDetailFragmentDirections
                .actionEmployeeDetailFragmentToAddEditEmployeeFragment(
                    getString(R.string.title_edit_employee)
                    ,navArgs.employeeId
                )
            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //TODO Destroy the binding reference
    }
}