package com.github.ebrahimi16153.music.playingnow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.ebrahimi16153.music.databinding.ActivityPlayingNowBinding

class PlayingNow : AppCompatActivity() {
    // binding
    private lateinit var binding: ActivityPlayingNowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayingNowBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}