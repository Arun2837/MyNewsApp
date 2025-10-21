package com.gurugram.mynews.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gurugram.mynews.R
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat


class OtpFragment : Fragment() {

    private lateinit var otpBoxes: List<EditText>
    private lateinit var btnContinue: Button

    private lateinit var tvResendCode: TextView

    private lateinit var timerText: TextView

    private var countDownTimer: CountDownTimer? = null

    private val timerDuration: Long = 30000

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_otp, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        otpBoxes = listOf(
            view.findViewById(R.id.otp1),
            view.findViewById(R.id.otp2),
            view.findViewById(R.id.otp3),
            view.findViewById(R.id.otp4),
            view.findViewById(R.id.otp5),
            view.findViewById(R.id.otp6)
        )

        btnContinue = view.findViewById(R.id.otp_continue_btn)
        tvResendCode = view.findViewById(R.id.send_code)
        timerText = view.findViewById(R.id.otp_timer)
        val userNumber = view.findViewById<TextView>(R.id.user_number)


        val etMobile = arguments?.getString("MOBILE_NUMBER")
        if (etMobile != null) {
            Log.d("OtpFragment", "Received Mobile Number: $etMobile")

        userNumber.text = getString(R.string.number_at,etMobile)
        }
        else {
            Log.e("OtpFragment", "Mobile number not received!")
        }

        clearOtpBoxes()
        setupOtpBoxes()
        startTimer()

        tvResendCode.setOnClickListener {
            if (tvResendCode.isEnabled) {
                clearOtpBoxes()
                startTimer()
            }

        }

        btnContinue.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, UserNameFragment())
                .addToBackStack(null)
                .commit()

        }
    }

    private fun setupOtpBoxes() {
        otpBoxes.forEachIndexed { index, editText ->


            editText.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    //editText.isActivated = s?.isNotEmpty() == true

                    if (s?.isNotEmpty() == true) {
                        editText.isActivated = true   // ✅ white background
                    } else {
                        editText.isActivated = false  // ✅ grey background
                    }

                    if (s?.length == 1) {
                        if (index < otpBoxes.size - 1) {
                            otpBoxes[index + 1].isEnabled = true
                            otpBoxes[index + 1].alpha = 1.0f
                            otpBoxes[index + 1].requestFocus()
                        }
                    } else if (s?.isEmpty() == true) {
                        if (index > 0) {
                            //otpBoxes[index].isEnabled = false
                            //otpBoxes[index].alpha = 0.5f
                            otpBoxes[index - 1].requestFocus()
                        }
                    }
                    val allFilled = otpBoxes.all { it.text?.length == 1 }
                    if (allFilled) {
                        btnContinue.isEnabled = true
                        //btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.light_green))
                    } else {
                        btnContinue.isEnabled = false
                        //btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.light_grey))
                    }
                }

            })

        }


    }

    private fun clearOtpBoxes() {
        otpBoxes.forEachIndexed { index, editText ->
            editText.setText("")
            editText.hint = ""
            editText.isActivated = false
            editText.isEnabled = false
            editText.alpha = 0.5f
        }

        otpBoxes[0].isEnabled = true
        otpBoxes[0].alpha = 1.0f
        otpBoxes[0].requestFocus()

        btnContinue.isEnabled = false
        //btnContinue.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.light_grey))
    }

    private fun startTimer() {
        tvResendCode.isEnabled = false
        tvResendCode.setTextColor(ContextCompat.getColor(requireContext(), R.color.dark_grey))

        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(timerDuration, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val secondLeft = millisUntilFinished / 1000
                timerText.text = getString(R.string.otp_timer1,secondLeft)
            }


            override fun onFinish() {
                tvResendCode.isEnabled = true
                tvResendCode.setTextColor(ContextCompat.getColor(requireContext(),R.color.text_red))
                timerText.text = ""
            }

        }.start()

    }

    }