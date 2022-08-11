package com.example.challenge5.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.challenge5.R
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
    private var binding: ActivityPlayBinding? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        loadImage()
        supportActionBar?.hide()

        val name = intent.getStringExtra(userName)

        binding?.let {
            it.tvPlayer1.text = name
            it.tvPlayer2.setText(R.string.pemain2_cpu)
            it.ivExit.setOnClickListener {
                val intent = Intent(this@PlayActivity, MenuActivity::class.java)
                intent.putExtra(userName, name)
                startActivity(intent)
            }
        }

        binding?.ivBatu?.setOnClickListener {
            while (!isPlay) {
                playCPU(BATU, randomCPU(),name)
                isPlay = true
            }
        }

        binding?.ivKertas?.setOnClickListener {
            while (!isPlay) {
                playCPU(KERTAS, randomCPU(),name)
                isPlay = true
            }
        }

        binding?.ivGunting?.setOnClickListener {
            while (!isPlay) {
                playCPU(GUNTING, randomCPU(),name)
                isPlay = true
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


    private fun playCPU(p1: Int, p2: Int, name: String?) {

        when (p1) {
            1 -> {
                binding?.ivBatu?.setBackgroundResource(R.drawable.shape_background)
                Toast.makeText(
                    this@PlayActivity,
                    getString(R.string.toast_choice_batu, name),
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("User Input", "User menginputkan batu")

            }
            2 -> {binding?.ivKertas?.setBackgroundResource(R.drawable.shape_background)
                Toast.makeText(
                    this@PlayActivity,
                    getString(R.string.toast_choice_kertas, name),
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("User Input", "User menginputkan kertas")
            }
            3 -> {binding?.ivGunting?.setBackgroundResource(R.drawable.shape_background)
                Toast.makeText(
                    this@PlayActivity,
                    getString(R.string.toast_choice_gunting, name),
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("User Input", "User menginputkan gunting")

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
            else -> lost()
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

    private fun lost() {
        binding?.tvResult?.apply {
            text = getString(R.string.result_com_menang)
            setTextColor(ContextCompat.getColor(context, R.color.white))
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
            setBackgroundColor(ContextCompat.getColor(context, R.color.green_result))
        }
    }

    private fun reset() {
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


}