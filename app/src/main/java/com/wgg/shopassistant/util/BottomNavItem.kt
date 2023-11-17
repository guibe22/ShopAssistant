package com.wgg.shopassistant.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector
) {
    object Agregar :
        BottomNavItem(
            "Agregar",
            Icons.Default.AddCircle
        )

    object List :
        BottomNavItem(
            "Lista",
            Icons.Default.List
        )

    object Planificar :
        BottomNavItem(
            "Planificar",
            Icons.Default.DateRange
        )

    object Ajustes :
        BottomNavItem(
            "Ajustes",
            Icons.Default.Settings
        )
}