package com.github.ebrahimi16153.music.data.model

import android.graphics.Bitmap
import android.net.Uri
import java.io.ByteArrayOutputStream

data class AlbumMusic(val album:String, val artist:String, val cover: ByteArray)


//MediaStore.Audio.Albums.ALBUM_ID,
//MediaStore.Audio.Albums.ALBUM,
//MediaStore.Audio.Albums.ARTIST,
//MediaStore.Audio.Albums.ALBUM_ART,
//MediaStore.Audio.Albums.NUMBER_OF_SONGS