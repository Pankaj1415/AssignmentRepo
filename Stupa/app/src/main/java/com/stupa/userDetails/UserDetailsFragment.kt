package com.stupa.userDetails

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.stupa.R
import com.stupa.base.BaseFragment
import com.stupa.databinding.UserDetailsFragmentBinding
import org.koin.android.ext.android.inject

class UserDetailsFragment : BaseFragment() {

    private val userDetailsViewModel: UserDetailsViewModel by inject()
    private lateinit var tabList: ArrayList<String>
    private lateinit var binding: UserDetailsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.user_details_fragment, container, false
        )
        userDetailsViewModelObserver()

        tabList = ArrayList()
        tabList.clear()
        tabList.add("Home")
        tabList.add("tab 2")
        tabList.add("tab 3")
        tabList.add("tab 4")
        tabList.add("tab 5")
        // Replace with your fragment instances
        val adapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Set tab text or icon here if needed
            tab.text = tabList[position]
        }.attach()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupBackPressListener()

    }

    private fun userDetailsViewModelObserver() {
        binding.myUserDetailsViewModel = userDetailsViewModel
        binding.lifecycleOwner = this


    }

    private fun setupBackPressListener() {
        this.requireView().isFocusableInTouchMode = true
        this.requireView().requestFocus()
        this.requireView().setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                requireActivity().finish()
                return@OnKeyListener true
            }
            false
        })
    }


}