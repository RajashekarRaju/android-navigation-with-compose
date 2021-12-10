package com.developersbreach.cutepuppies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.developersbreach.cutepuppies.AppDestinations.CAT_DETAIL_ID_KEY
import com.developersbreach.cutepuppies.ui.cat.CatDetails
import com.developersbreach.cutepuppies.ui.cats.CatsList

/**
 * Destinations used in the App.
 */
private object AppDestinations {
    const val CATS_ROUTE = "cats"
    const val CAT_DETAIL_ROUTE = "cat"
    const val CAT_DETAIL_ID_KEY = "catId"
}

@Composable
fun AppNavigation(
    startDestination: String = AppDestinations.CATS_ROUTE
) {
    val navController = rememberNavController()
    val actions = remember(navController) { AppActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            AppDestinations.CATS_ROUTE
        ) {
            CatsList(selectedCat = actions.selectedCat)
        }
        composable(
            "${AppDestinations.CAT_DETAIL_ROUTE}/{$CAT_DETAIL_ID_KEY}",
            arguments = listOf(
                navArgument(CAT_DETAIL_ID_KEY) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            CatDetails(
                catId = arguments.getInt(CAT_DETAIL_ID_KEY),
                navigateUp = actions.navigateUp
            )
        }
    }
}

private class AppActions(
    navController: NavHostController
) {
    val selectedCat: (Int) -> Unit = { catId: Int ->
        navController.navigate("${AppDestinations.CAT_DETAIL_ROUTE}/$catId")
    }
    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }
}
