package com.example.pillertest2_2.screen.main_screen.parts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.pillertest2_2.data.model.CharacterModel
import com.example.pillertest2_2.screen.main_screen.logic.MainViewModel

@Composable
fun CharacterChard(
    character : CharacterModel,
    onClick : ()->Unit
) {
    val mainStateView = hiltViewModel<MainViewModel>()
    val icon =
        if (character.isFavorite) {
            Icons.Filled.Favorite
        }
        else {
            Icons.Filled.FavoriteBorder
        }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                onClick()
            }

    ) {
        Box() {
            Row {
                AsyncImage(
                    model = character.image,
                    "Image from character",
                    modifier = Modifier.size(
                        height = 150.dp,
                        width  = 150.dp,
                    ),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Row {
                        Text("Name:")
                        Text(character.name.toString())
                    }

                    Row {
                        Text("Gender:")
                        Text(character.gender.toString())
                    }
                    Row {
                        Text("Status:")
                        Text(character.status.toString())
                    }
                }
            }

            Icon(
                imageVector        = icon,
                contentDescription = "favorite_marker_icon",
                tint               = Color.Black,
                modifier           = Modifier
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        mainStateView.changeFavoriteState(character)
                    }
            )
        }
    }
}
