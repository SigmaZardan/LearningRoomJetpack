package com.example.todoapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todoapp.ui.screens.home.HomeDestination
import com.example.todoapp.ui.screens.home.HomeScreen
import com.example.todoapp.ui.screens.todo.ToDoEntryDestination
import com.example.todoapp.ui.screens.todo.ToDoEntryScreen


@Composable
fun ToDoNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = HomeDestination.route) {
        composable(route = HomeDestination.route) {
            HomeScreen(onAddToDoClicked = {
                navController.navigate(ToDoEntryDestination.route)
            })
        }

        composable(route = ToDoEntryDestination.route) {
            ToDoEntryScreen(navigateUp = { navController.popBackStack() })
        }
    }

}