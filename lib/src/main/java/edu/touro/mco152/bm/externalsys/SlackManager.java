package edu.touro.mco152.bm.externalsys;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.api.ApiTestResponse;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import edu.touro.mco152.bm.Observer;

import java.io.*;
import java.util.logging.*;

/**
 * A Slack Manager for BadBM that just knows how to send a string msg to a pre-designated channel
 * whose token is in an external file.
 * Usage:
 * SlackManager slackmgr = new SlackManager("myAppName");
 * Boolean worked = slackmgr.postMsg2OurChannel(":smile: Benchmark completed");
 */
public class SlackManager {
    private static Logger log = Logger.getLogger("SlackManager");
    private static Slack slack = null;  // obtain/keep one copy of expensive item

    /**
     * The channel we will send all our messages to
     */
    private final String ourChannel = "mco152_auto_notifications";
    private String appName = "App";

    private SlackManager() {
        // disallow private constructor
    }

    /**
     * Construct a Slack Manager for our application, to be used for sending messages to Slack
     *
     * @param appName - pass a sring like BadBM, or whatever name you want to appear in msgs
     */
    public SlackManager(String appName) {
        this.appName = appName;

        if (slack == null)
            slack = Slack.getInstance();

        // auto-validate environment
        try {
            ApiTestResponse testResponse = slack.methods().apiTest(r -> r.foo("bar"));
            if (!testResponse.isOk()) {
                log.severe("Problem with auto-validation of Slack: "
                        + testResponse.getError());
            }
        } catch (IOException | SlackApiException exc) {
            log.log(Level.SEVERE, "SlackManager: Problem with auto-validation of Slack", exc);
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getenv("USER"));
        SlackManager slackmgr = new SlackManager("BadBM");
        //Boolean worked = slackmgr.postMsg2OurChannel(":cry: Benchmark failed");
        Boolean worked = slackmgr.postMsg2OurChannel("token retrieved from a file successfully");
        log.info("Retcode from test sending msg is " + worked);
    }

    /**
     * Post slack message to our pre-defined channel using predefined credential token
     *
     * @param msg - String with message to post, can contain emojis like :smile:
     * @return True if successful
     */
    public Boolean postMsg2OurChannel(String msg) {
        // Initialize an API Methods client with the given token
        MethodsClient methods = slack.methods(getSlackToken());

        String postText = String.format("Automated message from <%s>%s: %s",
                System.getProperty("user.name"), appName, msg);

        // Build a request object
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(ourChannel)
                .text(postText)
                .build();

        // Get a response as a Java object
        ChatPostMessageResponse response = null;
        try {
            response = methods.chatPostMessage(request);
        } catch (IOException | SlackApiException exc) {
            log.log(Level.SEVERE, "SlackManager: Problem with execution of chatPostMessage", exc);
            return false;
        }


        if (response.isOk()) {
            return true;
        } else {
            log.severe("SlackManager: Problem with Response = " + response);
            return false;
        }
    }

    /**
     * Obtain Slack security token for our bot/app/channel from file slacktoken.txt.
     * Cant be in code in case gets checked in to Github,
     * which is illegal acc to Slack and will get invalidated when detected.
     * <p>
     * If the token is a bot token, it starts with `xoxb-` while if it's a user token, it starts with `xoxp-`
     */
    private String getSlackToken() {
        String token = "Unknown Slack Token";
        String tfname = System.getenv("TMPDIR") + File.separator + "slacktoken.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(tfname));
            token = br.readLine();
        } catch (FileNotFoundException e) {
            log.severe("Could not find token file: " + tfname);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Error while read token", e);
        }

        return token;   // may be actual token or dummy string
    }
}
