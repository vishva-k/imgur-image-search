package com.vishvakoladiya.imgursearch.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vishvakoladiya.imgursearch.viewmodel.ImageSearchViewModel
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen(
    viewModel: ImageSearchViewModel = viewModel()
) {
    // text being typed
    var query by remember { mutableStateOf("") }
    // last submitted search
    var lastSearchedQuery by remember { mutableStateOf("") }
    // full screen view
    var selectedImageUrl by remember { mutableStateOf<String?>(null) }

    if (selectedImageUrl != null) {
        ImageDetailScreen(
            imageUrl = selectedImageUrl!!,
            onBack = { selectedImageUrl = null }
        )
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Search images") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            if (query.isNotBlank()) {
                                lastSearchedQuery = query
                                viewModel.search(query)
                            } else {
                                lastSearchedQuery = query
                                viewModel.search(query)
                            }
                        }
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (query.isNotBlank()) {
                            // clear results when searching with empty input
                            lastSearchedQuery = query
                            viewModel.search(query)
                        } else {
                            // valid search
                            lastSearchedQuery = query
                            viewModel.search(query)
                        }
                    }
                ) {
                    Text("Search")
                }
            }

            when {
                // show loading
                viewModel.isLoading && lastSearchedQuery.isNotBlank() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 32.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        CircularProgressIndicator()
                    }
                }

                // search was submitted but no results were returned
                lastSearchedQuery.isNotBlank() && viewModel.imageUrls.isEmpty() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 32.dp),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Text("No results found. Try a different search.")
                    }
                }

                // valid search with results
                lastSearchedQuery.isNotBlank() -> {
                    ImageGrid(
                        imageUrls = viewModel.imageUrls,
                        onImageClick = { url ->
                            selectedImageUrl = url
                        }
                    )
                }

                // no search yet
                else -> {
                    Spacer(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}