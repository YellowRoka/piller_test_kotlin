package com.example.pillertest2_2.screen.splash.parts

import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri

@Composable
fun UniqueVideoView(link: Int, callBack : () -> Unit){
    val context   = LocalContext.current
    val videoView = VideoView(context)
    val uri       = "android.resource://${context.packageName}/${link}".toUri()
    videoView.setVideoURI(uri)
    videoView.setOnCompletionListener {
        callBack()
    }
    videoView.setOnPreparedListener {
        it.isLooping = false
        videoView.start()
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory  = {
            videoView
        }
    )
}
