package com.wilvermartinez.rickandmortyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wilvermartinez.rickandmortyapp.ui.screen.CharacterDetailScreen
import com.wilvermartinez.rickandmortyapp.ui.screen.CharacterListScreen
import com.wilvermartinez.rickandmortyapp.ui.theme.RickAndMortyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            RickAndMortyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "character_list",
                        enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { it }) },
                        exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { -it }) }
                    ) {
                        composable("character_list") {
                            CharacterListScreen(navController)
                        }
                        composable("character_detail/{characterId}") { backStackEntry ->
                            val characterId =
                                backStackEntry.arguments?.getString("characterId") ?: ""
                            CharacterDetailScreen(characterId, navController)
                        }
                    }
                }
            }
        }
    }
}
