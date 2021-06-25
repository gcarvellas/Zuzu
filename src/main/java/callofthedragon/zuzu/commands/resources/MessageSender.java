package callofthedragon.zuzu.commands.resources;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class MessageSender {
    public static void callPending(GuildMessageReceivedEvent event, String number){
        event.getChannel().sendMessage("```fix\nCalling " + number +  "...\n```").queue();
    }

    public static void callSuccess(GuildMessageReceivedEvent event, String number){
        event.getChannel().sendMessage("```diff\n+ Successfully called " + number+"\n```").queue();
    }

    public static void errorMessage(GuildMessageReceivedEvent event, Exception e){
        event.getChannel().sendMessage("```diff\n- Error! " + e.getMessage() + "\n```").queue();
    }


    public static void addContact(GuildMessageReceivedEvent event, User user, String number){
            event.getChannel().sendMessage("```diff\n+ Added " + user.getName() + " with phone number " + number + "\n```").queue();
    }
    public static void addContact(GuildMessageReceivedEvent event, User user, String number, String nick){
        event.getChannel().sendMessage("```diff\n+ Added " + user.getName() + " with phone number " + number + " with nickname: " + nick + "\n```").queue();
    }
}
