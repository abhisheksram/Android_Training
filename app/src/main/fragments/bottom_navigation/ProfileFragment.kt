package com.example.fragments.bottom_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.fragments.fragments.Constants
import com.example.fragments.R

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSubmit = view.findViewById<Button>(R.id.btnProfileFrag)
        val textView = view.findViewById<TextView>(R.id.tvProfileFrag)

        btnSubmit.setOnClickListener{

            val editText = view.findViewById<EditText>(R.id.etProfileFrag)
            val text = editText.text.toString()

            val bundle =Bundle()
            bundle.putString(Constants.Nav.profile, text)

            val navController: NavController = Navigation.findNavController(view)
            navController.navigate(R.id.action_navigation_profile_to_navigation_home, bundle)

        }

        val args = this.arguments
        val recData = args?.getString(Constants.Nav.orders)
        if (recData.isNullOrEmpty()){
            textView.text = getString(R.string.title_profile)}

        else {textView.text = recData}    }


}