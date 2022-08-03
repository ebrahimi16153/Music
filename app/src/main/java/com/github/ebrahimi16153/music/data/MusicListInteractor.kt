package com.github.ebrahimi16153.music.data

import android.annotation.SuppressLint
import android.content.Context
import android.provider.MediaStore

class MusicListInteractor(private val context: Context) {


    @SuppressLint("Recycle")
    fun getListOfMusic(): MutableList<MusicFile> {

        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"

        val projection = arrayOf(

            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION,


            )

        val cursor = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )

        val songs = mutableListOf<MusicFile>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                /*  StaticData.musicCount++ save  how many music is ArrayList - > int */


                songs.add(

                    MusicFile(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),

                        )
                )
            }
        }
        StaticData.musicCount = songs.size
        return songs

    }


}