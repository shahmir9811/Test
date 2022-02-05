package com.example.test.view.fragments.homeTabs

import android.os.Bundle
import android.view.View

class SavedUserListFragment : BaseUserListFragment(){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun isShowSavedUsers(): Boolean {
        return true
    }

    override fun isShowToolBar(): Boolean {
        return false
    }
}