# Requirements
The API to be used is [GitHub API v3](https://developer.github.com/v3/

## Minimum Specifications
Please implement the following specifications. You may include additional functions and ideas.

### User list screen
- Display the user list as a list.
- Elements required for each line:
    - Icon image
    - User name
- Selecting each line moves to the user repository screen.
- Display a search string input column at the top of the screen, fixed at the top.
- Display a list of users below the input field based on the input string.

### User repository screen
1. Displays detailed user information at the top of the list.
    - Elements required:
        - Icon image
        - User Name
        - Full name
        - Number of followers
        - Number of following
2. List repositories of users who are not forking repositories below
    - Elements required:
        - Repository name
        - Repository language
        - Number of stars
        - Description
    - Tap a line in the repository list to display the repository URL in WebView