package com.example.loginactivity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation


class LoginFragment : Fragment(R.layout.fragment_login) {

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editUserName = view.findViewById<EditText>(R.id.etLoginUserName)
        val editPassword = view.findViewById<EditText>(R.id.etLoginPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val btnGoToSignUp = view.findViewById<Button>(R.id.btnGoToSignUp)
        val btnForgotPassword = view.findViewById<Button>(R.id.btnForgotPassword)

        btnLogin.setOnClickListener {

            val userName = editUserName.text
            val password = editPassword.text
            val sharedPreferences: SharedPreferences? =
                context?.getSharedPreferences(Constants.SharedPref.myData, Context.MODE_PRIVATE)

            when {
                userName.isNullOrEmpty() -> {
                    Toast.makeText(this.context, "User Name is empty", Toast.LENGTH_SHORT).show()
                }
                password.isNullOrEmpty() -> {
                    Toast.makeText(this.context, "Password is empty", Toast.LENGTH_SHORT).show()
                }
                sharedPreferences?.getString("UserName$userName", "").isNullOrEmpty() -> {
                    Toast.makeText(context, "Username not found", Toast.LENGTH_SHORT).show()
                }
                sharedPreferences?.getString("Password$password", "").isNullOrEmpty() -> {
                    Toast.makeText(context, "Password is incorrect", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(this.context,"You have success fully Logged in", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this.context, WidgetActivity::class.java)
                    startActivity(intent)

                    (requireActivity() as AuthNavigationActivity).finish()

                }
            }
        }

        btnGoToSignUp.setOnClickListener {

            val navController: NavController = Navigation.findNavController(view)
            navController.navigate(R.id.action_loginFragment_to_signUPFragment)
            navController.backStack

        }

        btnForgotPassword.setOnClickListener {

            val navController: NavController = Navigation.findNavController(view)
            navController.navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
            navController.backStack

        }
    }
}