package com.stupa.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.hbb20.CountryCodePicker
import com.hbb20.CountryCodePicker.OnCountryChangeListener
import com.stupa.R
import com.stupa.base.BaseFragment
import com.stupa.databinding.RegisterHomeFragmentBinding
import org.koin.android.ext.android.inject


class RegisterFragment : BaseFragment() {

    private val registerViewModel: RegisterViewModel by inject()
    private lateinit var binding: RegisterHomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.register_home_fragment, container, false
        )
        edtTextChangeListener()
        registerViewModelObserver()
        return binding.root
    }

    private fun registerViewModelObserver() {
        binding.lifecycleOwner = this
        binding.myViewModel = registerViewModel
        registerViewModel.inputCountryCode = binding.countryCode.selectedCountryCode
        registerViewModel.navigateto.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished == true) {
                displayUsersList()
                registerViewModel.doneNavigating()
            }
        }

        registerViewModel.userDetailsLiveData.observe(viewLifecycleOwner) {
            Log.i("MYTAG", it.toString() + "000000000000000000000000")
        }


        registerViewModel.errorToastName.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {
                binding.edtNameLayout.error = getString(R.string.error_please_enter_your_name)
                registerViewModel.doneToastName()
            }
        }
        registerViewModel.errorToastPhone.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {

                binding.edtPhoneLayout.error =
                    getString(R.string.error_please_enter_your_phone_number)
                registerViewModel.doneToastPhone()
            }
        }
        registerViewModel.errorToastPhoneLength.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {

                binding.edtPhoneLayout.error =
                    getString(R.string.error_please_enter_your_phone_number_length)
                registerViewModel.doneToastPhoneLength()
            }
        }
        registerViewModel.errorToastEmail.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {
                binding.edtEmailLayout.error = getString(R.string.error_please_enter_your_email)

                registerViewModel.doneToastEmail()
            }
        }
        registerViewModel.errotoastUsernameInvalid.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {
                binding.edtEmailLayout.error =
                    getString(R.string.error_please_enter_your_valid_email)
                registerViewModel.donetoastErrorUsernameInvalid()
            }
        }
        registerViewModel.errorToastPassword.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {
                binding.edtPasswordLayout.error =
                    getString(R.string.error_please_enter_your_password)

                registerViewModel.doneToastPassword()
            }
        }
        registerViewModel.errorToastUsername.observe(viewLifecycleOwner) { hasError ->
            if (hasError == true) {
                binding.edtEmailLayout.error = getString(R.string.error_email_already_exits)

                registerViewModel.donetoastUserName()
            }
        }
    }

    private fun edtTextChangeListener() {
        binding.countryCode.setBackgroundResource(R.drawable.bg_country_code_active)
        binding.edtName.addTextChangedListener { it ->
            if (it != null) {
                if (it.isNotEmpty()) {
                    binding.edtNameLayout.error = ""
                }
            }
        }
        binding.edtPhone.addTextChangedListener { it ->
            if (it != null) {
                if (it.isNotEmpty()) {
                    binding.edtPhoneLayout.error = ""
                }
            }
        }
        binding.edtEmail.addTextChangedListener { it ->
            if (it != null) {
                if (it.isNotEmpty()) {
                    binding.edtEmailLayout.error = ""
                }
            }
        }
        binding.edtPassword.addTextChangedListener { it ->
            if (it != null) {
                if (it.isNotEmpty()) {
                    binding.edtPasswordLayout.error = ""
                }
            }
        }
    }

    private fun displayUsersList() {
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }


}