package com.github.ebrahimi16153.music.spalsh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.ebrahimi16153.music.databinding.ActivitySplashBinding
import com.github.ebrahimi16153.music.databinding.RowMusicListBinding

class Splash : AppCompatActivity() {
    lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}