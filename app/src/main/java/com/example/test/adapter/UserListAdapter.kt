package com.example.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.test.app.loadByUrl
import com.example.test.data.model.dbModel.User
import com.example.test.data.model.dbModel.Users
import com.example.test.databinding.ListItemProgressBinding
import com.example.test.databinding.ListItemUserBinding

class UserListAdapter(
    private val onItemClicked: (User) -> Unit,
    private var users: ArrayList<User> = ArrayList(),
    private var isLoading: Boolean = false,
    private var filteredUsers: ArrayList<User> = ArrayList(),
    private var query: String = ""
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == 0){
            ViewHolder(ListItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
        }else {
            ProgressViewHolder(ListItemProgressBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
        }
    }

    override fun getItemCount(): Int {
        return filteredUsers.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if(position <= filteredUsers.size - 1){
            0
        }else{
            1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder.itemViewType == 0) {
            val viewHolder = holder as ViewHolder
            viewHolder.bind(filteredUsers[position], onItemClicked)
        }else {
            val viewHolder = holder as ProgressViewHolder
            viewHolder.bind(isLoading && users.size != 0)
        }
    }

    fun setLoading(isLoading: Boolean){
        this.isLoading = isLoading
        //notify last item
        notifyItemChanged(filteredUsers.size)
    }

    fun addUsers(users: Users) {
        val oldListSize = filteredUsers.size

        this.users.addAll(users)
        filteredUsers.addAll(users)
        filter(query)

        notifyItemChanged(oldListSize, filteredUsers.size)
    }

    fun setUsers(users: Users) {
        this.users = ArrayList(users)
        filteredUsers = this.users

        notifyDataSetChanged()
    }


    fun filterUsers(query: String){
        val oldUserList = ArrayList(filteredUsers)

        filter(query)
        this.query = query

        updateListByDiff(oldUserList, filteredUsers)
    }

    private fun updateListByDiff(oldList: ArrayList<User>, newList: ArrayList<User>){
        val diffResult = DiffUtil
            .calculateDiff(UserDiffUtilCallback(oldList, newList))
        diffResult.dispatchUpdatesTo(this)
    }

    private fun filter(query: String){
        if(query.isEmpty()){
            filteredUsers = ArrayList(users)
            return
        }

        if(users.isEmpty()){
            return
        }

        filteredUsers = users.filter {
            val bool =
                it.name.toLowerCase().contains(query.toLowerCase()) ||
                it.phoneNumber.toLowerCase().contains(query.toLowerCase()) ||
                it.address.toLowerCase().contains(query.toLowerCase()) ||
                it.country.toLowerCase().contains(query.toLowerCase()) ||
                it.gender.toLowerCase().contains(query.toLowerCase())
           bool
        } as ArrayList<User>
    }

    class ViewHolder(private val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, onItemClicked: (User) -> Unit) {
            binding.imageView.setOnClickListener {
                onItemClicked(user)
            }

            binding.imageView.loadByUrl(user.pictureUrl)
            binding.nameTv.text = user.name
            binding.genderPhoneTv.text = user.gender + " " + user.phoneNumber
            binding.countryTv.text = user.country
            binding.addressTv.text = user.address
        }
    }

    class ProgressViewHolder(val binding: ListItemProgressBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(isLoading: Boolean) {
                if(isLoading){
                    binding.progressBar.visibility = View.VISIBLE
                }else{
                    binding.progressBar.visibility = View.GONE
                }
            }
    }
}
