package com.gurugram.mynews
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gurugram.mynews.fragment.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, LoginFragment())
                .commit()
        }
    }
}






































/*...........................LoginActivityCode...............................................

        val etMobile = findViewById<TextInputEditText>(R.id.etMobile)
        val loginMobileNo = findViewById<TextInputLayout>(R.id.login_enter_mobile)
        val otpButton = findViewById<MaterialButton>(R.id.otp_button)


        val endIconView = loginMobileNo.findViewById<ImageView>(com.google.android.material.R.id.text_input_end_icon)
        endIconView.scaleX = 0.5f
        endIconView.scaleY = 0.5f

        loginMobileNo.prefixText = "+91 "


        otpButton.isEnabled = false
        loginMobileNo.isEndIconVisible = false
        otpButton.setBackgroundColor(ContextCompat.getColor(this,R.color.light_grey))

        etMobile.addTextChangedListener(object : TextWatcher{

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val input = s?.toString() ?: ""
                //val valid = input.length == 10 && input.all { it.isDigit() }
                //val valid = input.matches(Regex("^[0-9]{10}$"))
                val valid = Validator.isValidMobile(input)

                if(valid){
                    otpButton.isEnabled = true
                    otpButton.setBackgroundColor(ContextCompat.getColor(this@MainActivity,R.color.light_green))
                    loginMobileNo.isEndIconVisible = true
                    loginMobileNo.error = null
                }
                else{
                    otpButton.isEnabled = false
                    otpButton.setBackgroundColor(ContextCompat.getColor(this@MainActivity,R.color.light_grey))
                    loginMobileNo.isEndIconVisible = false

                    //if (input.isNotEmpty() && input.length < 10)
                    if (input.isNotEmpty())
                    {
                        loginMobileNo.error = "Enter 10-digit valid number"
                    } else {
                        loginMobileNo.error = null
                    }
                }


            }

        })

        otpButton.setOnClickListener {

            val mobile = etMobile.text.toString().trim()
            //if(mobile.matches(Regex("^[0-9]{10}$"))) {
                val intent = Intent(this, OtpActivity::class.java)
                intent.putExtra("MOBILE_NUMBER", mobile)
                startActivity(intent)
            //}
            //else {
             //   loginMobileNo.error = "Please enter valid 10-digit number"
              //  Toast.makeText(this, "Invalid mobile number!", Toast.LENGTH_LONG).show()
            //}

        }

    }
}



............................OTPActivityCode..................................
package com.gurugram.mynews

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.gurugram.mynews.fragment.UserNameFragment

class OtpActivity : AppCompatActivity() {
    private lateinit var otpBoxes: List<EditText>
    private lateinit var btnContinue: MaterialButton

    private lateinit var tvResendCode: TextView

    private lateinit var timerText: TextView

    private var countDownTimer: CountDownTimer? = null

    private val timerDuration: Long = 30000

    //private val interval = 1_000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_otp_layout)

        otpBoxes = listOf(
            findViewById(R.id.otp1),
            findViewById(R.id.otp2),
            findViewById(R.id.otp3),
            findViewById(R.id.otp4),
            findViewById(R.id.otp5),
            findViewById(R.id.otp6)
        )

        //otpBoxes.forEach {
        //    it.isEnabled = false
        //    it.alpha = 0.5f
        //}

        //otpBoxes[0].isEnabled = true
        //otpBoxes[0].alpha = 1.0f

        btnContinue = findViewById(R.id.continue_btn)
        tvResendCode = findViewById(R.id.send_code)
        timerText = findViewById(R.id.otp_timer)

        val mobileNumber = intent.getStringExtra("MOBILE_NUMBER")
        val userNumber = findViewById<TextView>(R.id.user_number)

        userNumber.text = getString(R.string.number_at,mobileNumber)

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
            val fragment = UserNameFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.verify_name_container,fragment)
                .commit()
        }

    }

    // btnContinue.isEnabled = false
    // btnContinue.setBackgroundColor(ContextCompat.getColor(this,R.color.light_grey))

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
                        btnContinue.setBackgroundColor(
                            ContextCompat.getColor(
                                this@OtpActivity,
                                R.color.light_green
                            )
                        )
                    } else {
                        btnContinue.isEnabled = false
                        btnContinue.setBackgroundColor(
                            ContextCompat.getColor(
                                this@OtpActivity,
                                R.color.light_grey
                            )
                        )
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
        btnContinue.setBackgroundColor(
            ContextCompat.getColor(this, R.color.light_grey)
        )
    }

    private fun startTimer() {
        tvResendCode.isEnabled = false
        tvResendCode.setTextColor(ContextCompat.getColor(this, R.color.dark_grey))

        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(timerDuration, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val secondLeft = millisUntilFinished / 1000
                timerText.text = getString(R.string.otp_timer1,secondLeft)
            }


            override fun onFinish() {
                tvResendCode.isEnabled = true
                tvResendCode.setTextColor(
                    ContextCompat.getColor(
                        this@OtpActivity,
                        R.color.text_red
                    )
                )
                timerText.text = ""
            }

        }.start()

    }


}

 */