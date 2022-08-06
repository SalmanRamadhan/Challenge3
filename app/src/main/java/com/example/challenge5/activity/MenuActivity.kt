package com.example.challenge5.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challenge5.R
import com.example.challenge5.databinding.ActivityMenuBinding

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

        val data = intent.getStringExtra(nameFromLanding)

        binding?.let {
          //  it.tvPilihan1.text = data.plus(getString(R.string.vs_pemain))
          //  it.tvPilihan2.text = data.plus(getString(R.string.vs_cpu))
            it.tvPilihan1.setText(getString(R.string.vs_pemain,data))
            it.tvPilihan2.setText(getString(R.string.vs_cpu,data))
        }
    }
}