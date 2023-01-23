package ca.josuelubaki.borutoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ca.josuelubaki.borutoapp.presentation.screens.home.HomeScreen
import ca.josuelubaki.borutoapp.presentation.screens.splash.SplashScreen
import ca.josuelubaki.borutoapp.presentation.screens.welcome.WelcomeScreen
import ca.josuelubaki.borutoapp.util.Constants.DETAILS_ARGUMENT_KEY

@Composable
fun SetupNavGraph(navController : NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(Screen.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }
        composable(Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) { type = NavType.IntType })
        ) { backStackEntry ->
            val heroId = backStackEntry.arguments?.getInt(DETAILS_ARGUMENT_KEY)

        }
        composable(Screen.Search.route){

        }
    }
}