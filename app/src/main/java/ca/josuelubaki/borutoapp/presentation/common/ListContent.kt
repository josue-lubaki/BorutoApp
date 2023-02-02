package ca.josuelubaki.borutoapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import ca.josuelubaki.borutoapp.R
import ca.josuelubaki.borutoapp.domain.model.Hero
import ca.josuelubaki.borutoapp.navigation.Screen
import ca.josuelubaki.borutoapp.presentation.components.RatingWidget
import ca.josuelubaki.borutoapp.presentation.components.ShimmerEffect
import ca.josuelubaki.borutoapp.ui.theme.HERO_ITEM_HEIGHT
import ca.josuelubaki.borutoapp.ui.theme.LARGE_PADDING
import ca.josuelubaki.borutoapp.ui.theme.MEDIUM_PADDING
import ca.josuelubaki.borutoapp.ui.theme.SMALL_PADDING
import ca.josuelubaki.borutoapp.ui.theme.topAppBarContentColor
import ca.josuelubaki.borutoapp.util.Constants.BASE_URL
import coil.compose.rememberAsyncImagePainter

@Composable
fun ListContent(
    heroes: LazyPagingItems<Hero>,
    navController: NavHostController
) {
    val result = handlePagingResult(heroes)

    if(result){
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
        ){
            items(
                items = heroes,
                key = { hero -> hero.id }
            ){hero ->
                hero?.let{
                    HeroItem(
                        hero = it,
                        navController = navController
                    )
                }

            }
        }
    }
}

@Composable
fun handlePagingResult(
    heroes: LazyPagingItems<Hero>
) : Boolean {
    heroes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                false
            }
            else -> true
        }
    }
}

@Composable
fun HeroItem(
    hero: Hero,
    navController: NavHostController
) {

    val painter = rememberAsyncImagePainter(
        model = "$BASE_URL${hero.image}",
        placeholder = painterResource(id = R.drawable.ic_placeholder),
        error = painterResource(id = R.drawable.ic_placeholder)
    )
    Box(modifier = Modifier
        .height(HERO_ITEM_HEIGHT)
        .clickable {
            navController.navigate(Screen.Details.passHeroId(heroId = hero.id))
        },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.hero_image),
                contentScale = ContentScale.Crop
            )
        }

        Surface(modifier = Modifier
            .fillMaxHeight(0.4f)
            .fillMaxWidth(),
            color = Color.Black.copy(alpha = 0.5f),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(all = MEDIUM_PADDING)
            ){
                Text(
                    text = hero.name,
                    color = MaterialTheme.colors.topAppBarContentColor,
                    fontSize= MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Row(modifier = Modifier
                    .padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = hero.rating
                    )
                    Text(
                        text = "(${hero.rating})",
                        color = Color.White.copy(alpha = ContentAlpha.medium),
                        textAlign= TextAlign.Center,
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroItemPreview() {
    val hero = Hero(
        id = 1,
        name = "Sasuke",
        image = "/images/sasuke.jpg",
        about = "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
        rating = 5.0,
        power = 98,
        month = "July",
        day = "23rd",
        family = listOf(),
        abilities = listOf(),
        natureTypes = listOf()
    )

    HeroItem(hero = hero, navController = rememberNavController())
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HeroItemDarkPreview() {
    val hero = Hero(
        id = 1,
        name = "Sasuke",
        image = "/images/sasuke.jpg",
        about = "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
        rating = 5.0,
        power = 98,
        month = "July",
        day = "23rd",
        family = listOf(),
        abilities = listOf(),
        natureTypes = listOf()
    )

    HeroItem(hero = hero, navController = rememberNavController())
}