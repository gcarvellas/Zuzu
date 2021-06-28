package callofthedragon.zuzu.commands;

import callofthedragon.zuzu.commands.resources.contactmanager.Contact;
import callofthedragon.zuzu.commands.resources.contactmanager.ContactListManager;
import callofthedragon.zuzu.config.ConfigParser;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ListContacts extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        //work in progress. Want to add embed.
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(ConfigParser.getPrefix() + "listContacts")) {
            for (Contact contact : ContactListManager.getContactList().values())
                event.getChannel().sendMessage(contact.toString()).queue();
        }
    }
}