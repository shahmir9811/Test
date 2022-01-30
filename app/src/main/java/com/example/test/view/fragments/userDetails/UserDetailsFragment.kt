package com.example.test.view.fragments.userDetails

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.app.loadByUrl
import com.example.test.app.loadCircleByUrl
import com.example.test.data.model.dbModel.User
import com.example.test.databinding.FragmentUserDetailsBinding
import com.example.test.view.fragments.baseFragment.BaseFragment
import com.example.test.viewModel.UserDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserDetailsFragment : BaseFragment<FragmentUserDetailsBinding>(FragmentUserDetailsBinding::inflate) {

    private val viewModel: UserDetailsViewModel by viewModel { parametersOf(arguments?.getParcelable<User>("user")) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = arguments?.getParcelable<User>("user")

        val isSavedUser = arguments?.getBoolean("isSavedUser")

        if(isSavedUser == true){
            binding.saveUser.visibility = View.GONE
            binding.removeUser.setOnClickListener {
                viewModel.removeUser()
                findNavController().navigateUp()
            }
        }else{
            binding.removeUser.visibility = View.GONE
            binding.saveUser.setOnClickListener {
                viewModel.saveUser()
                findNavController().navigateUp()
            }
        }


        user?.apply {
            binding.userImageview.loadCircleByUrl(pictureUrl)
            binding.nameTv.text = name
            binding.genderPhoneTv.text = "$gender $phoneNumber"
            binding.countryTv.text = country
            binding.addressTv.text = address
        }
    }

    override fun isShowToolBar(): Boolean {
        return true
    }
}