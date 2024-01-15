package com.example.classwork.Presentation.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.classwork.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(nameState: MutableState<String>, lable:String) {
    TextField(
        value = nameState.value,
        onValueChange = {
            nameState.value = it
        },
        label = {
                Text(
                    text = lable
                )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}

@Composable
fun FormButton(
    lable: String
) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0x1F, 0xDF, 0x64),
            contentColor = Color.White
        ),
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
    ) {
        Text(text = lable)
    }
}

@Composable
fun SpotifyLogo() {
    Image(
        painter = painterResource(id =R.drawable.sportify),
        contentDescription = "Logo",
    )
}
