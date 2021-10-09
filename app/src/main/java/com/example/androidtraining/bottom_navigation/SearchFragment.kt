package com.example.androidtraining.bottom_navigation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.androidtraining.constants.Constants
import com.example.androidtraining.R


class SearchFragment : Fragment(R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSubmit = view.findViewById<Button>(R.id.btnSearchFrag)
        val textView = view.findViewById<TextView>(R.id.tvSearchFrag)

        btnSubmit.setOnClickListener{

            val editText = view.findViewById<EditText>(R.id.etSearchFrag)
            val text = editText.text.toString()

            val bundle =Bundle()
            bundle.putString(Constants.Nav.search, text)

            val navController: NavController = Navigation.findNavController(view)
            navController.navigate(R.id.action_navigation_search_to_navigation_orders, bundle)

        }

        val args = this.arguments
        val recData = args?.getString(Constants.Nav.home)
        if (recData.isNullOrEmpty()){
            textView.text = getString(R.string.title_search)}

        else {textView.text = recData}
    }


}