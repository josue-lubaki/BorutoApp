package ca.josuelubaki.borutoapp.presentation.screens.details

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
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
import ca.josuelubaki.borutoapp.ui.theme.titleColor
import ca.josuelubaki.borutoapp.util.Constants.ABOUT_TEXT_MAX_LINES
import coil.compose.AsyncImagePainter.State.Empty.painter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsContent(
    navController : NavHostController,
    selectedHero: Hero?
) {

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(
            initialValue = BottomSheetValue.Expanded,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow,
            )
        )
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedHero?.let { BottomSheetContent(selectedHero = it) }
        }
    ) {

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

@Preview(showBackground = true)
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

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
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