/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.AppDestinations.CAT_DETAIL_ID_KEY
import com.example.androiddevchallenge.ui.cat.CatDetails
import com.example.androiddevchallenge.ui.cats.CatsList

/**
 * Destinations used in the App.
 */
private object AppDestinations {
    const val CATS_ROUTE = "cats"
    const val CAT_DETAIL_ROUTE = "cat"
    const val CAT_DETAIL_ID_KEY = "catId"
}

@ExperimentalAnimationApi
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
