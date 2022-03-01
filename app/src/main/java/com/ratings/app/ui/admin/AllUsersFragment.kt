package com.ratings.app.ui.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ratings.app.R
import com.ratings.app.ui.viewmodels.AdminViewModel
import com.ratings.app.ui.viewmodels.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AllUsersFragment : DaggerFragment(R.layout.fragment_all_users) {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val adminViewModel: AdminViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_all_users, container, false)
        adminViewModel.usersList.observe(viewLifecycleOwner, {
            println(it)
        })
        return view
    }
}