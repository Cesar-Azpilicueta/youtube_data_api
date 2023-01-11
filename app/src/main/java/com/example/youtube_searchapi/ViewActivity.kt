package com.example.youtube_searchapi

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener
import com.google.android.youtube.player.YouTubePlayerView


class ViewActivity : YouTubeBaseActivity() {
    private lateinit var playerView: YouTubePlayerView
    lateinit var player: YouTubePlayer
    val tag = "ViewActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        val videoId = intent.getStringExtra("videoId")
        println("videoId $videoId")
        initPlayer()

        val btnPlay = findViewById<Button>(R.id.youtubeBtn)

        btnPlay.setOnClickListener(View.OnClickListener {
            if (videoId != null) {
                playVideo(videoId)
            }
        })
    }


    private fun playVideo(videoId: String) {
        if (player.isPlaying) {
            player.pause()
        }
        player.cueVideo(videoId)
    }

    //유튜브 플레이어 메서드
    private fun initPlayer() {
        playerView = (findViewById<View>(R.id.youTubePlayerView) as YouTubePlayerView?)!!
        playerView.initialize(
            R.string.api_key.toString(),
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer,
                    b: Boolean
                ) {
                    player = youTubePlayer
                    player.setPlayerStateChangeListener(object : PlayerStateChangeListener {
                        override fun onLoading() {}
                        override fun onLoaded(id: String) {
                            Log.d(tag, "onLoaded: $id")
                            player.play()
                        }

                        override fun onAdStarted() {}
                        override fun onVideoStarted() {}
                        override fun onVideoEnded() {}
                        override fun onError(errorReason: YouTubePlayer.ErrorReason) {
                            Log.d(tag, "onError: $errorReason")
                        }
                    })
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                    Log.d(tag, "youTubeInitializationResult: $youTubeInitializationResult")
                }
            })
    }
}