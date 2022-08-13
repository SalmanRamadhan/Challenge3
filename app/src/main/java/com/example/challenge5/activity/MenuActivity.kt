package com.example.challenge5.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.challenge5.R
import com.example.challenge5.databinding.ActivityMenuBinding
import com.google.android.material.snackbar.Snackbar

class MenuActivity : AppCompatActivity() {

    companion object {
        const val userName = "userName"
        const val choice = "choice"

    }
    var name = ""
    private var  binding: ActivityMenuBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportActionBar?.hide()
        val nameFromLanding = intent.getStringExtra(userName)
        if (nameFromLanding != null) {
            name = nameFromLanding
        }

    }

    override fun onStart() {
        super.onStart()

        screenPreparation()
        binding?.ivPilihan2?.setOnClickListener {
            navigationPlay(false)
        }
        binding?.tvPilihan2?.setOnClickListener {
            navigationPlay(false)
        }
        binding?.ivPilihan1?.setOnClickListener {
            navigationPlay(true)
        }
        binding?.tvPilihan1?.setOnClickListener {
            navigationPlay(true)
        }
    }

    private fun screenPreparation(){
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

    private fun navigationPlay( isAgainstPlayer: Boolean){
        val intent = Intent(this@MenuActivity, PlayActivity::class.java)
        val bundle = Bundle()
        bundle.putString(userName, name)
        bundle.putBoolean(choice, isAgainstPlayer)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString(userName, name )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        name = savedInstanceState.getString(userName).toString()
    }


}