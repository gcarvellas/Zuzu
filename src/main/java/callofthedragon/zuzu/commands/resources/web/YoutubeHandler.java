package callofthedragon.zuzu.commands.resources.web;

import callofthedragon.zuzu.commands.resources.MessageSender;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.net.URI;
import java.net.URISyntaxException;

public class YoutubeHandler {
    public static String addYoutubeURL(GuildMessageReceivedEvent event, String message){
        String[] messageAsList = message.split(" ");
        for (int i=0; i< messageAsList.length; i++){
            if (isValidURL(messageAsList[i])){
                MessageSender.detectedYoutubeURL(event, messageAsList[i]);
                MP3Handler handler = new MP3Handler(event);
                handler.stringToMP3String(messageAsList[i]);
                //add google drive handler, upload file to google drive and send twilio link to there.
            }
        }
        return messageAsList.toString();
    }

    public static boolean isValidURL(String urlString){
        try{
            URI uri = new URI(urlString);
            return true;
        } catch (URISyntaxException e){
            return false;
        }
    }
}
