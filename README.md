## APIs

- https://unsplash.com/documentation#public-authentication
- https://unsplash.com/documentation#list-collections
- https://unsplash.com/documentation#search-photos

## Screenshots

- ![Feeds](https://github.com/sangdepzaiaa/mvvmfirst/blob/master/app/src/main/assets/Screenshot_2025-06-27-15-27-46-963_com.example.myapplication.jpg)
- ![Search](https://github.com/sangdepzaiaa/mvvmfirst/blob/master/app/src/main/assets/Screenshot_2025-06-27-15-27-59-902_com.example.myapplication.jpg)

## TODO

- [x] Create `CollectionItemResponse`.
- [x] Add API endpoint to `UnsplashApiService`.
- [x] Create `AuthorizationInterceptor` and update `UnsplashServiceLocator`.
- [x] Create `CollectionsViewModel`, test API endpoint.
- [x] Setup views
  - [x] Setup views in `FeedsFragment`.
  - [x] Setup views in `FeedCollectionsFragment`.
- [x] Add `CollectionsUiState`, add logics in `CollectionsViewModel`.
- [x] Connect `FeedCollectionsFragment` with `CollectionsViewModel`.
  - [x] Observe ui state and call ViewModel's methods.
  - [x] Add `CollectionItemAdapter`.
  - [x] Config `Glide` to load image.
