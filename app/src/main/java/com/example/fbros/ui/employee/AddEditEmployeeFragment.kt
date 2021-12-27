package com.example.fbros.ui.employee

import android.content.DialogInterface
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
import com.example.fbros.databinding.FragmentAddEditEmployeeBinding
import com.example.fbros.model.Employee
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class AddEditEmployeeFragment : Fragment() {

    private val navArgs : AddEditEmployeeFragmentArgs by navArgs()

    // Binding with backing property, which should have get() associated with it.
    private var _binding : FragmentAddEditEmployeeBinding? = null
    private val binding get() = _binding!!

    private var currentEmployee:Employee? = null

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
                showDeleteConfirmationDialog()
            }

            viewModel.getEmployeeById(id).observe(viewLifecycleOwner){
                employee-> fillDetails(employee)
                currentEmployee = employee
            }

        }
    }

    private fun showDeleteConfirmationDialog() {
         MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete Employee")
            .setMessage("Are you sure to delete ${currentEmployee?.name} from record?")
            .setPositiveButton("YES") { _, _ ->
                deleteEmployee()
            }
            .setNegativeButton("NO"){ _,_->

            }.show()
    }

    private fun fillDetails(employee: Employee?) {
        employee?.let {
            binding.etName.setText(employee.name)
            binding.etEmail.setText(employee.emailId)
            binding.etPhone.setText(employee.phoneNumber)
            binding.etAddress.setText(employee.address)
            if(employee.gender) binding.radioButtonMale.isChecked = true
            else binding.radioButtonFemale.isChecked = true
        }

    }

    private fun deleteEmployee() {
        viewModel.deleteEmployee(currentEmployee!!)
        findNavController().navigate(R.id.action_addEditEmployeeFragment_to_employeeListFragment)
    }

    private fun updateEmployee() {
        val name =  binding.etName.text.toString()
        val email =  binding.etEmail.text.toString()
        val phone =  binding.etPhone.text.toString()
        val address =  binding.etAddress.text.toString()
        val gender = binding.radioButtonMale.isChecked

        if(viewModel.validateEntry(name,email,phone,address)){
            viewModel.updateEmployee(navArgs.employeeId,name,email,phone,address,gender)
            findNavController().navigate(R.id.action_addEditEmployeeFragment_to_employeeListFragment)
        }else{
            // TODO: 2021-12-26 set error to the corresponding fields
            Toast.makeText(requireContext(),"Please fill all details",Toast.LENGTH_LONG).show()
        }
    }

    private fun saveEmployee() {
        val name =  binding.etName.text.toString()
        val email =  binding.etEmail.text.toString()
        val phone =  binding.etPhone.text.toString()
        val address =  binding.etAddress.text.toString()
        val gender = binding.radioButtonMale.isChecked

        if(viewModel.validateEntry(name,email,phone,address)){
            viewModel.addEmployee(name,email,phone,address,gender)
            findNavController().navigate(R.id.action_addEditEmployeeFragment_to_employeeListFragment)
        }else{
            // TODO: 2021-12-26 set error to the corresponding fields
            Toast.makeText(requireContext(),"Please fill all details",Toast.LENGTH_LONG).show()
        }

    }

}