package github.user.activity.cli.excpetion;

public class UsernameNotFoundException extends Exception {

    public UsernameNotFoundException(String username) {
        super("Username not found: " + username);
    }

    public UsernameNotFoundException(String username, Throwable cause) {
        super("Username not found: " + username, cause);
    }
}