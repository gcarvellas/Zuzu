package callofthedragon.zuzu.commands;

import callofthedragon.zuzu.commands.resources.MessageSender;
import callofthedragon.zuzu.commands.resources.contactmanager.Contact;
import callofthedragon.zuzu.commands.resources.contactmanager.ContactListManager;
import callofthedragon.zuzu.config.ConfigParser;
import callofthedragon.zuzu.events.database.Mongo;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Shutdown extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        //work in progress. Want to add embed.
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(ConfigParser.getPrefix() + "shutdown") && event.getAuthor().getId().equals(ConfigParser.getAuthorId())) {
            MessageSender.shutdown(event);
            event.getJDA().shutdown();
        }
    }
}