package com.cgesgin;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import com.cgesgin.api.GithubApi;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GithubApi githubApi = new GithubApi();

        while (true) {

            showCommand();

            String username = scanner.nextLine().trim();

            if (username.isEmpty()) {
                System.out.println("Username cannot be empty!");
                continue;
            }

            switch (username) {
                case "exit":
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    try {
                        String events = githubApi.getUserEvents(username);
                        if (events.length()==2) {
                            System.out.println("\nplease enter a valid username\n");
                        }
                        extractActivities(events);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
            }
        }
    }

    private static void showCommand() {
        System.out.println("exit: Exit the program");
        System.out.print("Enter a GitHub username to see user events : ");
    }

    public static void extractActivities(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode activities = objectMapper.readTree(jsonResponse);

            Iterator<JsonNode> elements = activities.elements();

            while (elements.hasNext()) {
                JsonNode event = elements.next();
                String eventType = event.get("type").asText();

                String repoName = event.get("repo").get("name").asText();

                switch (eventType) {
                    case "PushEvent":
                        int commitCount = event.get("payload").get("size").asInt();
                        System.out.println("- Pushed " + commitCount + " commits to " + repoName);
                        break;
                    case "CreateEvent":
                        String refType = event.get("payload").get("ref_type").asText();
                        if ("repository".equals(refType)) {
                            System.out.println("- Created a new repository: " + repoName);
                        } else if ("branch".equals(refType)) {
                            System.out.println("- Created a new branch in " + repoName);
                        }
                        break;
                    case "DeleteEvent":
                        String deleteRefType = event.get("payload").get("ref_type").asText();
                        if ("branch".equals(deleteRefType)) {
                            System.out.println("- Deleted a branch in " + repoName);
                        } else if ("tag".equals(deleteRefType)) {
                            System.out.println("- Deleted a tag in " + repoName);
                        }
                        break;
                    case "IssuesEvent":
                        System.out.println("- Opened a new issue in " + repoName);
                        break;
                    case "PullRequestEvent":
                        String prAction = event.get("payload").get("action").asText();
                        System.out.println("- " + prAction + " a pull request in " + repoName);
                        break;
                    case "PullRequestReviewEvent":
                        String prReviewAction = event.get("payload").get("action").asText();
                        System.out.println("- " + prReviewAction + " a pull request review in " + repoName);
                        break;
                    case "WatchEvent":
                        System.out.println("- Starred " + repoName);
                        break;
                    case "ReleaseEvent":
                        System.out.println("- Published a release in " + repoName);
                        break;
                    case "ForkEvent":
                        System.out.println("- Forked the repository " + repoName);
                        break;
                    case "MemberEvent":
                        String memberAction = event.get("payload").get("action").asText();
                        System.out.println(
                                "- " + memberAction + " a member to the organization for repository " + repoName);
                        break;
                    case "GollumEvent":
                        System.out.println("- Edited wiki in " + repoName);
                        break;
                    case "PublicEvent":
                        System.out.println("- Made repository public: " + repoName);
                        break;
                    case "TeamAddEvent":
                        System.out.println("- Added a user to a team in " + repoName);
                        break;
                    case "CommitCommentEvent":
                        System.out.println("- Added a comment to a commit in " + repoName);
                        break;
                    default:
                        break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
