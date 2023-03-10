# User Repo Merge
This application contains an API which merges the data from a Github User
and the repositories they contribute to.

## How to Use
This application uses an embedded Tomcat server that comes with Spring Boot. Clone this repository
and run UserRepoMergeApplication.java in your java IDE (IntelliJ, Eclipse, etc.) to start the embedded server.

From there, you can access the API at http://localhost:8080/user/{username} and replace {username} with the
requested user to get the data for (example: http://localhost:8080/user/octocat )

## Architecture
This is a simple Spring Boot Web application using Jackson annotations to do the mapping from the GitHub APIs
to the requested JSON format for the assignment.

There are two services implemented. The UserService accesses the GitHub API with RestTemplate to get the User details,
while the RepoService accesses the GitHub API to get the users repositories.

The web controller UserController has these services as dependencies and combines the results into the User object
for return. We can mock these dependencies in our unit tests using Mockito.
