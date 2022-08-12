package com.github.ebrahimi16153.music.data

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import androidx.annotation.RequiresApi
import com.github.ebrahimi16153.music.R
import com.github.ebrahimi16153.music.data.model.AlbumMusic
import com.github.ebrahimi16153.music.data.model.MusicFile
import java.io.ByteArrayOutputStream
import java.io.IOException

class TrackListInteractor(private val context: Context) {


    @SuppressLint("Recycle")
    fun getListOfMusic(): MutableList<MusicFile> {

        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"

        val projection = arrayOf(

            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM,
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
        val album = mutableListOf<String>()
        val artist = mutableListOf<String>()
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

                album.add(cursor.getString(4))
                artist.add(cursor.getString(1))

            }

        }
        StaticData.artistList = artist.toSet().toList().toMutableList()
        StaticData.artistList.sort()
        return songs

    }

    @SuppressLint("Recycle")
    fun getAlbum(): MutableList<AlbumMusic> {
        val selection = MediaStore.Audio.Media.ALBUM+ " != 0"

        val projection = arrayOf(
            MediaStore.Audio.Albums.ALBUM,
            MediaStore.Audio.Albums.ARTIST,
        )


        val cursor = context.contentResolver.query(
            MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )
        val album = mutableListOf<AlbumMusic>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val data = StaticData.allTrack.find {
                    it.albume == cursor.getString(0)
                }
                if (data != null) {
                    album.add(

                        AlbumMusic(
                            cursor.getString(0),
                            cursor.getString(1),
                            data.data
                        )
                    )
                }
            }
        }

        return album
    }
}