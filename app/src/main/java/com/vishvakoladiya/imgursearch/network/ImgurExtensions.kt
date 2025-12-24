package com.vishvakoladiya.imgursearch.network

// extract image URL from imgur item
fun ImgurItem.getImageUrl(): String? {
    val image = when {
        !images.isNullOrEmpty() -> images.first()
        else -> null
    }

    // return valid images only
    return if (image != null && image.type.startsWith("image")) {
        image.link
    } else {
        null
    }
}
