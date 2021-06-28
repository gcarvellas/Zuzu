package callofthedragon.zuzu.commands;

import callofthedragon.zuzu.commands.resources.MessageSender;
import callofthedragon.zuzu.commands.resources.contactmanager.Contact;
import callofthedragon.zuzu.commands.resources.contactmanager.ContactListManager;
import callofthedragon.zuzu.commands.resources.parsers.MessageParser;
import callofthedragon.zuzu.config.ConfigParser;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;

public class Messenger extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");
        try {
            if (args[0].equalsIgnoreCase(ConfigParser.getPrefix() + "message") && args.length > 1) {
                String message = String.join(" ", Arrays.copyOfRange(args, 2, args.length)) + "\nMessage from " + event.getAuthor();
                if (MessageParser.isUser(args[1]) || args[1].matches("^[a-zA-Z0-9]+$")) { //&message CONTACT message... or //&call NAME message
                    Contact contact = ContactListManager.getContactByName(args[1]);
                    MessageSender.messagePending(event, contact.getName(), contact.getNumber());
                    message(event, contact.getNumber(), contact.getName(), message);
                } else if (args[1].matches("^[a-zA-Z0-9]+$")) {
                    Contact contact = ContactListManager.getContactByName(args[1]);
                    MessageSender.messagePending(event, contact.getName(), contact.getNumber());
                    message(event, contact.getNumber(), contact.getName(), message);
                } else { //&message PHONE_NUMBER message...
                    MessageSender.messagePending(event, "", args[1]);
                    message(event, args[1], "", message);
                }
            }
        } catch (NullPointerException e){
            MessageSender.errorMessage(event, e);
        }
    }
    private void message(GuildMessageReceivedEvent event, String ID, String name, String messageContent){
        try {
            Message message = Message.creator(
                    new PhoneNumber(ID),
                    new PhoneNumber(ConfigParser.getPhoneNumber()),
                    messageContent)
                    .create();
            if (name=="")
                MessageSender.messageSuccess(event, ID);
            else
                MessageSender.messageSuccess(event, name);
        } catch (ApiException e){
            MessageSender.errorMessage(event, e);
        }
    }
}
