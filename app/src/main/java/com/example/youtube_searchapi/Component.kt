package com.example.youtube_searchapi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.youtube_searchapi.theme.Youtube_searchApiTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TestView(vm: SearchViewModel) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val options = listOf("관련도", "평가", "최신", "제목", "조회수")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    var orderText = "relevance"
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (tf, sm, btn) = createRefs()
        TextField(
            modifier = Modifier.constrainAs(tf) {
                start.linkTo(parent.start, margin = 30.dp)
                end.linkTo(sm.start, margin = 10.dp)
            }.onKeyEvent {
                         if(it.key.keyCode == Key.Enter.keyCode) {
                             vm.search(text.text, orderText)
                         }
                false
            },
            value = text,
            onValueChange = {
                text = it
            },
            singleLine = true
        )
        Box(modifier = Modifier.constrainAs(sm) {
            start.linkTo(tf.end, margin = 10.dp)
            end.linkTo(btn.start, margin = 10.dp)
        }) {

// We want to react on tap/press on TextField to show menu
            ExposedDropdownMenuBox(
                modifier = Modifier.width(150.dp),
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {
                TextField(
                    // The `menuAnchor` modifier must be passed to the text field for correctness.
                    modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = selectedOptionText,
                    onValueChange = {},
                    label = { Text("정렬조건") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(selectionOption) },
                            onClick = {
                                when (selectionOption) {
                                    "최신" -> orderText = "date"
                                    "평가" -> orderText = "rating"
                                    "관련도" -> orderText = "relevance"
                                    "제목" -> orderText = "title"
                                    "조회수" -> orderText = "viewCount"
                                }
                                selectedOptionText = selectionOption
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }
        }
        Button(
            onClick = { vm.search(text.text, orderText) },
            Modifier
                .width(30.dp)
                .height(30.dp)
                .constrainAs(btn) {
                    end.linkTo(parent.end, margin = 30.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        ) {}
    }
}

/*@Composable
fun Modifier.clearFocusOnKeyboardDismiss(): Modifier  {
    var isFocused by remember { mutableStateOf(false) }
    var keyboardAppearedSinceLastFocused by remember { mutableStateOf(false) }
    if (isFocused) {
        val imeIsVisible = LocalWindowInsets.current.ime.isVisible
        val focusManager = LocalFocusManager.current
        LaunchedEffect(imeIsVisible) {
            if (imeIsVisible) {
                keyboardAppearedSinceLastFocused = true
            } else if (keyboardAppearedSinceLastFocused) {
                focusManager.clearFocus()
            }
        }
    }
    onFocusEvent {
        if (isFocused != it.isFocused) {
            isFocused = it.isFocused
            if (isFocused) {
                keyboardAppearedSinceLastFocused = false
            }
        }
    }
}*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpinnerMenu() {
    val options = listOf("최신", "평가", "관련도", "제목", "조회수")

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
// We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        modifier = Modifier.width(150.dp),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text("정렬조건") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}
