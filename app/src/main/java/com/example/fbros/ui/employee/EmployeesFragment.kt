package com.example.fbros.ui.employee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fbros.FBrosApplication
import com.example.fbros.R
import com.example.fbros.databinding.FragmentEmployeesBinding
import com.example.fbros.ui.employee.adapter.EmployeeListAdapter

class EmployeesFragment : Fragment() {

    // Binding
    private var _binding : FragmentEmployeesBinding? = null
    private val binding get()= _binding!!

    //ViewModel
    private val viewModel : ViewModelEmployee by viewModels {
        EmployeeViewModelFactory((activity?.application as FBrosApplication).db.getEmployeeDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEmployeesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = EmployeeListAdapter{ employee->
            val action = EmployeesFragmentDirections
                .actionEmployeeListFragmentToEmployeeDetailFragment(employeeId = employee.id)
            findNavController().navigate(action)
        }

        binding.apply{
//            fragment = this@EmployeesFragment
            recyclerEmployees.adapter = adapter
        }

        viewModel.employeeList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        binding.fabAddEmployee.setOnClickListener {
            openAddEmployee()
        }

    }

    fun openAddEmployee(){
        val action = EmployeesFragmentDirections
            .actionEmployeeListFragmentToAddEditEmployeeFragment(getString(
                    R.string.add_employee),0)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        //TODO Destroy the binding reference
    }
}