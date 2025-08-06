package github.user.activity.cli.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import github.user.activity.cli.endpoint.UserActivityController;
import github.user.activity.cli.excpetion.UsernameNotFoundException;
import github.user.activity.cli.parser.ActivityParser;

public class UserActivityService {

    public String getActivity() throws IOException, InterruptedException, UsernameNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter username:");
        String username = reader.readLine();
        String response = UserActivityController.getUserActivity(username);
        ActivityParser.activityParser(response);

        return null;
    }
}
