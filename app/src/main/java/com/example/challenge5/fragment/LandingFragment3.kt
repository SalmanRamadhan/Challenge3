package com.example.challenge5.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.text.trimmedLength
import com.example.challenge5.R
import com.example.challenge5.activity.LandingActivity
import com.example.challenge5.activity.MainActivity
import com.example.challenge5.activity.MenuActivity
import com.example.challenge5.activity.MenuActivity.Companion.userName
import com.example.challenge5.databinding.FragmentLanding3Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LandingFragment3.newInstance] factory method to
 * create an instance of this fragment.
 */
class LandingFragment3 : Fragment() {
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

    var binding: FragmentLanding3Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLanding3Binding.inflate(inflater, container, false)
        return binding?.root
    }

    fun goToMenuActivity() {

        if (binding?.etLandingName?.text.toString().trim().isNotEmpty()) {
            val intent = Intent(activity, MenuActivity::class.java)
            intent.putExtra(userName, binding?.etLandingName?.text.toString())
            startActivity(intent)
            activity?.finish()
        } else {
            Toast.makeText(activity, getString(R.string.warning_name_empty), Toast.LENGTH_SHORT)
                .show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LandingFragment3.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LandingFragment3().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTextValidation()

    }

    private fun editTextValidation() {
        binding?.etLandingName?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                goToMenuActivity()
            }
            true
        }
    }


}