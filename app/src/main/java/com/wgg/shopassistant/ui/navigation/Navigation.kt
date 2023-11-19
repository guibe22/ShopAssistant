package com.wgg.shopassistant.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.wgg.shopassistant.util.BottomNavItem

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title, color = MaterialTheme.colorScheme.primary)
        },
        icon = {
            Icon(
                 screen.icon,
                contentDescription = screen.title,
            )
        },
        selected = true,
        alwaysShowLabel = true,
        onClick = { navController.navigate(screen.title) }
    )
}



@Composable
fun BottomNavigation( navController: NavHostController) {

    val items = listOf(
        BottomNavItem.Agregar,
        BottomNavItem.List,
        BottomNavItem.Planificar,
        BottomNavItem.Ajustes
    )


    NavigationBar {
        items.forEach { item ->
            AddItem(
                screen = item,
                navController = navController
            )
        }
    }
}
