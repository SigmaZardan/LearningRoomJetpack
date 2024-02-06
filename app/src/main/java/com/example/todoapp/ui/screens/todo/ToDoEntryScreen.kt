package com.example.todoapp.ui.screens.todo

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.R
import com.example.todoapp.ui.AppViewModelProvider
import com.example.todoapp.ui.navigation.NavigationDestination
import com.example.todoapp.ui.screens.common.CommonToDoScreen
import com.example.todoapp.ui.screens.common.ToDoTopAppBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


object ToDoEntryDestination : NavigationDestination {
    override val route: String
        get() = "todo_entry"
    override val titleRes: Int
        get() = R.string.todo_entry

}

@Composable
fun ToDoEntryScreen(
    modifier: Modifier = Modifier,
    todoEntryViewModel: ToDoEntryViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateUp: () -> Unit = {}
) {
    val toDoUiState by todoEntryViewModel.toDoUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = { SnackbarHost(snackBarHostState) }, modifier = modifier, topBar = {
        ToDoTopAppBar(
            canNavigateBack = true,
            currentTitle = stringResource(id = ToDoEntryDestination.titleRes),
            navigateUp = navigateUp
        )
    }) { paddingValues ->
        CommonToDoScreen(modifier = Modifier.padding(paddingValues),
            inputValidation = toDoUiState.inputValidation,
            toDoDetails = toDoUiState.todoDetails,
            onToDoValueChange = {
                todoEntryViewModel.updateUiState(it)
            },
            onSaveButtonClick = {
                coroutineScope.launch {
                    todoEntryViewModel.saveToDo()
                    delay(10)
                    snackBarHostState.showSnackbar(
                        "ToDo Added Successfully!", withDismissAction = true
                    )
                }

            })
    }
}

@Preview
@Composable
fun ToDoEntryScreenPreview() {
    ToDoEntryScreen()
}