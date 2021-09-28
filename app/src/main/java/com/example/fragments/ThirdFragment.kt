package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation

class ThirdFragment : Fragment(R.layout.fragment_third) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnThirdFragment = view.findViewById<Button>(R.id.btnFrag3)
        val textView31 = view.findViewById<TextView>(R.id.tvFrag31)
        val textView32 = view.findViewById<TextView>(R.id.tvFrag32)

        btnThirdFragment.setOnClickListener{

            val navController: NavController = Navigation.findNavController(view)
            navController.navigate(R.id.action_thirdFragment_to_firstFragment)

        }

        val args = this.arguments
        textView31.text = args?.getString(Constants.Pref.fragment1)
        textView32.text = args?.getString(Constants.Pref.fragment2)

    }


}
