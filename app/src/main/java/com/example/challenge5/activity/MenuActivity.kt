package com.example.challenge5.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.ActionBarContextView
import com.example.challenge5.R
import com.example.challenge5.databinding.ActivityMenuBinding
import com.google.android.material.snackbar.Snackbar

class MenuActivity : AppCompatActivity() {

    companion object {
        const val nameFromLanding = "nameFromLanding"
    }

    var binding: ActivityMenuBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

        val name = intent.getStringExtra(nameFromLanding)
        screenPreparation(name)

    }

    private fun screenPreparation(name: String?){
        binding?.let {

            it.tvPilihan1.text = getString(R.string.vs_pemain, name)
            it.tvPilihan2.text = getString(R.string.vs_cpu, name)

            val snackBar = Snackbar.make(
                it.tvPilihan2,
                getString(R.string.welcome, name),
                Snackbar.LENGTH_INDEFINITE
            )
            snackBar.setAction(getString(R.string.tutup)) {
                snackBar.dismiss()
            }
            snackBar.show()
        }
    }
}