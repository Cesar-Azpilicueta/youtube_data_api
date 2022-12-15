package com.example.youtube_searchapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.youtube_searchapi.theme.Youtube_searchApiTheme

class SearchActivity : ComponentActivity() {

    lateinit var searchViewModel: SearchViewModel
    var resultList: ArrayList<SearchItems> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        setContent {
            Youtube_searchApiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        SearchBar()
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Youtube_searchApiTheme {
            SearchBar()
        }
    }

    @Composable
    private fun search(): MutableList<SearchItems> {
        val testList by searchViewModel.liveData.observeAsState(SearchResult())
        return testList.items
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SearchBar() {
        Column() {
            Box {
                var text by remember { mutableStateOf(TextFieldValue("")) }
                TextField(
                    value = text,
                    onValueChange = {
                        text = it
                    }
                )
                Box(
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 10.dp, end = 10.dp)
                ) {
                    Button(
                        onClick = { searchViewModel.search(text.text) },
                        Modifier
                            .width(30.dp)
                            .height(30.dp)
                    ) {}
                }
            }

            SearchList(list = search())
        }
    }

    @Composable
    fun SearchList(list: MutableList<SearchItems>) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            itemsIndexed(
                list
            ) { index, item ->
                SearchItemList(result = item, index = index)
            }
        }
    }

    @Composable
    fun LiveDataLoadingComponent() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
        }
    }
}

