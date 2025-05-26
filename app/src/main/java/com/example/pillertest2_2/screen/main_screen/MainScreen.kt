package com.example.pillertest2_2.screen.main_screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.pillertest2_2.navigation.Routes
import com.example.pillertest2_2.screen.main_screen.logic.MainEvent
import com.example.pillertest2_2.screen.main_screen.logic.MainState
import com.example.pillertest2_2.screen.main_screen.logic.MainViewModel
import com.example.pillertest2_2.screen.main_screen.parts.CharacterChard
import com.example.pillertest2_2.screen.main_screen.parts.CharactersSearchBar
import com.example.pillertest2_2.screen.main_screen.parts.Switcher
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainScreen(
     navController : NavController,
     mainViewModel : MainViewModel = hiltViewModel()
) {
    val uiState by mainViewModel.mainState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        mainViewModel.event.collectLatest {
            event -> when (event) {
                is MainEvent.OpenDetails -> {
                    /* DO NOTHING! */
                }

                is MainEvent.GeneralError -> {
                    navController.navigate(Routes.Error.route)
                }
            }
        }
    }

    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Switcher()

        CharactersSearchBar()

        if(uiState is MainState.Loading) {
            CircularProgressIndicator(
                color = Color.Blue,
                modifier = Modifier.size(50.dp, 50.dp)
            )
        }

        if(uiState is MainState.Init) {
            CircularProgressIndicator(
                color = Color.Blue,
                modifier = Modifier.size(50.dp, 50.dp)
            )
        }

        if(uiState is MainState.Success) {
            val characters = mainViewModel.dataList?:emptyList()
            LazyColumn {
                items(characters) {
                    it -> CharacterChard(
                        character = it,
                        onClick = {
                            mainViewModel.searchById(it.id!!)
                            //SRP miatt itt érdemes navigáltatni
                            navController.navigate(Routes.Details.route)
                        }
                    )
                }
            }
        }

        if(uiState is MainState.Favorites) {
            val favorites = mainViewModel.favoritesMap?.values?.toList()?:emptyList()
            if (favorites.isEmpty()){
                Text("There is no favorite character!")
            }
            else{
                LazyColumn {
                    items(favorites){
                        it -> CharacterChard(
                            character = it,
                            onClick = {
                                mainViewModel.searchById(it.id!!)
                                navController.navigate(Routes.Details.route)
                            }
                        )
                    }
                }
            }
        }

        if(uiState is MainState.NoCharacter){
            Text("There is no searched character!")
        }

        if(uiState is MainState.Error){
            Box(
                contentAlignment = Alignment.Center
            ) {
                ElevatedButton(
                    onClick = {
                        mainViewModel.refresh()
                    }
                ) {
                    Text("Reload")
                }
            }
        }
    }
}
