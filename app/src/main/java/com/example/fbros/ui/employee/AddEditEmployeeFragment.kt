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
import com.example.fbros.databinding.FragmentAddEditEmployeeBinding


class AddEditEmployeeFragment : Fragment() {

    private val navArgs : AddEditEmployeeFragmentArgs by navArgs()

    // Binding with backing property
    private var _binding : FragmentAddEditEmployeeBinding? = null
    private val binding get() = _binding!!

    //ViewModels
    val viewModel : ViewModelEmployee by viewModels {
        EmployeeViewModelFactory((activity?.application as FBrosApplication).db.getEmployeeDao())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddEditEmployeeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      val id =  navArgs.employeeId
        // id == 0 means we are adding an employee
        if(id<=0){

            binding.saveBtn.setOnClickListener {
                saveEmployee()
            }

        }else{
            binding.saveBtn.text = getString(R.string.update)
            binding.saveBtn.setOnClickListener {
                updateEmployee()
            }
            binding.deleteBtn.visibility = View.VISIBLE
            binding.deleteBtn.setOnClickListener {
                deleteEmployee()
            }

        }
    }

    private fun deleteEmployee() {

    }

    private fun updateEmployee() {

    }

    private fun saveEmployee() {

        val name =  binding.etName.text.toString()
        val email =  binding.etEmail.text.toString()
        val phone =  binding.etPhone.text.toString()
        val address =  binding.etAddress.text.toString()
        val gender = binding.radioButtonMale.isChecked

        if(validateEntry(name,email,phone,address)){
            viewModel.addEmployee(name,email,phone,address,gender)
            findNavController().navigate(R.id.action_addEditEmployeeFragment_to_employeeListFragment)
        }
    }

    private fun validateEntry(name: String, email: String, phone: String, address: String): Boolean {
        if(name.isBlank()||email.isBlank()||phone.isBlank()||address.isBlank())
            return false
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}