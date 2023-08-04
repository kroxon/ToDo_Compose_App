package com.todocomposeapp.ui.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.todocomposeapp.data.models.Priority
import com.todocomposeapp.data.models.ToDoTask
import com.todocomposeapp.ui.theme.LARGE_PADDING
import com.todocomposeapp.ui.theme.PRIORITY_INDICATOR_SIZE
import com.todocomposeapp.ui.theme.TASK_ITEM_ELEVATION
import com.todocomposeapp.ui.theme.TOP_APP_BAR_HEIGHT
import com.todocomposeapp.ui.theme.taskItemTextColor
import com.todocomposeapp.ui.theme.taskItembackgroudColor
import com.todocomposeapp.util.RequestState
import com.todocomposeapp.util.SearchAppBarState

@Composable
fun ListContent(
    allTasks: RequestState<List<ToDoTask>>,
    searchedTasks: RequestState<List<ToDoTask>>,
    navigateToTaskScreens: (taskId: Int) -> Unit,
    searchAppBarState: SearchAppBarState
) {
    if (searchAppBarState == SearchAppBarState.TRIGGERED) {
        if (searchedTasks is RequestState.Succes) {
            HandleListContent(
                tasks = searchedTasks.data,
                navigateToTaskScreens = navigateToTaskScreens
            )
        }
    } else {
        if (allTasks is RequestState.Succes) {
            HandleListContent(
                tasks = allTasks.data,
                navigateToTaskScreens = navigateToTaskScreens
            )
        }
    }
}

@Composable
fun HandleListContent(
    tasks: List<ToDoTask>,
    navigateToTaskScreens: (taskId: Int) -> Unit
) {
    if (tasks.isEmpty())
        EmptyContent()
    else
        DisplayTasks(
            tasks = tasks,
            navigateToTaskScreens = navigateToTaskScreens
        )
}

@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>,
    navigateToTaskScreens: (taskId: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(top = TOP_APP_BAR_HEIGHT)
    ) {
        items(
            items = tasks,
            key = { task ->
                task.id
            }
        ) { task ->
            TaskItem(
                toDoTask = task,
                navigateToTaskScreens = navigateToTaskScreens
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreens: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.taskItembackgroudColor,
        tonalElevation = TASK_ITEM_ELEVATION,
        shape = RectangleShape,
        onClick = {
            navigateToTaskScreens(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    color = MaterialTheme.colorScheme.taskItemTextColor,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(
                        modifier = Modifier
                            .width(PRIORITY_INDICATOR_SIZE)
                            .height(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }
                }
            }
            Text(
                text = toDoTask.description,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.taskItemTextColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
@Preview
private fun TaskItemPreview() {
    TaskItem(
        toDoTask = ToDoTask(
            0,
            "title",
            "Description",
            Priority.HIGH
        ), navigateToTaskScreens = {}
    )
}