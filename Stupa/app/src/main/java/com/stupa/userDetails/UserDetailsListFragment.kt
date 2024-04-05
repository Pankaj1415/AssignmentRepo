package com.stupa.userDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.stupa.R
import com.stupa.base.BaseFragment
import com.stupa.databinding.FragmentUserDetailsListBinding
import org.koin.android.ext.android.inject

class UserDetailsListFragment : BaseFragment() {

    private val userDetailsViewModel: UserDetailsViewModel by inject()
    private lateinit var binding: FragmentUserDetailsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_details_list, container, false
        )
        initRecyclerView()
    return binding.root
    }

    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this.context)
        displayUsersList()
    }
    private fun displayUsersList() {
        Log.i("MYTAG", "Inside ...UserDetails..Fragment")
        userDetailsViewModel.users.observe(viewLifecycleOwner) {
            binding.usersRecyclerView.adapter = MyRecycleViewAdapter(it)
        }

    }
}