package com.example.pillertest2_2.screen.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pillertest2_2.screen.details.logic.DetailsState
import com.example.pillertest2_2.screen.details.logic.DetailsViewModel

@Composable
fun DetailsView(
     navController: NavController,
     detailsViewModel: DetailsViewModel = hiltViewModel(),
) {
     val uiState by detailsViewModel.detailsState.collectAsStateWithLifecycle()

     Box(
          modifier         = Modifier.fillMaxSize(),
          contentAlignment = Alignment.Center
     ) {
          when (uiState) {
               is DetailsState.Init -> {}

               is DetailsState.Loading -> {
                    CircularProgressIndicator(
                         modifier = Modifier.size(
                              height = 150.dp,
                              width = 150.dp
                         )
                    )
               }

               is DetailsState.Error -> {
                    Text(
                         (uiState as DetailsState.Error).message!!,
                    )
               }

               is DetailsState.DetailsAvailable -> {
                    val character = detailsViewModel.selectedCharacterDetails!!
                    val icon =
                         if (character.isFavorite) {
                              Icons.Filled.Favorite
                         }
                         else {
                              Icons.Filled.FavoriteBorder
                         }
                    Box() {
                         ElevatedCard() {
                              Column {
                                   AsyncImage(
                                        model = character.image,
                                        "Image from Details",
                                        modifier = Modifier.size(
                                             height = 200.dp,
                                             width = 200.dp,
                                        )
                                   )

                                   Row {
                                        Text("name:")
                                        Text(character.name ?: "-")
                                   }

                                   Row {
                                        Text("gender:")
                                        Text(character.gender ?: "-")
                                   }

                                   Row {
                                        Text("species:")
                                        Text(character.species ?: "-")
                                   }

                                   Row {
                                        Text("status:")
                                        Text(character.status ?: "-")
                                   }

                                   Row {
                                        Text("location:")
                                        Text(character.location?.keys?.first() ?: "-")
                                   }
                              }
                         }
                         Icon(
                              imageVector = icon,
                              contentDescription = "favorite_marker_icon",
                              tint = Color.Black,
                              modifier = Modifier
                                   .size(24.dp)
                                   .align(Alignment.TopEnd)
                         )
                    }
               }
          }
     }
}
