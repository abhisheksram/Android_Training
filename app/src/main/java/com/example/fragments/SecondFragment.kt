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


class SecondFragment : Fragment(R.layout.fragment_second) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSecondFragment = view.findViewById<Button>(R.id.btnSubmitSecond)
        val textView2 = view.findViewById<TextView>(R.id.tv2)

        btnSecondFragment.setOnClickListener{

            val editText2 = view.findViewById<EditText>(R.id.et2)
            val text2 = editText2.text.toString()
            val bundle =Bundle()
            bundle.putString(Constants.Keys.fragment2, text2)

            val fragment2 = FirstFragment()
            fragment2.arguments = bundle
            parentFragmentManager.beginTransaction().replace(R.id.container1View,fragment2).commit()

        }
        val args = this.arguments
        val recData = args?.getString(Constants.Pref.fragment1)

        if (recData.equals("")){        textView2.text = "Second Fragment"}
        else
        textView2.text = recData
    }


}