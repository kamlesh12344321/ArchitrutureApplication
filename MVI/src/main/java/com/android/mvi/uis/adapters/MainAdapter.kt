package com.android.mvi.uis.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.mvi.data.model.Users
import com.android.mvi.databinding.UserRowBinding
import com.bumptech.glide.Glide


class MainAdapter(private val users:ArrayList<Users>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
   inner class DataViewHolder(binding :UserRowBinding) : RecyclerView.ViewHolder(binding .root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : DataViewHolder {
        val binding = UserRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DataViewHolder(binding)
    }


    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
      val user = users[position]
        holder.itemView.apply {
            val binding = UserRowBinding.bind(this)
            binding.textViewUserName.text = user.first_name + " " + user.last_name
            binding.textViewUserEmail.text = user.email
            Glide.with(binding.imageViewAvatar.context)
                .load(user.avatar)
                .into(binding.imageViewAvatar)
        }
    }

    fun addUsers(users: List<Users>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }

}