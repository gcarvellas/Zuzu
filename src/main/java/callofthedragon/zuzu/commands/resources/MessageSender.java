package callofthedragon.zuzu.commands.resources;

import callofthedragon.zuzu.config.ConfigParser;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class MessageSender {
    public static void callPending(GuildMessageReceivedEvent event, String name, String phoneNumber){
        String text;
        if (name == "")
            text = phoneNumber;
        else
            text = name;
        event.getChannel().sendMessage("```fix\nCalling " + text +  "...\n```").queue();
    }

    public static void messagePending(GuildMessageReceivedEvent event, String name, String phoneNumber){
        String text;
        if (name == "")
            text = phoneNumber;
        else
            text = name;
        event.getChannel().sendMessage("```fix\nMessaging " + text +  "...\n```").queue();
    }

    public static void setName(GuildMessageReceivedEvent event, String name){
        event.getChannel().sendMessage("```diff\n+ Set new name to " + name + "\n```").queue();
    }

    public static void removeContact(GuildMessageReceivedEvent event, String name){
        event.getChannel().sendMessage("```diff\n+ Removed " + name + "\n```").queue();
    }

    public static void callSuccess(GuildMessageReceivedEvent event, String number){
        event.getChannel().sendMessage("```diff\n+ Successfully called " + number+"\n```").queue();
    }

    public static void messageSuccess(GuildMessageReceivedEvent event, String number){
        event.getChannel().sendMessage("```diff\n+ Successfully messaged " + number+"\n```").queue();
    }

    public static void errorMessage(GuildMessageReceivedEvent event, Exception e){
        event.getChannel().sendMessage("```diff\n- Error! " + e.getMessage() + "\n```").queue();
        e.printStackTrace();
    }

    public static void shutdown(GuildMessageReceivedEvent event){
        event.getChannel().sendMessage("```diff\n- Shutting down bot...\n```").queue();
    }

    public static void addContact(GuildMessageReceivedEvent event, String name, String number){
        event.getChannel().sendMessage("```diff\n+ Added " + name + " with phone number " + number + "\n```").queue();
    }

    public static void detectedYoutubeURL(GuildMessageReceivedEvent event, String url){
        event.getChannel().sendMessage("```fix\n Youtube URL Detected! " + "Fetching from https://www.y2mate.com/youtube-mp3/...\n```").queue();
    }

    public static void successfulYoutubeURL(GuildMessageReceivedEvent event){
        event.getChannel().sendMessage("```diff\n+ Successfully fetched youtube video!\n```").queue();
    }

    public static void stealthModeStatus(GuildMessageReceivedEvent event){
        event.getChannel().sendMessage("```fix\n Stealth mode: " + ConfigParser.isStealthMode() + "\n```").queue();
    }

    public static void changeStealthStatus(GuildMessageReceivedEvent event, boolean result){
        event.getChannel().sendMessage("```diff\n+ Successfully changed stealth mode to " + Boolean.toString(result) + "!\n```").queue();
    }
}
