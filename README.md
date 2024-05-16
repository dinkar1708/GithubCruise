# GithubCruise
 A user-friendly GitHub app for anyone, allowing smooth navigation of users and repositories.

# Test case document 
- https://developer.android.com/kotlin/coroutines/test
- https://developer.android.com/kotlin/coroutines/test#inject-scope
- General guide
  - https://developer.android.com/training/testing/local-tests
  - https://developer.android.com/kotlin/flow/test
# Coding
- Use view model at screen leve - https://youtu.be/pCX9wvu-Bq0?t=470
- UI state - https://developer.android.com/topic/architecture/ui-layer/stateholders#ui-state
- UI state - https://developer.android.com/topic/architecture/ui-layer/state-production#stateflow_3
- Current nav controller following below with bit modification
  - https://github.com/android/compose-samples/blob/master/JetNews/app/src/main/java/com/example/jetnews/ui/JetnewsNavGraph.kt
  - https://github.com/android/architecture-samples/blob/main/app/src/main/java/com/example/android/architecture/blueprints/todoapp/TodoNavGraph.kt

# TODO
- User Repositories API Difficult to use pagination
When utilizing pagination in the user repositories API, it's essential to determine the total count of API results for effective navigation. Follow these steps to discover how to retrieve the total count:
#### Step 1
Access the following API endpoint:
```
https://api.github.com/users/dinkar1708/repos?q=page=1&per_page=2
```
This endpoint will return 2 results. However, it doesn't provide information about the total count of results.
#### Step 2
To obtain the total count, use the following API endpoint:
```
https://api.github.com/users/dinkar1708/repos
```
This endpoint will return 28 results, indicating that there is data available. By comparing the results from Step 1 and Step 2, you can discern the total count. Knowing the total count facilitates proper utilization of pagination.

- Verify code coverage - https://developer.android.com/codelabs/basic-android-kotlin-compose-test-viewmodel#4
- Navigation
  - 
  - try common controller https://github.com/android/nowinandroid/blob/main/app/src/main/kotlin/com/google/samples/apps/nowinandroid/ui/interests2pane/InterestsListDetailScreen.kt

# Try
## Pagination
- https://docs.github.com/en/rest/using-the-rest-api/using-pagination-in-the-rest-api?apiVersion=2022-11-28