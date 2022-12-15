package com.example.youtube_searchapi

import androidx.compose.material3.*
import androidx.compose.runtime.*



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerMenu() {
    val options = listOf("최신", "평가", "관련도", "제목", "조회수")

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text("") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
//            options.forEach { selectionOption ->
//                DropdownMenuItem(
//                    onClick = {
//                        selectedOptionText = selectionOption
//                        expanded = false
//                    }
//                ) {
//
//                }
//            }
        }
    }
}
