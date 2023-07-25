package com.bayu.instagramhomepage.ui.reels

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@SuppressLint("UnsafeOptInUsageError")
@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun Player(
    index: Int,
    uri: Uri,
    pagerState: PagerState,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val scope = rememberCoroutineScope()

    var exoPlayer: ExoPlayer? by remember { mutableStateOf(null) }

    val mediaItem: MediaItem by remember(uri) { mutableStateOf(MediaItem.fromUri(uri)) }
    var currentPlaybackPosition by remember { mutableStateOf(0L) }
    var currentMediaItemIndex by remember { mutableStateOf(0) }
    var playWhenReady by remember { mutableStateOf(false) }

    fun initializePlayer() {
        scope.launch {
            exoPlayer = ExoPlayer.Builder(context)
                .build()
                .also { player ->
                    player.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
                    player.repeatMode = Player.REPEAT_MODE_ONE
                    player.setMediaItems(listOf(mediaItem), currentMediaItemIndex, currentPlaybackPosition)
                    player.playWhenReady = playWhenReady
                    player.prepare()
                }
        }
    }

    fun releasePlayer() {
        exoPlayer?.let { player ->
            currentPlaybackPosition = player.currentPosition
            currentMediaItemIndex = player.currentMediaItemIndex
            playWhenReady = player.playWhenReady
            player.release()
        }
        exoPlayer = null
    }

    DisposableEffect(key1 = lifecycleOwner, uri, effect = {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> initializePlayer()
                Lifecycle.Event.ON_STOP -> releasePlayer()
                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })


    exoPlayer?.let {
        LaunchedEffect(key1 = pagerState, block = {
            combine(
                flow = snapshotFlow { pagerState.currentPageOffsetFraction },
                flow2 = snapshotFlow { pagerState.currentPage }
            ) { currentPageOffsetFraction, currentPage ->
                currentPageOffsetFraction to currentPage
            }.filter { it.first == 0.0f }.collect { (_, currentPage) ->
                exoPlayer ?: return@collect
                if (currentPage == index) {
                    exoPlayer!!.play() // play video hanya jika item seluruh tampil; atau snap 0.0 selain itu pause
                } else {
                    exoPlayer!!.pause()
                }
            }
        })

        AndroidView(
            modifier = Modifier
                .fillMaxSize(),
            factory = {
                PlayerView(it)
            },
            update = {
                with(it) {
                    player = exoPlayer
                    hideController()
                    useController = false
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                }
            },
            onRelease = {
                releasePlayer()
            },
            onReset = {
                // ignore
            }
        )
    }
}
