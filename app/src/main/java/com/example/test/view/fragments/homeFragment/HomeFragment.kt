package com.example.test.view.fragments.homeFragment

import android.os.Bundle
import android.view.View
import com.example.test.R
import com.example.test.adapter.HomePagerAdapter
import com.example.test.adapter.SAVED_USER_PAGE
import com.example.test.adapter.USER_LIST_PAGE
import com.example.test.databinding.FragmentHomeBinding
import com.example.test.view.fragments.baseFragment.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.IndexOutOfBoundsException

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun isShowToolBar(): Boolean {
        return false
    }

    private fun initView(){
        initViewPager()
        initTabLayout()
    }

    private fun initViewPager() {
        binding.viewPager.adapter = HomePagerAdapter(this)
    }

    private fun initTabLayout() {
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

    private fun getTabTitle(position: Int): String {
        return when (position) {
            USER_LIST_PAGE -> getString(R.string.users)
            SAVED_USER_PAGE-> getString(R.string.saved_users)
            else -> throw IndexOutOfBoundsException()
        }
    }

}