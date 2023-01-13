package ca.josuelubaki.borutoapp.domain.model

import androidx.annotation.DrawableRes
import ca.josuelubaki.borutoapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image : Int,
    val title : String,
    val description : String
) {
    object First : OnBoardingPage(
        image = R.drawable.greetings,
        title = "Greetings",
        description = "Are you a fan of Boruto ? Because if you are then we have a great news for you !"
    )

    object Second : OnBoardingPage(
        image = R.drawable.explore,
        title = "explore",
        description = "Find your favorite hero and learn some of the things that you didn't know about."
    )

    object Third : OnBoardingPage(
        image = R.drawable.power,
        title = "Power",
        description = "Check out your hero's power level and see how much are they strong comparing to others"
    )
}
