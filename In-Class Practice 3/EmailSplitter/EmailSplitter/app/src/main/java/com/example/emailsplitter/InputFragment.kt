package com.example.emailsplitter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class InputFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val emailInput = view.findViewById<EditText>(R.id.emailInput)
        val button = view.findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val email = emailInput.text.toString()
            val pieces = email.split('@')

            if (pieces.size != 2 || pieces.any(String::isEmpty)) {
                Toast.makeText(
                    requireContext(),
                    "Invalid email!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val username = pieces[0]
                val domain = pieces[1]

                val resultFragment = ResultFragment()

                // Send the results to ResultFragment
                val sentData = Bundle()
                sentData.putString("UsernameKey", username)
                sentData.putString("DomainKey", domain)
                resultFragment.arguments = sentData

                Toast.makeText(
                    requireContext(),
                    "Data was passed!",
                    Toast.LENGTH_SHORT
                ).show()

                // Replace InputFragment with ResultFragment
                val fTrans = parentFragmentManager.beginTransaction()
                fTrans.replace(
                    R.id.fl_frag_container,
                    resultFragment,
                    "ResultFrag_tag"
                )
                fTrans.commit()
            }
        }
    }
}