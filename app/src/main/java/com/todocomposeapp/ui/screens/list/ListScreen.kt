package com.todocomposeapp.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.todocomposeapp.R
import com.todocomposeapp.ui.theme.fabContentColor
import com.todocomposeapp.ui.viewmodels.SharedViewModel
import com.todocomposeapp.util.Action
import com.todocomposeapp.util.SearchAppBarState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel,
) {

    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
    }

    val action by sharedViewModel.action

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchedTasks by sharedViewModel.searchTasks.collectAsState()

    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    val snackbarHostState = remember { SnackbarHostState() }

//    sharedViewModel.handleDatabaseActions(action = action)

    DisplaySnackbar(
        titleTask = sharedViewModel.title.value,
        handleDatabaseAction = { sharedViewModel.handleDatabaseActions(action = action) },
        snackbarHostState = snackbarHostState,
        action = action,
        onUndoClicked = {
            sharedViewModel.action.value = it
        }
    )

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(
                allTasks = allTasks,
                searchAppBarState = searchAppBarState,
                searchedTasks = searchedTasks,
                navigateToTaskScreens = navigateToTaskScreen
            )
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit
) {
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        },
        containerColor = MaterialTheme.colorScheme.fabContentColor
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }
}

@Composable
fun DisplaySnackbar(
    onUndoClicked: (Action) -> Unit,
    titleTask: String,
    handleDatabaseAction: () -> Unit,
    snackbarHostState: SnackbarHostState,
    action: Action
) {
    handleDatabaseAction()
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            scope.launch {
                val snackBarResult = snackbarHostState.showSnackbar(
                    message = "${action.name}: $titleTask",
                    actionLabel = setActionLabel(action)
                )
                undoDeleteTesk(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }
        }
    }
}

private fun setActionLabel(action: Action): String {
    return if (action == Action.UNDO) "UNDO" else "OK"
}

private fun undoDeleteTesk(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed &&
        action == Action.DELETE
    )
        onUndoClicked(Action.UNDO)
}























