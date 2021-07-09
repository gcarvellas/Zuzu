package callofthedragon.zuzu.commands.resources.web;

import callofthedragon.zuzu.commands.resources.MessageSender;
import com.github.axet.vget.VGet;
import com.hp.gagawa.java.elements.P;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class YoutubeHandler {

    public YoutubeHandler(GuildMessageReceivedEvent event, URL link){
        try {
            //downloadVideo(event, new URL(link + "?vq=small"));
            downloadVideo(event, new URL("https://www.youtube.com/watch?v=dWgRm-_DQD0?vq=small"));
        } catch (MalformedURLException e){
            MessageSender.errorMessage(event, e);
        }
    }

    private void downloadVideo(GuildMessageReceivedEvent event, URL link){
        try {
            VGet vg = new VGet(link, File.createTempFile(String.valueOf(link.toString().hashCode()), "mp4"));
            vg.download();
        } catch (Exception e) {
            MessageSender.errorMessage(event, new RuntimeException(e));
        }
    }

    public static String addYoutubeURL(GuildMessageReceivedEvent event, String message){
        String[] messageAsList = message.split(" ");
        for (int i=0; i< messageAsList.length; i++){
            if (isValidURL(messageAsList[i])){
                MessageSender.detectedYoutubeURL(event, messageAsList[i]);
                //MP3Handler handler = new MP3Handler(event);
                //handler.stringToMP3String(messageAsList[i]);
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
