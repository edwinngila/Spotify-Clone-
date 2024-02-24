package com.example.classwork.Presentation.Screens.DashboardScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.classwork.Controlers.playMp3
import com.example.classwork.Presentation.Components.ProgressSpinner
import com.example.classwork.R


@Composable
fun MusicPayer(
    navController: NavController,
    param:String,
    onMp3: playMp3
) {
    val dataLoaded = remember { mutableStateOf(false) }
    LaunchedEffect(Unit){
        if (!dataLoaded.value) {
            onMp3.getData(mp3Id = param)
            dataLoaded.value = true
        }
    }

    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()
    val musicString = onMp3.musicUrl.value

    val mediaSource = remember(musicString) {
        androidx.media3.common.MediaItem.fromUri(musicString)
    }
    LaunchedEffect(mediaSource){
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }
    DisposableEffect(Unit){
        onDispose{
            exoPlayer.release()
        }
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ){
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back",
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        navController.navigate("Home")
                    }
            )
        }
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            val painter = rememberAsyncImagePainter(model = onMp3.musicImg.value)
            Image(
                painter = painter,
                contentDescription = "image",
                modifier = Modifier
                    .size(400.dp)
                    .padding(top = 30.dp)
            )
        }
        Column (
            modifier = Modifier.padding(bottom =20.dp)
        ){
            Text(
                text =onMp3.name.value ,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0x1F, 0xDF, 0x64),
                modifier = Modifier
                    .padding(10.dp)
                    .padding(top = 30.dp)
            )
            Text(
                text = onMp3.uploader.value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        AndroidView(
            factory = {
                ctx->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    setBackgroundColor(Color(0x12, 0x12, 0x12).toArgb())

                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
    }
    var inprogress = onMp3.inProgress.value
    if (inprogress){
        ProgressSpinner()
    }

}