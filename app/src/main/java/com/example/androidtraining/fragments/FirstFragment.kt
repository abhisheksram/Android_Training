package com.example.androidtraining.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.androidtraining.constants.Constants
import com.example.androidtraining.R


class FirstFragment : Fragment(R.layout.fragment_first) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnFirstFragment = view.findViewById<Button>(R.id.btnFrag1)

        btnFirstFragment.setOnClickListener{

            val editText = view.findViewById<EditText>(R.id.etFrag1)
            val text1 = editText.text.toString()

            val bundle =Bundle()
                bundle.putString(Constants.Pref.fragment1, text1)

            val navController: NavController = Navigation.findNavController(view)
            navController.navigate(R.id.action_firstFragment_to_secondFragment, bundle)

        }

    }


}