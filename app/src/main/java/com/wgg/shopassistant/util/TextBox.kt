package com.wgg.shopassistant.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextBox(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    focusDirection: FocusDirection,
    onImeActionPerformed: () -> Unit = {},
    onDoneAction: () -> Unit = {},
    modifier: Modifier,
    onNextAction: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current

    TextField(
        modifier = modifier,
        label = { Text(text = label) },
        singleLine = true,
        maxLines = 1,
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction,
            capitalization = KeyboardCapitalization.Words
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                onNextAction()
                focusManager.moveFocus(FocusDirection.Next)
            },
            onDone = {
                onImeActionPerformed.invoke()
                focusManager.clearFocus()
                onDoneAction()
            }

        )
    )
}
