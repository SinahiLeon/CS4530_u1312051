package com.example.emailsplitter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString("UsernameKey")
        val domain = arguments?.getString("DomainKey")

        val userView = view.findViewById<TextView>(R.id.userView)
        val domainView = view.findViewById<TextView>(R.id.domainView)

        userView.text = username
        domainView.text = domain
    }
}