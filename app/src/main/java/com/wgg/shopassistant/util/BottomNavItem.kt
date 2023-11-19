package com.wgg.shopassistant.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.twotone.AddCircle
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material.icons.twotone.List
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector
) {
    object Agregar :
        BottomNavItem(
            "Agregar",
            Icons.TwoTone.AddCircle
        )

    object List :
        BottomNavItem(
            "Lista",
            Icons.TwoTone.List
        )

    object Planificar :
        BottomNavItem(
            "Planificar",
            Icons.TwoTone.DateRange
        )

    object Ajustes :
        BottomNavItem(
            "Ajustes",
            Icons.TwoTone.Settings
        )
}