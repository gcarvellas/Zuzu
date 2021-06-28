package callofthedragon.zuzu.commands;

import callofthedragon.zuzu.commands.resources.MessageSender;
import callofthedragon.zuzu.commands.resources.contactmanager.Contact;
import callofthedragon.zuzu.commands.resources.contactmanager.ContactListManager;
import callofthedragon.zuzu.commands.resources.parsers.MessageParser;
import callofthedragon.zuzu.config.ConfigParser;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SetName extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(ConfigParser.getPrefix() + "setName") && args.length > 2) {
            try {
                if (MessageParser.isUser(args[1])) {
                    ContactListManager.setName(args[1], args[2]);
                    MessageSender.setName(event, args[2]);
                }
                else
                    MessageSender.errorMessage(event, new IllegalArgumentException("Invalid syntax"));
            } catch (IllegalArgumentException e){
                MessageSender.errorMessage(event, e);
            } catch (NullPointerException e){
                MessageSender.errorMessage(event, e);
            }
        }
    }
}
