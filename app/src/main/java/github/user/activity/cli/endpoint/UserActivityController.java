package github.user.activity.cli.endpoint;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

import github.user.activity.cli.excpetion.UsernameNotFoundException;

public class UserActivityController {

    public static String getUserActivity(String username)
            throws IOException, InterruptedException, UsernameNotFoundException {

        String uri = "https://api.github.com/users/" + username + "/events";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))

                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        // Files.writeString(Path.of("github.json"), response.body());

        System.out.println("Response Code: " + response.statusCode());

        if (response.statusCode() == 404) {
            throw new UsernameNotFoundException("Username is not found");
        }

        return response.body();

    }

}
