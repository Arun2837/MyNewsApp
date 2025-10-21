package com.gurugram.mynews.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gurugram.mynews.R
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.gurugram.mynews.utils.Validator


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etMobile = view.findViewById<TextInputEditText>(R.id.etMobile)
        val loginMobileNo = view.findViewById<TextInputLayout>(R.id.login_enter_mobile)
        val otpButton = view.findViewById<Button>(R.id.otp_button)
        val skipText = view.findViewById<TextView>(R.id.login_skip_text)


        val endIconView =
            loginMobileNo.findViewById<ImageView>(com.google.android.material.R.id.text_input_end_icon)
        endIconView.scaleX = 0.5f
        endIconView.scaleY = 0.5f

        loginMobileNo.prefixText = "+91 "

        //otpButton.isEnabled = false
        loginMobileNo.isEndIconVisible = false

        //otpButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.light_grey))

        etMobile.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val input = s?.toString() ?: ""
                //val valid = input.length == 10 && input.all { it.isDigit() }
                //val valid = input.matches(Regex("^[0-9]{10}$"))
                val valid = Validator.isValidMobile(input)

                if (valid) {
                    otpButton.isEnabled = true
                    //otpButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.light_green))
                    loginMobileNo.isEndIconVisible = true
                    loginMobileNo.error = null
                } else {
                    otpButton.isEnabled = false
                    //otpButton.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.light_grey))
                    loginMobileNo.isEndIconVisible = false

                    //if (input.isNotEmpty() && input.length < 10)
                    if (input.isNotEmpty()) {
                        loginMobileNo.error = "Enter 10-digit valid number"
                    } else {
                        loginMobileNo.error = null
                    }
                }


            }

        })
        otpButton.setOnClickListener {

            val etMobile = etMobile.text.toString()

            if (etMobile.isNotEmpty()) {

                Log.d("LoginFragment", "Sending Mobile Number: $etMobile")

                val otpFragment = OtpFragment()
                val bundle = Bundle()
                bundle.putString("MOBILE_NUMBER", etMobile)
                otpFragment.arguments = bundle

                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_container, otpFragment)
                    .addToBackStack(null)
                    .commit()
            } else {
                Toast.makeText(requireContext(), "Enter Mobile Number", Toast.LENGTH_LONG).show()
            }
        }

        skipText.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, HomeFragment())
                .addToBackStack(null)
                .commit()
        }

    }


}