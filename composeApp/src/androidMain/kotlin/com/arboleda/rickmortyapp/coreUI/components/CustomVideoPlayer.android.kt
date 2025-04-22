package com.arboleda.rickmortyapp.coreUI.components

import android.widget.VideoView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun CustomVideoPlayer(
    modifier: Modifier,
    url: String,
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val videoView = VideoView(context)
            videoView.apply {
                setVideoPath(url)
                setMediaController(
                    android.widget.MediaController(context).apply {
                        setAnchorView(videoView)
                    },
                )
                start()
            }
        },
    )
}
