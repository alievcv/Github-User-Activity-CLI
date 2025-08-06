# GitHub User Activity CLI

A simple command-line interface (CLI) application that fetches and displays the **recent public activity** of any GitHub user.

This project is built in **pure Java**, without using any external libraries or frameworks. It interacts directly with the [GitHub Events API](https://docs.github.com/en/rest/activity/events?apiVersion=2022-11-28) and parses the raw JSON response manually.

---

## 🚀 Features

- Accepts a GitHub username as a command-line argument
- Sends HTTP requests to GitHub’s public API
- Parses JSON responses using built-in Java tools
- Outputs user activity in a human-readable format
- Handles errors gracefully (e.g., invalid username, no activity)

---

## 🛠 Example Usage

```bash
# Compile the app
javac -d out src/github/user/activity/cli/*.java

# Run the app
java -cp out github.user.activity.cli.App torvalds


⚙️ Tech Stack
Language: Java 17+

API: GitHub REST API v3

HTTP client: java.net.http.HttpClient

JSON Parsing: Manual string operations (no libraries)

📦 Build Tools
You can use any of the following (optional):

javac and java (manual build)

Gradle or Maven (for packaging or running tests)

🧠 Educational Value
This project is great for:

Practicing Java's built-in HTTP and I/O libraries

Strengthening your understanding of JSON formats

Getting comfortable with real-world API integration

Building lightweight tools with no dependencies
