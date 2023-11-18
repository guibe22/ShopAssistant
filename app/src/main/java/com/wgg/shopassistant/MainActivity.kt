package com.wgg.shopassistant

import ajustesScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.wgg.shopassistant.ui.navigation.BottomNavigation
import com.wgg.shopassistant.ui.theme.ShopAssistantTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopAssistantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomNavigation(navController)
                        },
                        topBar = {
                            TopAppBar(
                                title = {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.Transparent),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.ShoppingCart,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.primary
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = "Shop Assistant",
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        }

                                    }
                                }
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it)
                                .padding(8.dp)
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = "bottomNavHost"
                            ) {
                                composable("bottomNavHost") {
                                    klk()
                                }
                                composable("agregar") {
                                    klk2()

                                }
                                composable("lista") {
                                    klk()

                                }
                                composable("planificar") {
                                    klk()
                                }
                                composable("ajustes") {
                                    ajustesScreen()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}


@Composable
fun klk(){
    Text("klk jeffry")
}
@Composable
fun klk2(){
    Text("klk vladi")
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}