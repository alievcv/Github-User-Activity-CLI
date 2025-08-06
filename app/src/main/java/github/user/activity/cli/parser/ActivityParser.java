package github.user.activity.cli.parser;

public class ActivityParser {

    public static void activityParser(String response) {

        String[] events = response.split("(?=\"id\"\\s*:\\s*\"\\d+\"\\s*,\\s*\"type\"\\s*:\\s*\"\\w+Event\")");

        for (String event : events) {
            System.out.println(extractTypeOfEvent(event));
            System.out.println("\n");
        }

    }

    public static String extractTypeOfEvent(String event) {

        try {
            int start = event.indexOf("type", 0, 100);
            int end = event.indexOf(',', start, start * start);

            int startTypeOfEvent = 0;

            for (int i = end - 2; i > 0; i--) {
                if (event.charAt(i) == '"') {
                    startTypeOfEvent = i;
                    break;
                }

            }

            String typeOfEvent = event.substring(startTypeOfEvent + 1, end - 1);
            String action = extractActionFromPayload(event, typeOfEvent);
            String repo = extractRepo(event);

            if (typeOfEvent.equalsIgnoreCase("PushEvent")) {
                return "Event: " + typeOfEvent + " - " + "Commits: " + action + " - " + "Repository: " + repo;

            }
            return "Event: " + typeOfEvent + " - " + "Action: " + action + " - " + "Repository: " + repo;

        } catch (Exception exception) {

        }
        return "We couldn't reach the event name";

    }

    private static String extractActionFromPayload(String event, String eventType) {
        try {
            int start = event.indexOf("payload");

            if (eventType.equalsIgnoreCase("PushEvent")) {
                int indexOfSize = event.indexOf("size", start);
                int endOfCommitSize = event.indexOf(',', indexOfSize, indexOfSize + 100);

                return event.substring(endOfCommitSize - 1, endOfCommitSize);

            } else {

                int end = event.indexOf(',', start, start * 2);

                int startTypeOfEvent = 0;

                for (int i = end - 2; i > 0; i--) {
                    if (event.charAt(i) == '"') {
                        startTypeOfEvent = i;
                        break;
                    }

                }

                return event.substring(startTypeOfEvent + 1, end - 1);
            }
        } catch (Exception exception) {

        }
        return "We couldn't reach the payload action";

    }

    private static String extractRepo(String event) {

        try {
            int repoIndex = event.indexOf("\"repo\"");
            if (repoIndex == -1)
                return "repo field not found";

            int repoNameIndex = event.indexOf("\"name\"", repoIndex);
            if (repoNameIndex == -1)
                return "repo-name field not found";

            int endQuoteIndex = event.indexOf(",", repoNameIndex, repoNameIndex + 200);
            if (endQuoteIndex == -1)
                return "end quote not found";

            for (int i = endQuoteIndex - 2; i > 0; i--) {
                if (event.charAt(i) == '"') {
                    repoNameIndex = i;
                    break;
                }

            }

            return event.substring(repoNameIndex + 1, endQuoteIndex - 1);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return "We couldn't get repo";
    }
}
