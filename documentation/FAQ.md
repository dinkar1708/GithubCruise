## Called API after free limit is over
**Problem**
- {"message":"API rate limit exceeded for 134.180.235.148. (But here's the good news: Authenticated 
requests get a higher rate limit. Check out the documentation for more details.)",
"documentation_url":"https://docs.github.com/rest/overview/resources-in-the-rest-api#rate-limiting"}

**Solution**
- If the Rate Limit is 60 requests per hour without authentication, please use a personal access token.
https://developer.github.com/v3/guides/getting-started/#oauth

## HIlt generator issue
**Problem**
- /GithubCruise/app/build/generated/hilt/component_sources/debug/com/jetpack/compose/github/github/cruise/GithubCruiseApplication_HiltComponents.java:141: error: [Dagger/MissingBinding] com.jetpack.compose.github.github.cruise.repository.user.UserRepository cannot be provided without an @Provides-annotated method.
**Solution**
Fix from DI layer. RepositoryModule -> add below
```
    @Singleton
    @Binds
    abstract fun bindUserRepository(repository: UserRepositoryImpl): UserRepository
```