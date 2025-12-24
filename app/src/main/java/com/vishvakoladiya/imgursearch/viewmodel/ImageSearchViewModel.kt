package com.vishvakoladiya.imgursearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishvakoladiya.imgursearch.network.ApiClient
import com.vishvakoladiya.imgursearch.network.getImageUrl
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class ImageSearchViewModel : ViewModel() {

    // URLs from imgur results
    var imageUrls by mutableStateOf<List<String>>(emptyList())
        private set

    // show/hide loading
    var isLoading by mutableStateOf(false)
        private set

    fun search(query: String) {
        viewModelScope.launch {
            isLoading = true
            try {
                val response = ApiClient.imgurApi.searchImages(query)
                // get an image URL form each item
                imageUrls = response.data
                    .mapNotNull { it.getImageUrl() }
            } catch (e: Exception) {
                imageUrls = emptyList()
            } finally {
                isLoading = false
            }
        }
    }
}