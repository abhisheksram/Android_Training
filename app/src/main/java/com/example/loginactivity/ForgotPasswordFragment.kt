package com.example.loginactivity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentManager


class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editUserName = view.findViewById<EditText>(R.id.etSignUpUserName)
        val editPassword = view.findViewById<EditText>(R.id.etSignUpPassword)
        val editConfirmPassword = view.findViewById<EditText>(R.id.etConfirmSignUpPassword)
        val btnSignUp = view.findViewById<Button>(R.id.btnSignUp)


        val sharedPreferences: SharedPreferences? =
            context?.getSharedPreferences("myData", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = sharedPreferences?.edit()

        btnSignUp.setOnClickListener {

            val userName = editUserName.text
            val password = editPassword.text
            val confirmPassword = editConfirmPassword.text

            if (userName.isNullOrEmpty() || password.isNullOrEmpty() || confirmPassword.isNullOrEmpty()) {

                Toast.makeText(context, "Please Fill All the Fields", Toast.LENGTH_SHORT).show()
            } else
                if (password.toString() != confirmPassword.toString()) {
                    Toast.makeText(context, "Password not matching", Toast.LENGTH_SHORT).show()
                } else
                    if (sharedPreferences?.getString("UserName$userName", "").isNullOrEmpty()) {
                        Toast.makeText(context, "UserName not found", Toast.LENGTH_SHORT)
                            .show(); } else {

                        editor?.putString("UserName$userName", userName.toString())?.apply()
                        editor?.putString("Password$password", password.toString())?.apply()
                        Toast.makeText(this.context,
                            "$userName is updated successfully",
                            Toast.LENGTH_SHORT).show()
                        val supportFragmentManager: FragmentManager = parentFragmentManager
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.containerView, LoginFragment())
                            .commit()

                    }
        }
    }
}