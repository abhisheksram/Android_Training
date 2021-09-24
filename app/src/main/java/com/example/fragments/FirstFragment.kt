package com.example.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class FirstFragment : Fragment(R.layout.fragment_first) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnFirstFragment = view.findViewById<Button>(R.id.btnSubmitFirst)
        val textView1 = view.findViewById<TextView>(R.id.tv1)

        btnFirstFragment.setOnClickListener{

            val editText1 = view.findViewById<EditText>(R.id.et1)
            val text1 = editText1.text.toString()

            val bundle =Bundle()
            bundle.putString(Constants.Pref.fragment1, text1)

            val fragment1 = SecondFragment()
            fragment1.arguments = bundle
            parentFragmentManager.beginTransaction().replace(R.id.container2View,fragment1).commit()

        }
        val args = this.arguments
        val recData = args?.getString(Constants.Keys.fragment2)

        if (recData.equals("")){        textView1.text = "First Fragment"}
        else
            textView1.text = recData
    }


}