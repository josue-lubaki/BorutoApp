package ca.josuelubaki.borutoapp.presentation.screens.details

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.BottomSheetValue.Expanded
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ca.josuelubaki.borutoapp.R
import ca.josuelubaki.borutoapp.domain.model.Hero
import ca.josuelubaki.borutoapp.presentation.components.InfoBox
import ca.josuelubaki.borutoapp.presentation.components.OrderedList
import ca.josuelubaki.borutoapp.ui.theme.INFO_ICON_SIZE
import ca.josuelubaki.borutoapp.ui.theme.LARGE_PADDING
import ca.josuelubaki.borutoapp.ui.theme.MEDIUM_PADDING
import ca.josuelubaki.borutoapp.ui.theme.MIN_SHEET_HEIGHT
import ca.josuelubaki.borutoapp.ui.theme.SMALL_PADDING
import ca.josuelubaki.borutoapp.ui.theme.titleColor
import ca.josuelubaki.borutoapp.util.Constants.ABOUT_TEXT_MAX_LINES
import ca.josuelubaki.borutoapp.util.Constants.BASE_URL
import ca.josuelubaki.borutoapp.util.Constants.MIN_BACKGROUND_IMAGE_HEIGHT
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsContent(
    navController : NavHostController,
    selectedHero: Hero?
) {

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = Expanded,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessLow,
            )
        )
    )

    val currentSheetFraction = scaffoldState.getCurrentSheetFraction()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedHero?.let { BottomSheetContent(selectedHero = it) }
        }
    ) {
        selectedHero?.let { hero ->
            BackgroundContent(
                heroImage = hero.image,
                imageFraction = currentSheetFraction,
                onCloseClicked = {
                    navController.popBackStack()
                },
            )
        }
    }

}

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor : Color = MaterialTheme.colors.surface,
    contentColor : Color = MaterialTheme.colors.titleColor
) {

    Column(
        modifier = Modifier
            .background(color = sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier
                    .size(INFO_ICON_SIZE)
                    .weight(2f),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = stringResource(id = R.string.app_logo),
                tint = contentColor
            )

            Text(
                modifier = Modifier
                    .weight(8f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            InfoBox(
                icon = painterResource(id = R.drawable.ic_bolt),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.power.toString(),
                smallText = stringResource(id = R.string.power),
                textColor = contentColor,
            )

            InfoBox(
                icon = painterResource(id = R.drawable.ic_calendar),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.month,
                smallText = stringResource(id = R.string.month),
                textColor = contentColor,
            )

            InfoBox(
                icon = painterResource(id = R.drawable.ic_cake),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.day,
                smallText = stringResource(id = R.string.day),
                textColor = contentColor,
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold,
        )

        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = ABOUT_TEXT_MAX_LINES,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            OrderedList(
                title = stringResource(id = R.string.family),
                items = selectedHero.family,
                textColor = contentColor,
            )

            OrderedList(
                title = stringResource(id = R.string.abilities),
                items = selectedHero.abilities,
                textColor = contentColor,
            )

            OrderedList(
                title = stringResource(id = R.string.nature_types),
                items = selectedHero.natureTypes,
                textColor = contentColor,
            )
        }
    }
}

@Composable
fun BackgroundContent(
    heroImage : String,
    imageFraction : Float = 1f,
    backgroundColor : Color = MaterialTheme.colors.surface,
    onCloseClicked : () -> Unit = {}
) {

    val imageURL = "$BASE_URL$heroImage"
    val painter = rememberAsyncImagePainter(
        model = imageURL,
        placeholder = painterResource(id = R.drawable.ic_placeholder),
        error = painterResource(id = R.drawable.ic_placeholder)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MIN_BACKGROUND_IMAGE_HEIGHT)
                .align(Alignment.TopStart),
            painter = painter,
            contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() }
            ){
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.close_icon),
                    tint = Color.White
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
fun BottomSheetScaffoldState.getCurrentSheetFraction() : Float {
    val fraction = bottomSheetState.progress.fraction
    val currentValue = bottomSheetState.currentValue
    val targetValue = bottomSheetState.targetValue

    return when {
        currentValue == Collapsed && targetValue == Collapsed -> 1f
        currentValue == Expanded && targetValue == Expanded -> 0f
        currentValue == Collapsed && targetValue == Expanded -> 1f - fraction
        currentValue == Expanded && targetValue == Collapsed -> fraction
        else -> fraction
    }
}

@Preview
@Composable
fun DetailsContentPreview() {
    DetailsContent(
        navController = rememberNavController(),
        selectedHero = Hero(
            id = 1,
            name = "Boruto",
            image = "https://localhost:8080/images/boruto.png",
            about = "Boruto is a young shinobi with an incorrigible knack for mischief. He achieved his dream to become a ninja in the Village Hidden in the Leaves, and now his face sits atop the Hokage monument. But this is not his story... A new generation of ninja are ready to take the stage, led by Naruto's own son, Boruto! ",
            rating = 4.5,
            power = 92,
            month = "March",
            day = "10",
            family = listOf(
                "Naruto",
                "Hinata",
                "Hanabi",
                "Himawari",
                "Kawaki"),
            abilities = listOf(
                "Karma",
                "Jogan",
                "Rasengan",
                "Intelligence"),
            natureTypes = listOf(
                "Lightning",
                "Wind",
                "Water"
            )
        )
    )
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailsContentDarkPreview() {
    DetailsContent(
        navController = rememberNavController(),
        selectedHero = Hero(
            id = 1,
            name = "Boruto",
            image = "https://localhost:8080/images/boruto.png",
            about = "Boruto is a young shinobi with an incorrigible knack for mischief. He achieved his dream to become a ninja in the Village Hidden in the Leaves, and now his face sits atop the Hokage monument. But this is not his story... A new generation of ninja are ready to take the stage, led by Naruto's own son, Boruto! ",
            rating = 4.5,
            power = 92,
            month = "March",
            day = "10",
            family = listOf(
                "Naruto",
                "Hinata",
                "Hanabi",
                "Himawari",
                "Kawaki"),
            abilities = listOf(
                "Karma",
                "Jogan",
                "Rasengan",
                "Intelligence"),
            natureTypes = listOf(
                "Lightning",
                "Wind",
                "Water"
            )
        )
    )
}