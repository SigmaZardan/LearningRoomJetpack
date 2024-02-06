package com.example.todoapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.model.ToDo
import com.example.todoapp.ui.navigation.NavigationDestination
import com.example.todoapp.ui.screens.common.ToDoTopAppBar


object HomeDestination : NavigationDestination {
    override val route: String
        get() = "home_screen"


    override val titleRes: Int
        get() = R.string.app_name

}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onAddToDoClicked: () -> Unit = {}) {
    Scaffold(
        modifier = modifier, topBar = {
            ToDoTopAppBar(
                canNavigateBack = false,
                currentTitle = stringResource(id = HomeDestination.titleRes)
            )
        }, floatingActionButton = {
            FloatingActionButton(onClick = { onAddToDoClicked() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add ToDo")
            }
        }) { paddingValues ->
        HomeBody(todos = listOf(), modifier = Modifier.padding(paddingValues))
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}


@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    todos: List<ToDo>,
    onTodoClicked: (Int) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(todos, key = { it.id }) { item ->
            ToDoListItem(item, onToDoClicked = onTodoClicked)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeBodyPreview() {
    val todos = listOf(
        ToDo(1, "I have to do something.", title = "Something"),
        ToDo(2, "I have to go for a run.", title = "Running"),
        ToDo(3, "I have to make sure I dance.", title = "Dance"),
        ToDo(4, "Working Hard.", title = "Stay hard"),
        ToDo(5, "Making something to eat.", title = "Eat")
    )
    HomeBody(todos = todos, onTodoClicked = { })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListItem(todo: ToDo, modifier: Modifier = Modifier, onToDoClicked: (Int) -> Unit = {}) {
    Card(modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(topStart = 25.dp, bottomEnd = 25.dp),
        onClick = {
            onToDoClicked(todo.id)
        }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                todo.title, style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                todo.description, style = MaterialTheme.typography.bodyLarge
            )


        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ToDoListItemPreview() {
    ToDoListItem(todo = ToDo(id = 1, description = "I have to study Kotlin", title = "Study"))
}