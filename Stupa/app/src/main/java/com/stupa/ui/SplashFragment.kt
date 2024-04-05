package com.stupa.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.stupa.R
import com.stupa.base.BaseFragment
import com.stupa.constants.Constants
import com.stupa.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSplashBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_splash, container, false
        )


        Handler(Looper.getMainLooper()).postDelayed({

            if (sharedPreference.getString(Constants.USER_NAME) != null) {
//
//                val action = SplashFragmentDirections.actionSplashFragmentToUserDetailsFragment()
//                NavHostFragment.findNavController(this).navigate(action)
            } else {

                            navigateToLogin()
            }

        }, 500)
        return binding.root

    }

    private fun navigateToLogin() {
        Log.i("MYTAG", "insidisplayUsersList")

//        val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
//        NavHostFragment.findNavController(this).navigate(action)

    }

}