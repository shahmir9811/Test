package com.example.test.view.fragments.homeTabs

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.R
import com.example.test.adapter.UserListAdapter
import com.example.test.app.toast
import com.example.test.data.model.dbModel.User
import com.example.test.data.model.dbModel.Users
import com.example.test.view.fragments.baseFragment.BaseFragment
import com.example.test.viewModel.UserListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.test.data.repository.Result
import com.example.test.databinding.FragmentUserListBinding
import androidx.recyclerview.widget.RecyclerView

private const val FIRST_PAGE = 1

abstract class BaseUserListFragment : BaseFragment<FragmentUserListBinding>(FragmentUserListBinding::inflate){

    private val viewModel: UserListViewModel by viewModel()

    private var page: Int = 0

    private lateinit var adapter: UserListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        initAdapter()
        initSearchView()

        if(isShowSavedUsers()){
            observeLocalData()
        }else {
            loadNextPage()
            observeNetworkData()
        }
    }

    private fun onItemClicked(user: User) {
        val bundle = Bundle()
        bundle.putParcelable("user", user)
        bundle.putBoolean("isSavedUser", isShowSavedUsers())

        findNavController().navigate(R.id.action_global_userDetailsFragment, bundle)
    }

    private var loading = false
    private var pastVisiblesItems: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0

    private lateinit var layoutManager: LinearLayoutManager

    private fun initAdapter() {
        layoutManager = LinearLayoutManager(context)
        binding.recyclerview.layoutManager = layoutManager
        adapter = UserListAdapter(this::onItemClicked)
        binding.recyclerview.adapter = adapter

        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = layoutManager.getChildCount()
                    totalItemCount = layoutManager.getItemCount()
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()
                    if (!loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loadNextPage()
                        }
                    }
                }
            }
        })

    }

    private fun configureAdapter(users: Users){
        if(isShowSavedUsers()){
               adapter.setUsers(users)
        }else{
            if (users.isNotEmpty()) {
                adapter.addUsers(users)
            }
        }
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { search(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { search(it) }
                return false
            }
        })
    }

    private fun search(query: String) {
        adapter.filterUsers(query)
    }

    private fun observeLocalData(){
        viewModel.apply {
            savedUsers.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Result.Success -> {
                        configureAdapter(result.users)
                    }
                    else -> {

                    }
                }
            }
        }
    }

    private fun observeNetworkData(){
        viewModel.users.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                }
                is Result.Success -> {
                    configureAdapter(result.users)
                    stopLoading()
                }
                is Result.Error -> {
                    if(result.isConnectionError){
                        toast(getString(R.string.network_error))
                    }
                    stopLoading()
                }
            }
        }
    }

    private fun loadNextPage() {
        loading = true
        adapter.setLoading(true)
        viewModel.loadPage(++page)

        if(page == FIRST_PAGE) {
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    private fun stopLoading(){
        binding.progressBar.visibility = View.GONE
        loading = false
        adapter.setLoading(false)
    }

    abstract fun isShowSavedUsers() : Boolean
}