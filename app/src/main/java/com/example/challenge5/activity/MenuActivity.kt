package com.example.challenge5.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge5.R
import com.example.challenge5.databinding.ActivityMenuBinding
import com.google.android.material.snackbar.Snackbar

class MenuActivity : AppCompatActivity() {

    companion object {
        const val userName = "userName"
        const val choice = "choice"

    }

    private var  binding: ActivityMenuBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()

    }

    override fun onStart() {
        super.onStart()
        val name = intent.getStringExtra(userName)
        screenPreparation(name)
        binding?.ivPilihan2?.setOnClickListener {
            navigationPlay(name,false)
        }
        binding?.tvPilihan2?.setOnClickListener {
            navigationPlay(name,false)
        }
        binding?.ivPilihan1?.setOnClickListener {
            navigationPlay(name,true)
        }
        binding?.tvPilihan1?.setOnClickListener {
            navigationPlay(name,true)
        }
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

    private fun navigationPlay(name: String?, isAgainstPlayer: Boolean){
        val intent = Intent(this@MenuActivity, PlayActivity::class.java)
        intent.putExtra(userName, name)
        intent.putExtra(choice, isAgainstPlayer)
        startActivity(intent)
    }


}