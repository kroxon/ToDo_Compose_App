package com.todocomposeapp.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.todocomposeapp.data.models.Priority
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.todocomposeapp.R
import com.todocomposeapp.ui.theme.PRIORITY_DROP_DOWN_HEIGHT
import com.todocomposeapp.ui.theme.PRIORITY_INDICATOR_SIZE


@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val angle: Float by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(PRIORITY_DROP_DOWN_HEIGHT)
        .clickable { expanded = true }
        .border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.34f),
            shape = MaterialTheme.shapes.small
        ),
        verticalAlignment = Alignment.CenterVertically) {
        Canvas(
            modifier = Modifier
                .size(PRIORITY_INDICATOR_SIZE)
                .weight(1f)
        ) {
            drawCircle(color = priority.color)
        }
        Text(
            modifier = Modifier.weight(8f),
            text = priority.name,
            style = MaterialTheme.typography.titleSmall
        )
        IconButton(
            onClick = { expanded = true }, modifier = Modifier
                .alpha(0.74f)
                .rotate(degrees = angle)
                .weight(1.5f)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(id = R.string.dropdown_arrow_icon)
            )
        }
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(fraction = 0.94f),
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { priority.name },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.LOW)
                },
                leadingIcon = { PriorityItem(priority = Priority.LOW) }
            )
            DropdownMenuItem(
                text = { priority.name },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.MEDIUM)
                },
                leadingIcon = { PriorityItem(priority = Priority.MEDIUM) }
            )
            DropdownMenuItem(
                text = { priority.name },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.HIGH)
                },
                leadingIcon = { PriorityItem(priority = Priority.HIGH) }
            )
        }
    }
}

@Composable
@Preview
private fun PriorityDropDownPreview() {
    PriorityDropDown(priority = Priority.LOW, onPrioritySelected = {})
}