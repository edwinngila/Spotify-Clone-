package com.example.classwork.Presentation.Components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.classwork.Controlers.GetUserMusicViewModel
import com.example.classwork.R
import com.example.classwork.data.MusicItems
import com.example.classwork.data.UserMusic
import androidx.compose.foundation.layout.Column as Column


@Composable
fun HomeComponents(navController: NavController,viewModel: GetUserMusicViewModel) {
    val inprogress = viewModel.inProgress.value
    val getData = viewModel.state.value
    Log.d("TAG", "HomeComponents: ${getData.size}")
    if(inprogress){
        ProgressSpinner()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //Rows in the home page
        Row {
            Column (
                modifier = Modifier.padding(5.dp)
            ){
                AlbumCard()
            }
            Column (
                modifier = Modifier.padding(5.dp)
            ){
                AlbumCard()
            }
        }
        Row {
            Column (
                modifier = Modifier.padding(5.dp)
            ){
                AlbumCard()
            }
            Column (
                modifier = Modifier.padding(5.dp)
            ){
                AlbumCard()
            }
        }
        Row {
            Column (
                modifier = Modifier.padding(5.dp)
            ){
                AlbumCard()
            }
            Column (
                modifier = Modifier.padding(5.dp)
            ){
                AlbumCard()
            }
        }

        //Rows in the home page
        Row (
            modifier = Modifier
                .background(Color(0x12, 0x12, 0x12))
                .fillMaxWidth(),
        ){
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Best Artists",
                color = Color.White,
                fontSize = 20.sp
            )
        }

            Row(
                modifier= Modifier
                    .horizontalScroll(rememberScrollState())
                    .background(Color(0x12, 0x12, 0x12)),
            ){
                getData.forEach { album ->
                    MusicCards(navController, album)
                }
            }

        //Rows in the home page
        Row (
            modifier = Modifier
                .background(Color(0x12, 0x12, 0x12))
                .fillMaxWidth(),
        ){
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Artists of the week",
                color = Color.White,
                fontSize = 20.sp
            )
        }
            Row(
                modifier= Modifier
                    .horizontalScroll(rememberScrollState())
                    .background(Color(0x12, 0x12, 0x12)),
            ){
                getData.forEach { album ->
                    MusicCards(navController, album)
                }
            }


        //Rows in the home page
        Row (
            modifier = Modifier
                .background(Color(0x12, 0x12, 0x12))
                .fillMaxWidth(),
        ){
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Artists of the Month",
                color = Color.White,
                fontSize = 20.sp
            )
        }
        Row(
            modifier= Modifier
                .horizontalScroll(rememberScrollState()),
        ){

        }
    }
}

@Composable
fun AlbumCard() {
    Card(
        modifier = Modifier
            .size(width = 170.dp, height = 60.dp)
            .background(Color(0xFF181818))
    ) {
         Row(
             modifier = Modifier.fillMaxSize(),
             verticalAlignment = Alignment.CenterVertically,
             horizontalArrangement = Arrangement.SpaceBetween
         ){
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Album"
            )
            Text(
                modifier = Modifier.padding(end = 40.dp),
                text = "Filled",
                color = Color.White
            )
        }
    }
}
@Composable
fun MusicCards(navController: NavController, album: UserMusic) {
    Card(
        modifier = Modifier
            .size(width = 150.dp, height = 200.dp)
            .padding(5.dp)
            .clickable {
                navController.navigate("MusicPlayer/$album")
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF181818))
        ) {
            val painter = rememberAsyncImagePainter(model = album.musicImg)
            Image(
                painter = painter,
                contentDescription = "Album",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Add a black fade color with a graphics layer
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    )
                    .graphicsLayer(alpha = 0.6f) // Adjust the alpha value as needed
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Row (
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Image(
                        painter = painterResource(id = R.drawable.playbutton),
                        contentDescription =null,
                        modifier = Modifier.size(30.dp)
                    )
                }

                Text(
                    text = album.name?.capitalize() ?: "Unknown",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }
}

@Composable
fun CirculerIcon() {

}