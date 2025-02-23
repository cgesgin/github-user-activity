# 🎯 Overview
Solution to the roadmap.sh project https://roadmap.sh/projects/github-user-activity

## 🔍 Features:

- ✅ Fetch user activities (commits, issues opened, starred repositories)
- ✅ Display the number of commits pushed to repositories
- ✅ Show the issues opened by the user
- ✅ Track repositories that the user has starred

## 💾 Data Source:

All data is fetched directly from GitHub using the GitHub API. The user is required to provide a GitHub username, and the system will retrieve relevant data (such as commits, issues, and starred repositories) for that user.

## 🛠️ Technical Details: This project is built using:

- Java 19
- Maven for build management (version 3.9.9)
- Jackson for JSON processing
- GitHub API for retrieving user activities
- Command-line interface (CLI) for interaction

## 🏃 How to Run: To get started with the project, follow these steps:

- Clone the repository:
        
        git clone https://github.com/cgesgin/github-user-activity.git

- Navigate to the project directory:

        cd github-user-activity-tracker

- Build the project:

        mvn clean install

Run the application:

        mvn exec:java

## 📘 Usage Example: 

After running the application, you will be prompted to enter a GitHub username. The program will then fetch and display a list of activities (e.g., commits, issues opened, starred repositories) for the specified user.

Example output:

Enter GitHub username: cgesgin
Fetching activities for user: cgesgin...

- Pushed 3 commits to johndoe/tasktrackercli
- Opened a new issue in johndoe/tasktrackercli
- Starred johndoe/tasktrackercli
