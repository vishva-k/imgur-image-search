package com.vishvakoladiya.imgursearch.network

// responses returned by imgur api
data class ImgurResponse(
    val data: List<ImgurItem>,
    val success: Boolean,
    val status: Int
)

// gallery items
data class ImgurItem(
    val id: String,
    val title: String?,
    val link: String?,
    val images: List<ImgurImage>?,
    val is_album: Boolean
)

// image inside an album
data class ImgurImage(
    val id: String,
    val link: String,
    val type: String
)