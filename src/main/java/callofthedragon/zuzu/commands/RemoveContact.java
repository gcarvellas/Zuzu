package callofthedragon.zuzu.commands;

import callofthedragon.zuzu.commands.resources.MessageSender;
import callofthedragon.zuzu.commands.resources.contactmanager.Contact;
import callofthedragon.zuzu.commands.resources.contactmanager.ContactListManager;
import callofthedragon.zuzu.commands.resources.parsers.MessageParser;
import callofthedragon.zuzu.config.ConfigParser;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RemoveContact extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(ConfigParser.getPrefix() + "removeContact") && args.length > 1) {
            try {
                if (MessageParser.isUser(args[1])) {
                    Contact contact = ContactListManager.getContact(args[1]);
                    ContactListManager.removeContact(contact);
                    MessageSender.removeContact(event, contact.getName());
                } else if (args[1].matches("^[a-zA-Z0-9]+$")) {
                    Contact contact = ContactListManager.getContactByName(args[1]);
                    ContactListManager.removeContact(contact);
                    MessageSender.removeContact(event, contact.getName());
                }
                else
                    MessageSender.errorMessage(event, new IllegalArgumentException("Invalid syntax"));
            } catch (IllegalArgumentException e){
                MessageSender.errorMessage(event, e);
            }
        }
    }
}
