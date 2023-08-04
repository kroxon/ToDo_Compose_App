package com.todocomposeapp.components

import androidx.compose.foundation.background
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.todocomposeapp.R

@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    openDialog: Boolean,
    closeDialog: () -> Unit,
    onYesClicked: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = message,
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    fontWeight = FontWeight.Normal
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onYesClicked()
                        closeDialog()
                    })
                {
                    Text(text = stringResource(id = R.string.yes))
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { closeDialog() })
                { Text(text = stringResource(id = R.string.no)) }
            },
            onDismissRequest = { closeDialog() }
        )
    }
}

@Composable
@Preview
private fun DisplayAlertDialogPreview() {
    DisplayAlertDialog(
        title = "Do you wat delete all tasks?",
        message = "Do you want to permanently delete all tasks?",
        openDialog = true,
        closeDialog = { /*TODO*/ }) {

    }
}