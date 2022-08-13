package com.example.challenge5.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.ImageView
import android.widget.Toast

import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.challenge5.R
import com.example.challenge5.activity.MenuActivity.Companion.choice
import com.example.challenge5.activity.MenuActivity.Companion.userName
import com.example.challenge5.databinding.ActivityPlayBinding
import kotlin.random.Random

class PlayActivity : AppCompatActivity() {

    companion object {

        const val BATU = 1
        const val KERTAS = 2
        const val GUNTING = 3
        const val MAX_RANDOM = 4

    }

    private var isPlay = false
    private var isPlayPlayer2 = false
    private var binding: ActivityPlayBinding? = null
    private var name = ""


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        loadImage()
        supportActionBar?.hide()


        val bundle = intent.extras
        val nameFromMenu = bundle?.getString(userName)
        if (nameFromMenu != null) {
            name = nameFromMenu
        }
        val isAgainstPlayer = bundle?.getBoolean(choice)


        binding?.let {
            it.tvPlayer1.text = name
            it.tvPlayer2.setText(
                if (!isAgainstPlayer!!) {
                    R.string.pemain2_cpu
                } else {
                    R.string.pemain2_player
                }
            )
            it.ivExit.setOnClickListener { finish() }
        }

        binding?.ivBatu?.setOnClickListener {
            toastPilihan(BATU, name)
            when {
                !isPlay && !isAgainstPlayer!! -> {
                    playKGB(BATU, randomCPU(), isAgainstPlayer)
                    isPlay = true
                }
                !isPlay && isAgainstPlayer!! -> {
                    playPlayer(BATU, isAgainstPlayer)
                    isPlayPlayer2 = true
                }
            }
        }

        binding?.ivKertas?.setOnClickListener {
            toastPilihan(KERTAS, name)
            when {
                !isPlay && !isAgainstPlayer!! -> {
                    playKGB(KERTAS, randomCPU(), isAgainstPlayer)
                    isPlay = true
                }
                !isPlay && isAgainstPlayer!! -> {
                    playPlayer(KERTAS, isAgainstPlayer)
                    isPlayPlayer2 = true
                }
            }

        }

        binding?.ivGunting?.setOnClickListener {
            toastPilihan(GUNTING, name)
            when {
                !isPlay && !isAgainstPlayer!! -> {
                    playKGB(GUNTING, randomCPU(), isAgainstPlayer)
                    isPlay = true
                }
                !isPlay && isAgainstPlayer!! -> {
                    playPlayer(GUNTING, isAgainstPlayer)
                    isPlayPlayer2 = true
                }
            }
        }

        binding?.ivReset?.setOnClickListener {
            reset()
        }

    }

    private fun loadImage() {
        val ivLogo = findViewById<ImageView>(R.id.ivLogo)
        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(ivLogo)
    }

    private fun randomCPU(): Int = Random.nextInt(BATU, MAX_RANDOM)

    private fun playPlayer(p1: Int, isAgainstPlayer: Boolean) {
        binding?.ivBatuCom?.setOnClickListener {

            while (!isPlay && isPlayPlayer2) {
                toastPilihan(BATU, getString(R.string.pemain_2))
                playKGB(p1, BATU, isAgainstPlayer)
                isPlay = true
            }
        }
        binding?.ivKertasCom?.setOnClickListener {

            while (!isPlay && isPlayPlayer2) {
                toastPilihan(KERTAS, getString(R.string.pemain_2))
                playKGB(p1, KERTAS, isAgainstPlayer)
                isPlay = true
            }
        }
        binding?.ivGuntingCom?.setOnClickListener {

            while (!isPlay && isPlayPlayer2) {
                toastPilihan(GUNTING, getString(R.string.pemain_2))
                playKGB(p1, GUNTING, isAgainstPlayer)
                isPlay = true
            }
        }
    }


    private fun playKGB(p1: Int, p2: Int, isAgainstPlayer: Boolean) {

        when (p1) {
            1 -> {
                binding?.ivBatu?.setBackgroundResource(R.drawable.shape_background)
            }
            2 -> {
                binding?.ivKertas?.setBackgroundResource(R.drawable.shape_background)
            }
            3 -> {
                binding?.ivGunting?.setBackgroundResource(R.drawable.shape_background)
            }
        }

        when {
            p1 == p2 -> {
                draw()
            }
            p1 == BATU && p2 == GUNTING -> {
                won()
            }
            p1 == KERTAS && p2 == BATU -> {
                won()
            }
            p1 == GUNTING && p2 == KERTAS -> {
                won()
            }
            else -> lost(isAgainstPlayer)
        }

        when (p2) {
            1 -> binding?.ivBatuCom?.setBackgroundResource(R.drawable.shape_background)
            2 -> binding?.ivKertasCom?.setBackgroundResource(R.drawable.shape_background)
            3 -> binding?.ivGuntingCom?.setBackgroundResource(R.drawable.shape_background)
        }

    }

    private fun won() {
        binding?.tvResult?.apply {
            text = getString(R.string.result_text_p1_menang)
            setTextColor(ContextCompat.getColor(context, R.color.white))
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            setBackgroundColor(ContextCompat.getColor(context, R.color.green_result))

        }
    }

    private fun draw() {
        binding?.tvResult?.apply {
            text = getString(R.string.draw)
            setTextColor(ContextCompat.getColor(context, R.color.white))
            setBackgroundColor(ContextCompat.getColor(context, R.color.blue_result))
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 30f)
        }
    }

    private fun lost(isAgainstPlayer: Boolean) {
        binding?.tvResult?.apply {
            text = getString(
                if (isAgainstPlayer) {
                    R.string.result_p2_menang
                } else {
                    R.string.result_com_menang
                }
            )
            setTextColor(ContextCompat.getColor(context, R.color.white))
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            setBackgroundColor(ContextCompat.getColor(context, R.color.green_result))
        }
    }

    private fun reset() {

        isPlayPlayer2 = false
        isPlay = false
        binding?.tvResult?.apply {
            setText(R.string.vs)
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 60f)
            setTextColor(ContextCompat.getColor(context, R.color.red_result))
            setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        }
        binding?.let {
            it.ivKertas.setBackgroundResource(R.color.white)
            it.ivGunting.setBackgroundResource(R.color.white)
            it.ivBatu.setBackgroundResource(R.color.white)
            it.ivKertasCom.setBackgroundResource(R.color.white)
            it.ivGuntingCom.setBackgroundResource(R.color.white)
            it.ivBatuCom.setBackgroundResource(R.color.white)
        }
    }

    private fun toastPilihan(p1: Int, name: String) {
        Toast.makeText(
            this@PlayActivity,
            when (p1) {
                1 -> getString(R.string.toast_choice_batu, name)
                2 -> getString(R.string.toast_choice_kertas, name)
                else -> getString(R.string.toast_choice_gunting, name)
            },
            Toast.LENGTH_SHORT
        ).show()
        Log.d(
            "$name input", "$name menginputkan ${
                when (p1) {
                    1 -> "BATU"
                    2 -> "KERTAS"
                    else -> "GUNTING"
                }
            }"
        )
    }


}