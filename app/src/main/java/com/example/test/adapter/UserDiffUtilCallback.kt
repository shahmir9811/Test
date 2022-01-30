package com.example.test.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.test.data.model.dbModel.User

class UserDiffUtilCallback (private val oldList: ArrayList<User>, private val newList: ArrayList<User>) :
    DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id === newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.id === newItem.id && oldItem.name === newItem.name && oldItem.address === newItem.address
    }
}