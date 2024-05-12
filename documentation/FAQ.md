## Called API after free limit is over

**Problem**
{"message":"API rate limit exceeded for 134.180.235.148. (But here's the good news: Authenticated 
requests get a higher rate limit. Check out the documentation for more details.)",
"documentation_url":"https://docs.github.com/rest/overview/resources-in-the-rest-api#rate-limiting"}

**Solution**
If the Rate Limit is 60 requests per hour without authentication, please use a personal access token.
https://developer.github.com/v3/guides/getting-started/#oauth

## XYZ