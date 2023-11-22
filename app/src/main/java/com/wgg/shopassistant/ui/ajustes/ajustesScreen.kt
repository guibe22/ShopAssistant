import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ExitToApp
import androidx.compose.material.icons.twotone.FavoriteBorder
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjustesScreen(
    isDarkTheme: MutableState<Boolean>
) {
    val context = LocalContext.current

    Column (
        modifier = Modifier.fillMaxWidth()
    ){
        Spacer(modifier = Modifier.height(16.dp))
        NavigationDrawerItem(
            icon = { Icon(Icons.TwoTone.FavoriteBorder, contentDescription = null) },
            label = { Text("Donaciones",color = MaterialTheme.colorScheme.primary) },
            selected = false,
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://paypal.me/wilbergalvez?country.x=IT&locale.x=en_US"))
                context.startActivity(intent)
            },
            modifier = Modifier
                .padding(NavigationDrawerItemDefaults.ItemPadding)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        NavigationDrawerItem(
            icon = { Icon(Icons.TwoTone.Info, contentDescription = null) },
            label = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Modo Oscuro", modifier = Modifier.weight(1f),color = MaterialTheme.colorScheme.primary)
                    Switch(
                        checked =  isDarkTheme.value,
                        onCheckedChange = { isChecked ->
                            isDarkTheme.value = isChecked

                        },
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            },
            selected = false,
            onClick = {},
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )


        Spacer(modifier = Modifier.height(16.dp))
        NavigationDrawerItem(
            icon = { Icon(Icons.TwoTone.ExitToApp, contentDescription = null) },
            label = { Text("Cerrar Sesión",color = MaterialTheme.colorScheme.primary) },
            selected = false,
            onClick = {
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}




