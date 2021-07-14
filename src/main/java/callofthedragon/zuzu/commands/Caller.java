package callofthedragon.zuzu.commands;

import callofthedragon.zuzu.commands.resources.MessageSender;
import callofthedragon.zuzu.commands.resources.contactmanager.Contact;
import callofthedragon.zuzu.commands.resources.contactmanager.ContactListManager;
import callofthedragon.zuzu.commands.resources.parsers.MessageParser;
import callofthedragon.zuzu.commands.resources.web.YoutubeHandler;
import callofthedragon.zuzu.config.ConfigParser;
import com.twilio.exception.ApiException;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import com.twilio.type.PhoneNumber;
import com.twilio.type.Twiml;
import com.twilio.rest.api.v2010.account.Call;

import java.util.Arrays;

public class Caller extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");
        try {
            if (args[0].equalsIgnoreCase(ConfigParser.getPrefix() + "call") && args.length > 1) {
                String message = MessageParser.convertToTwiml(event, Arrays.copyOfRange(args, 2, args.length));
                if (MessageParser.isUser(args[1]) || args[1].matches("^[a-zA-Z0-9]+$")) { //&call CONTACT message... or //&call NAME message
                    Contact contact = ContactListManager.getContactByName(args[1]);
                    if (!ConfigParser.isStealthMode())
                        MessageSender.callPending(event, contact.getName(), contact.getNumber());
                    call(event, contact.getNumber(), contact.getName(), message);
                } else if (args[1].matches("^[a-zA-Z0-9]+$")) {
                    Contact contact = ContactListManager.getContactByName(args[1]);
                    if (!ConfigParser.isStealthMode())
                        MessageSender.callPending(event, contact.getName(), contact.getNumber());
                    call(event, contact.getNumber(), contact.getName(), message);
                } else { //&call PHONE_NUMBER message...
                    if (!ConfigParser.isStealthMode())
                        MessageSender.callPending(event, "", args[1]);
                    call(event, args[1], "", message);
                }
            }
        } catch (NullPointerException e){
            MessageSender.errorMessage(event, e);
        }
    }
    private void call(GuildMessageReceivedEvent event, String ID, String name, String message){
        try {
            Call call = Call.creator(
                    new PhoneNumber(ID),
                    new PhoneNumber(ConfigParser.getPhoneNumber()),
                    new Twiml(message))
                    .create();
            if (!ConfigParser.isStealthMode()){
                if (name=="")
                    MessageSender.callSuccess(event, ID);
                else
                    MessageSender.callSuccess(event, name);
            } else{
                try {
                    event.getMessage().delete().queue();
                } catch (Exception e){
                    MessageSender.errorMessage(event, e);
                }
            }
        } catch (ApiException e){
            MessageSender.errorMessage(event, e);
        }
    }
}
