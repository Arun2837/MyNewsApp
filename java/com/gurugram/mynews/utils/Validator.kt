package com.gurugram.mynews.utils

object Validator {

    fun isValidMobile(number: String): Boolean {
        return number.matches(Regex("^[6-9][0-9]{9}$"))  && number.toCharArray().distinct().size > 1


    }

    fun isValidEmail(email: String): Boolean {
        return email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))
    }

    // 🔑 Password Validation (Min 8 chars, at least 1 letter & 1 number)
    fun isValidPassword(password: String): Boolean {
        return password.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"))
    }

    // 🔒 Strong Password Validation (Optional)
    fun isStrongPassword(password: String): Boolean {
        return password.matches(
            Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$")
        )
    }
}