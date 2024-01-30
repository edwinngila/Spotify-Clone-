package com.example.classwork.Presentation.Components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.classwork.Controlers.GetUserMusicViewModel
import com.example.classwork.R
import com.example.classwork.data.MusicItems


@Composable
fun HomeComponents(navController: NavController,viewModel: GetUserMusicViewModel) {
    val MusicViewModel = viewModel.musicFeed.value
    Log.d("TAG","name:${MusicViewModel}")
    val inprogress = viewModel.inProgress.value
    if(inprogress){
        ProgressSpinner()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                MusicCards(navController)
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
                .horizontalScroll(rememberScrollState()),
        ){
            MusicCards(navController)
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
fun MusicCards(navController: NavController) {
    Card(
        modifier = Modifier
            .size(width = 150.dp, height = 200.dp)
            .background(Color(0xFF181818))
            .padding(5.dp)
            .clickable {
                navController.navigate("MusicPlayer")
            }
    ) {
//        val painter = rememberAsyncImagePainter(model = musicImg)
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Album",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
    }
}

@Composable
fun CirculerIcon() {

}