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

# TODO
- Find the user repositories API documentation and check how we can find the total count of API results when using pagination. Pagination is working, but I don't know how to count the total results.
```
**User repositories**
- **Doc:** - ??
- **URL:** `https://api.github.com/users/dinkar1708/repos?q=page=1&per_page=2`
```

- UsersListView // TODO Handle restoring the scroll position of the list after the next page data is loaded.
- Check all apis required fields from doc
- Verify code coverage - https://developer.android.com/codelabs/basic-android-kotlin-compose-test-viewmodel#4

# Try
## Pagination
- https://docs.github.com/en/rest/using-the-rest-api/using-pagination-in-the-rest-api?apiVersion=2022-11-28