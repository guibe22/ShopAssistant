package com.wgg.shopassistant.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.wgg.shopassistant.util.BottomNavItem

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem
) {
    NavigationBarItem(

        label = {
            Text(text = screen.title)
        },


        icon = {
            Icon(
                 screen.icon,
                contentDescription = screen.title
            )
        },


        selected = true,


        alwaysShowLabel = true,


        onClick = { /*TODO*/ },


        colors = NavigationBarItemDefaults.colors()
    )
}



@Composable
fun BottomNavigation() {

    val items = listOf(
        BottomNavItem.Agregar,
        BottomNavItem.List,
        BottomNavItem.Planificar,
        BottomNavItem.Ajustes
    )


    NavigationBar {
        items.forEach { item ->
            AddItem(
                screen = item
            )
        }
    }
}
