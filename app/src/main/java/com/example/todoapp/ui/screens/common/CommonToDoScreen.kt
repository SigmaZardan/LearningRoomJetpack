package com.example.todoapp.ui.screens.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.ui.screens.todo.InputValidation
import com.example.todoapp.ui.screens.todo.ToDoDetails


@Composable
fun CommonToDoScreen(
    modifier: Modifier = Modifier,
    inputValidation: InputValidation,
    toDoDetails: ToDoDetails,
    onToDoValueChange: (ToDoDetails) -> Unit = {},
    onSaveButtonClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CommonInputForm(
            inputValidation = inputValidation,
            toDoDetails = toDoDetails,
            onToDoValueChange = onToDoValueChange
        )
        Button(onClick = onSaveButtonClick) {
            Text("Save ToDo")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CommonToDoScreenPreview() {
    CommonToDoScreen(inputValidation = InputValidation(), toDoDetails = ToDoDetails())
}

@Composable
fun CommonInputForm(
    modifier: Modifier = Modifier,
    inputValidation: InputValidation,
    toDoDetails: ToDoDetails,
    onToDoValueChange: (ToDoDetails) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CommonTextField(
            labelRes = R.string.to_do_title,
            value = toDoDetails.title,
            onValueChange = {
                onToDoValueChange(toDoDetails.copy(title = it))
            },
            imeAction = ImeAction.Next,
            isError = inputValidation.isTitleEmpty,
            errorRes = R.string.title_cannot_be_empty
        )
        CommonTextField(
            labelRes = R.string.to_do_description,
            value = toDoDetails.description,
            onValueChange = {
                onToDoValueChange(toDoDetails.copy(description = it))
            },
            imeAction = ImeAction.Done,
            isError = inputValidation.isDescriptionEmpty,
            errorRes = R.string.description_cannot_be_empty
        )
    }
}


@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    @StringRes labelRes: Int,
    @StringRes errorRes: Int,
    value: String,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction,
    isError: Boolean
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { if (isError) Text(stringResource(id = errorRes)) else Text(stringResource(id = labelRes)) },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = imeAction),
        singleLine = true,
        isError = isError,
    )
}