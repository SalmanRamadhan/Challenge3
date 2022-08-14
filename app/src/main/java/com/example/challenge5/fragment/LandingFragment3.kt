package com.example.challenge5.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast

import com.example.challenge5.R

import com.example.challenge5.activity.MenuActivity
import com.example.challenge5.activity.MenuActivity.Companion.userName
import com.example.challenge5.databinding.FragmentLanding3Binding

class LandingFragment3 : Fragment() {

    private var binding: FragmentLanding3Binding? = null

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