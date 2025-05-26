package com.example.pillertest2_2.screen.main_screen.parts




import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pillertest2_2.screen.main_screen.logic.MainViewModel


@Composable
fun Switcher (){
    val viewModel         = hiltViewModel<MainViewModel>()
    val isFavoritesActive = viewModel.isFavoritesActive

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment     = Alignment.CenterVertically,
        modifier              = Modifier.height(60.dp)
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(25.dp)
                .width(100.dp)
                .background(if(!isFavoritesActive) Color.Blue else Color.White)
                .clickable {
                    viewModel.showFavorites(false)
                }
        ) {
            Text(
                "All",
                color      = (if(!isFavoritesActive) Color.White else Color.Black),
                fontWeight = FontWeight.Bold,
                fontSize   = 20.sp,
                textAlign  = TextAlign.Center,
                modifier   = Modifier.fillMaxWidth()
            )
        }
            VerticalDivider(
            color = Color.Black,
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(25.dp)
                .width(100.dp)
                .background(if(isFavoritesActive) Color.Blue else Color.White)
                .clickable {
                    viewModel.showFavorites(true)
                }
        ) {
            Text(
                "Favorites",
                color      = (if(isFavoritesActive) Color.White else Color.Black),
                fontWeight = FontWeight.Bold,
                fontSize   = 20.sp,
                textAlign  = TextAlign.Center,
                modifier   = Modifier.fillMaxWidth()
            )
        }
    }
}
