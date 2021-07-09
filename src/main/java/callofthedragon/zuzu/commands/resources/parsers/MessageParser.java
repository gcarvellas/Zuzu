package callofthedragon.zuzu.commands.resources.parsers;

import callofthedragon.zuzu.commands.resources.MessageSender;
import callofthedragon.zuzu.commands.resources.gagawa.Play;
import callofthedragon.zuzu.commands.resources.gagawa.Response;
import callofthedragon.zuzu.commands.resources.gagawa.Say;
import callofthedragon.zuzu.commands.resources.web.YoutubeHandler;
import com.hp.gagawa.java.FertileNode;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageParser {

    public static String convertToTwiml(GuildMessageReceivedEvent event, String[] args){
        Response head = new Response();
        FertileNode childReference = head;
        for (String arg: args){
            if (isURL(arg)){
                try {
                    URL link = new URL(arg);
                    String linkHost = link.getHost().replaceAll("//", "");
                    if (linkHost.equals("www.youtube.com")){
                        MessageSender.detectedYoutubeURL(event, link.toString());
                        YoutubeHandler downloadedVideo = new YoutubeHandler(event, link);
                    }
                    childReference = new Play().appendText(arg);
                    head.appendChild(childReference);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else {
                if (childReference instanceof Say)
                    ((Say) childReference).appendText(" " + arg);
                else {
                    childReference = new Say().appendText(arg);
                    head.appendChild(childReference);
                }
            }
        }
        return head.write();
    }

    private static boolean isURL(String text){
        try{
            new URL(text);
            return true;
        } catch (MalformedURLException m) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean regex(String regex, String text){
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    public static boolean isUser(String text){
        return MessageParser.regex("<@![0-9]*>", text);
    }

    public static User getUser(GuildMessageReceivedEvent event, String text){
       return event.getChannel().getJDA().retrieveUserById(text.replaceAll("[<@!>]", "")).complete();
    }
}
