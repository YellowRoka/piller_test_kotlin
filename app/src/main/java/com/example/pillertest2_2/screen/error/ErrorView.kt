package com.example.pillertest2_2.screen.error

import androidx.compose.foundation.layout.Box

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment

@Composable
fun ErrorView(
    navController: NavController
){
    Box(
        contentAlignment = Alignment.Center
    ){
        Text("Something went wrong! Please try again later! :(")
    }
}
