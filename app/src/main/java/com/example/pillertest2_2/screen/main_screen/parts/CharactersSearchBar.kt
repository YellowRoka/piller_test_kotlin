package com.example.pillertest2_2.screen.main_screen.parts

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pillertest2_2.screen.main_screen.logic.MainViewModel

@Composable
fun CharactersSearchBar(){
    val mainViewModel = hiltViewModel<MainViewModel>()

    Box(){
        OutlinedTextField(
            mainViewModel.searchedName,
            label = { Text("Search...") },
            onValueChange = { value -> mainViewModel.searchByName(value) },
            trailingIcon = {
                IconButton(
                    onClick = {mainViewModel.searchByName("") }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = "clear_search",
                        tint = Color.Black
                    )
                }
            }
        )
    }
}
