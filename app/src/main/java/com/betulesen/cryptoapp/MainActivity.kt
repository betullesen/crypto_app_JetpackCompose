package com.betulesen.cryptoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.betulesen.cryptoapp.ui.theme.CryptoAppTheme
import com.betulesen.cryptoapp.view.CryptoDetailScreen
import com.betulesen.cryptoapp.view.CryptoListScreen
import com.betulesen.cryptoapp.view.CryptoSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import android.window.SplashScreen as SplashScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoAppTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash_screen"){

                    composable("splash_screen") {
                        CryptoSplashScreen(navController)
                    }

                    composable("crypto_list_screen"){
                        //cryptoListScreen
                        CryptoListScreen(navController)
                    }

                    composable("crypto_detail_screen/{cryptoId}/{cryptoPrice}", arguments = listOf(
                        navArgument("cryptoId"){
                            type = NavType.StringType
                        },
                        navArgument("cryptoPrice"){
                            type = NavType.StringType
                        }
                    )) {
                        val cryptoId = remember {
                            it.arguments?.getString("cryptoId")
                        }
                        val cryptoPrice = remember {
                            it.arguments?.getString("cryptoPrice")
                        }



                        // CryptoDetailScreen
                        CryptoDetailScreen(
                            id = cryptoId ?: ""
                            ,price = cryptoPrice ?: ""
                            ,navController = navController)
                    }

                }


            }
        }
    }
}
