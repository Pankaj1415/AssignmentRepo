package com.stupa.base

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.stupa.util.SharedPreference
import org.koin.android.ext.android.inject

open class BaseFragment : Fragment(), View.OnClickListener {

    val sharedPreference: SharedPreference by inject()
//    val connectionManager: ConnectionManager by inject()

    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
    

    fun showShortToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


    override fun onClick(v: View?) {
    }


    fun closeKeyboard() {
        val view = requireView()
        if (view != null) {
            val imm =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}