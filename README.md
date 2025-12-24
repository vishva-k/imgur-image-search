# Imgur Image Search

An Android app that lets users search for images using the Imgur API, browse results in a grid, and view images full screen.

Built as part of a coding challenge with a focus on clean structure, clear state handling, and a smooth user experience.

---

## Features

- Search images using a keyword  
- Responsive image grid  
- Tap an image to view it full screen  
- Clear separation between UI, ViewModel, and network layers  

---

## Tech Stack

- Kotlin  
- Jetpack Compose  
- ViewModel + Compose State  
- Retrofit + OkHttp  
- Coil (image loading)  
- Imgur API (Client-ID authentication)

---

## How It Works

1. The user enters a search term and starts a search.
2. The app queries Imgur’s gallery search endpoint.
3. Results are mapped to valid image URLs.
4. Images are displayed in a grid.
5. Selecting an image opens a full-screen view.

---

## Project Structure

- `ui/` – Compose UI components (`SearchScreen`, `ImageGrid`, `ImageDetailScreen`)
- `viewmodel/` – `ImageSearchViewModel`
- `network/` – Retrofit API, client setup, models, and helpers

---

## Author

Built by **Vishva Koladiya**
