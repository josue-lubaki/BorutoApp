package ca.josuelubaki.borutoapp.navigation

import ca.josuelubaki.borutoapp.util.Constants.DETAILS_ARGUMENT_KEY

sealed class Screen(val route : String){
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Home : Screen("home_screen")
    object Details : Screen("details_screen/{$DETAILS_ARGUMENT_KEY}"){
        fun passHeroId(id : String) = "details_screen/$id"
    }
    object Search : Screen("search_screen")
}
