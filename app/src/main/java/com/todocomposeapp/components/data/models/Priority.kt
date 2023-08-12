package com.todocomposeapp.components.data.models

import androidx.compose.ui.graphics.Color
import com.todocomposeapp.ui.theme.HighPriorityColor
import com.todocomposeapp.ui.theme.LowPriorityColor
import com.todocomposeapp.ui.theme.MediumPriorityColor
import com.todocomposeapp.ui.theme.NonePriorityColor

enum class Priority (val color: Color) {
    HIGH(HighPriorityColor),
    LOW(LowPriorityColor),
    MEDIUM(MediumPriorityColor),
    NONE(NonePriorityColor)
}