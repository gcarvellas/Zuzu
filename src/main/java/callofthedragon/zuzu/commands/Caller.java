package callofthedragon.zuzu.commands;

import callofthedragon.zuzu.commands.resources.MessageSender;
import callofthedragon.zuzu.commands.resources.contactmanager.Contact;
import callofthedragon.zuzu.commands.resources.contactmanager.ContactListManager;
import callofthedragon.zuzu.commands.resources.parsers.MessageParser;
import callofthedragon.zuzu.config.ConfigParser;
import com.twilio.exception.ApiException;
import net.dv8tion.jda.api.entities.User;
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
        if(args[0].equalsIgnoreCase(ConfigParser.getPrefix() + "call")){
            String message = MessageParser.convertToTwiml(Arrays.copyOfRange(args, 2, args.length));
            try {
                String phoneNumber = args[1];
                String name = "";
                if(MessageParser.isUser(args[1])){
                    Contact contact = ContactListManager.getContactByID(args[1]);
                    MessageSender.callPending(event, name = contact.getName(), phoneNumber = contact.getNumber());
                } else if (args[1].contains("+[0-9]*")){
                    MessageSender.callPending(event, name, args[1]);
                } else {
                    Contact contact = ContactListManager.getContactByName(args[1]);
                    MessageSender.callPending(event, name = contact.getName(), phoneNumber = contact.getNumber());
                }
                Call call = Call.creator(
                        new PhoneNumber(phoneNumber),
                        new PhoneNumber(ConfigParser.getPhoneNumber()),
                        new Twiml(message))
                        .create();
                if (name == "")
                    MessageSender.callSuccess(event, phoneNumber);
                else
                    MessageSender.callSuccess(event, name);
            } catch (ApiException a){
                MessageSender.errorMessage(event, a);
            } catch (IllegalArgumentException e){
                MessageSender.errorMessage(event, e);
            }
        }
    }
}
