package com.wgg.shopassistant

import AjustesScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.wgg.shopassistant.ui.navigation.BottomNavigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wgg.shopassistant.ui.theme.NavigationDrawerSwitchThemeTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val isDarkTheme = rememberSaveable { mutableStateOf(false) }

            NavigationDrawerSwitchThemeTheme(darkTheme = isDarkTheme.value) {

                Surface(
                    modifier = Modifier.fillMaxSize(),
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
                                                .fillMaxWidth(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                Icon(
                                                    imageVector = Icons.TwoTone.ShoppingCart,
                                                    contentDescription = null
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
                                    Klk()
                                }
                                composable("agregar") {
                                    Klk2()

                                }
                                composable("lista") {
                                    Klk()

                                }
                                composable("planificar") {
                                    Klk2()
                                }
                                composable("ajustes") {
                                    AjustesScreen(isDarkTheme)
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
fun Klk(){
    Text("klk jeffry")
}
@Composable
fun Klk2(){
    Text("klk vladi")
}
