package com.example.test.view.fragments.homeTabs

import android.os.Bundle
import android.view.View

class UserListFragment : BaseUserListFragment(){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun isShowSavedUsers(): Boolean {
        return false
    }

    override fun isShowToolBar(): Boolean {
        return false
    }
}