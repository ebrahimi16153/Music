package com.github.ebrahimi16153.music.helper

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.RecyclerView
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.StaticData


class CustomScrollListener() : RecyclerView.OnScrollListener() {
    private var isUp = false
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        when (newState) {
            RecyclerView.SCROLL_STATE_IDLE -> println("The RecyclerView is not scrolling")
            RecyclerView.SCROLL_STATE_DRAGGING -> println("Scrolling now")
            RecyclerView.SCROLL_STATE_SETTLING -> println("Scroll Settling")
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dx > 0) {
//            println("Scrolled Right")
        } else if (dx < 0) {
//            println("Scrolled Left")
        } else {
//            println("No Horizontal Scrolled")
        }
        if (dy > 20) {
//            println("Scrolled Downwards")
            if (!isUp && StaticData.motionLayout.currentState == R.id.start) {
                StaticData.motionLayout.setTransition(R.id.start, R.id.end)
                StaticData.motionLayout.transitionToEnd()
                isUp = !isUp

            }

        } else if (dy < -20) {
//            println("Scrolled Upwards")
            if (isUp && StaticData.motionLayout.currentState == R.id.end) {
                StaticData.motionLayout.setTransition(R.id.end, R.id.start)
                StaticData.motionLayout.transitionToEnd()
                isUp = !isUp
            }

        } else {
//            println("No Vertical Scrolled")
        }
    }
}