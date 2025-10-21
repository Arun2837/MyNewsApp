package com.gurugram.mynews.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.gurugram.mynews.R

class UserNameFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_user_name, container, false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val etUse = view.findViewById<TextInputLayout>(R.id.user_text)
        val etUserName = view.findViewById<TextInputEditText>(R.id.edit_user_full_name)
        val tvExample = view.findViewById<TextView>(R.id.eg_user_name)
        val btnContinue = view.findViewById<Button>(R.id.verify_name_button)

        btnContinue.isEnabled = false

        etUserName.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString().trim()
                btnContinue.isEnabled = input.isNotEmpty()
                tvExample.visibility = if (input.isNotEmpty()) View.GONE else View.VISIBLE

            }

        })
    }

}