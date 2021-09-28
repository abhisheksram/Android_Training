package com.example.fragments.bottom_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.fragments.Constants
import com.example.fragments.R

class OrdersFragment : Fragment(R.layout.fragment_orders) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSubmit = view.findViewById<Button>(R.id.btnOrdersFrag)
        val textView = view.findViewById<TextView>(R.id.tvOrdersFrag)

        btnSubmit.setOnClickListener{

            val editText = view.findViewById<EditText>(R.id.etOrdersFrag)
            val text = editText.text.toString()

            val bundle =Bundle()
            bundle.putString(Constants.Nav.orders, text)

            val navController: NavController = Navigation.findNavController(view)
            navController.navigate(R.id.action_navigation_orders_to_navigation_profile, bundle)

        }

        val args = this.arguments
        val recData = args?.getString(Constants.Nav.search)
        if (recData.isNullOrEmpty()){
            textView.text = getString(R.string.title_orders)}

        else {textView.text = recData}
    }


}