package com.example.test.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test.view.fragments.homeTabs.SavedUserListFragment
import com.example.test.view.fragments.homeTabs.UserListFragment

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentsCreator: Map<Int, () -> Fragment> = mapOf(
        USER_LIST_PAGE to { UserListFragment() },
        SAVED_USER_PAGE to { SavedUserListFragment() }
    )

    override fun getItemCount() = fragmentsCreator.size

    override fun createFragment(position: Int): Fragment {
        return fragmentsCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}

const val USER_LIST_PAGE = 0
const val SAVED_USER_PAGE = 1
