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
package com.example.forage.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fbros.databinding.ListItemStopBinding
import com.example.fbros.model.Stop

/**
 * ListAdapter for the list of [Forageable]s retrieved from the database
 */
class StopListAdapter(
    private val clickListener: (Stop) -> Unit
) : ListAdapter<Stop, StopListAdapter.StopViewHolder>(DiffCallback) {

    class StopViewHolder(
        private var binding: ListItemStopBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(stop: Stop) {
            binding.stop = stop
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Stop>() {
        override fun areItemsTheSame(oldItem: Stop, newItem: Stop): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Stop, newItem: Stop): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return StopViewHolder(
            ListItemStopBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StopViewHolder, position: Int) {
        val stop = getItem(position)
        holder.itemView.setOnClickListener{
            clickListener(stop)
        }
        holder.bind(stop)
    }
}
