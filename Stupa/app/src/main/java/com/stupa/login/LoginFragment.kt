package com.stupa.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.stupa.R
import com.stupa.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject


class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by inject()
    private lateinit var  binding :FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )
        edtTextChangeListener()
        loginViewModelObserver()
        return binding.root
    }


    private fun loginViewModelObserver() {
        binding.myLoginViewModel = loginViewModel
        binding.lifecycleOwner = this
        loginViewModel.errorToastUsername.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {
                binding.edtEmailLayout.error =  getString(R.string.error_please_enter_your_email)
                loginViewModel.doneToastErrorUsername()
            }
        }
        loginViewModel.errorToastUsernameInvalid.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {
                binding.edtEmailLayout.error =  getString(R.string.error_please_enter_your_valid_email)
                loginViewModel.doneToastErrorUsernameInvalid()
            }
        }
        loginViewModel.errorToastUsernameNotExit.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {
                binding.edtEmailLayout.error =  getString(R.string.error_user_does_not_exist)
                loginViewModel.doneToastUsernameNotExit()
            }
        }
        loginViewModel.errorToastPassword.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {
                binding.edtPasswordLayout.error = getString(R.string.error_please_enter_your_password)
                loginViewModel.doneToastPassword()
            }
        }

        loginViewModel.errorToastInvalidPassword .observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {
                binding.edtPasswordLayout.error =getString(R.string.error_please_check_your_password)
                loginViewModel.doneToastInvalidPassword()
            }
        }
        loginViewModel.navigatetoUserDetails.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished == true) {
                Log.i("MYTAG", "insidi observe")
                navigateUserDetails()
                loginViewModel.doneNavigatingUserDetails()
            }
        }
        loginViewModel.navigatetoRegister.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished == true) {
                displayUsersList()
                loginViewModel.doneNavigatingRegiter()
            }
        }
    }
    private fun displayUsersList() {
        Log.i("MYTAG","insidisplayUsersList")
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }

    private fun navigateUserDetails() {
        Log.i("MYTAG","insidisplayUsersList")
        val action = LoginFragmentDirections.actionLoginFragmentToUserDetailsFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
    private fun edtTextChangeListener() {
        binding.edtEmail.addTextChangedListener {
            if (it != null) {
                if (it.isNotEmpty()) {
                    binding.edtEmailLayout.error = ""
                }
            }
        }
        binding.edtPassword.addTextChangedListener {
            if (it != null) {
                if (it.isNotEmpty()) {
                    binding.edtPasswordLayout.error = ""
                }
            }
        }
    }
}