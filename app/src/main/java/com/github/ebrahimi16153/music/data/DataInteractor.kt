package com.github.ebrahimi16153.music.data

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.github.ebrahimi16153.music.data.model.Album
import com.github.ebrahimi16153.music.data.model.MusicFile


class DataInteractor(private val context: Context) {


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
        return songs

    }

    @SuppressLint("Recycle")
    fun getAlbum(): MutableList<Album> {
        val selection = MediaStore.Audio.Media.ALBUM + " != 0"

        val projection = arrayOf(
            MediaStore.Audio.Albums.ALBUM,
            MediaStore.Audio.Albums.ARTIST,
            MediaStore.Audio.Albums._ID,

        )


        val cursor = context.contentResolver.query(
            MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )
        val album: MutableList<Album> = mutableListOf()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                album.add(
                    Album(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                    )
                )
            }

        }



        return album
    }

    @SuppressLint("Recycle")
    fun getArtist(): MutableList<String> {
        val selection = MediaStore.Audio.Media.ARTIST + " !=0"
        val projection = arrayOf(
            MediaStore.Audio.Media.ARTIST
        )
        val cursor = context.contentResolver.query(
            MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )
        val artist = mutableListOf<String>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                artist.add(cursor.getString(0))
            }
        }
        return artist
    }


    fun getUri(albumID: String): Uri {
        return ContentUris.withAppendedId(
            MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
            //                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            albumID.toLong()
        )
    }


}