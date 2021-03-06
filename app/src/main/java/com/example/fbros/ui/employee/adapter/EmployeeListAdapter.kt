/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.fbros.ui.employee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fbros.databinding.ListItemEmployeeBinding
import com.example.fbros.model.Employee

class EmployeeListAdapter(
    private val clickListener: (Employee) -> Unit
) : ListAdapter<Employee, EmployeeListAdapter.EmployeeViewHolder>(DiffCallback) {

    class EmployeeViewHolder(
        private var binding: ListItemEmployeeBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(employee: Employee) {
            binding.employee = employee
            binding.executePendingBindings()

        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Employee>() {
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EmployeeViewHolder(
            ListItemEmployeeBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = getItem(position)
        holder.itemView.setOnClickListener{
            clickListener(employee)
        }
        holder.bind(employee)
    }
}


