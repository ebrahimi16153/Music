package com.github.ebrahimi16153.music.spalsh

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.motion.widget.MotionLayout
import com.github.ebrahimi16153.music.databinding.ActivitySplashBinding
import com.github.ebrahimi16153.music.musiclist.MusicList

class Splash : AppCompatActivity(), MotionLayout.TransitionListener {

    //binding
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // motion layout Listener
        binding.root.addTransitionListener(this)



    }
    override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}
    override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {}
    override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            goMusicLit()

        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 2723)
        }

    }
    override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 2723 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            goMusicLit()
        }
    }


    private fun goMusicLit() {

        startActivity(Intent(this, MusicList::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right)
        finish()
    }


}