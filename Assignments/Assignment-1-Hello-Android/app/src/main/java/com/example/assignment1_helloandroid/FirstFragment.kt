package com.example.assignment1_helloandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assignment1_helloandroid.databinding.FragmentFirstBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first, container, false)
        val binding = FragmentFirstBinding.inflate(inflater, container, false)
        // navigate to second fragment
        binding.button.setOnClickListener {
            val myFrag = SecondFragment()
            val sentData = Bundle()
            // key="Button clicked" value=button.text
            sentData.putString("Button clicked", binding.button.text.toString())
            myFrag.arguments = sentData

            val transFrag = parentFragmentManager.beginTransaction()
            transFrag.replace(R.id.fragmentContainerView, myFrag, "tag")
            transFrag.addToBackStack(null)
            transFrag.commit()
        }

        // Bind pink button
        binding.pinkButton.setOnClickListener{
            val myFrag = SecondFragment()
            val sentData = Bundle()
            sentData.putString(
                "Button clicked", binding.pinkButton.text.toString()
            )
            myFrag.arguments = sentData

            val transFrag = parentFragmentManager.beginTransaction()
            transFrag.replace(R.id.fragmentContainerView, myFrag, "tag")
            transFrag.addToBackStack(null)
            transFrag.commit()
        }

        // Bind cyan button
        binding.cyanButton.setOnClickListener {
            val myFrag = SecondFragment()
            val sentData = Bundle()
            sentData.putString (
                "Button clicked", binding.cyanButton.text.toString()
            )
            myFrag.arguments = sentData

            val transFrag = parentFragmentManager.beginTransaction()
            transFrag.replace(R.id.fragmentContainerView, myFrag, "tag")
            transFrag.addToBackStack(null)
            transFrag.commit()
        }

        // Bind peach button
        binding.peachButton.setOnClickListener{
            val myFrag = SecondFragment()
            val sentData = Bundle()
            sentData.putString(
                "Button clicked", binding.peachButton.text.toString()
            )
            myFrag.arguments = sentData

            val transFrag = parentFragmentManager.beginTransaction()
            transFrag.replace(R.id.fragmentContainerView, myFrag, "tag")
            transFrag.addToBackStack(null)
            transFrag.commit()
        }

        // Bind sage button
        binding.sageButton.setOnClickListener {
            val myFrag = SecondFragment()
            val sentData = Bundle()
            sentData.putString (
                "Button clicked", binding.sageButton.text.toString()
            )
            myFrag.arguments = sentData

            val transFrag = parentFragmentManager.beginTransaction()
            transFrag.replace(R.id.fragmentContainerView, myFrag, "tag")
            transFrag.addToBackStack(null)
            transFrag.commit()
        }

        // Bind lilac button
        binding.lilacButton.setOnClickListener {
            val myFrag = SecondFragment()
            val sentData = Bundle()
            sentData.putString(
                "Button clicked", binding.lilacButton.text.toString()
            )
            myFrag.arguments = sentData

            val transFrag = parentFragmentManager.beginTransaction()
            transFrag.replace(R.id.fragmentContainerView, myFrag, "tag")
            transFrag.addToBackStack(null)
            transFrag.commit()
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic fun newInstance(param1: String, param2: String) =
                FirstFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}