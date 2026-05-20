package com.app.testapp.ui.listscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.testapp.R
import com.app.testapp.api.response.GitHubUserListResponseItem
import com.app.testapp.databinding.RowListItemBinding


class MainAdapter : RecyclerView.Adapter<MainAdapter.GitHubViewHolder>() {

    private val repositoryList = mutableListOf<GitHubUserListResponseItem>()

    fun updateList(newList: List<GitHubUserListResponseItem>) {
        repositoryList.clear()
        repositoryList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubViewHolder {
        val binding: RowListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_list_item,
            parent,
            false
        )
        return GitHubViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitHubViewHolder, position: Int) {
        holder.bind(repositoryList[position])
    }

    override fun getItemCount(): Int = repositoryList.size

    inner class GitHubViewHolder(private val binding: RowListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repository: GitHubUserListResponseItem) {
            binding.repository = repository
            binding.executePendingBindings()
        }
    }
}