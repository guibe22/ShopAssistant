package com.wgg.shopassistant.util

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun AlertDialogSample(
    OnClick: () -> Unit = {},
    title:String,
    content:String,
    openDialog: MutableState<Boolean>,
) {


    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {

                openDialog.value = false
            },
            title = {
                Text(text = title)
            },
            text = {
                Text(text = content)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        OnClick()
                    }
                ) {
                    Text("SI")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("NO")
                }
            }
        )
    }
}