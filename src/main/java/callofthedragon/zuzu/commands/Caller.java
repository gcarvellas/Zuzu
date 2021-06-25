package callofthedragon.zuzu.commands;

import callofthedragon.zuzu.commands.resources.MessageSender;
import callofthedragon.zuzu.commands.resources.parsers.MessageParser;
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
        if(args[0].equalsIgnoreCase(ConfigParser.getPrefix() + "call")){
            String message = MessageParser.convertToTwiml(Arrays.copyOfRange(args, 2, args.length));
            MessageSender.callPending(event, args[1]);
            try {
                Call call = Call.creator(
                        new PhoneNumber(args[1]),
                        new PhoneNumber(ConfigParser.getPhoneNumber()),
                        new Twiml(message))
                        .create();
                MessageSender.callSuccess(event, args[1]);
            } catch (ApiException a){
                MessageSender.errorMessage(event, a);
            }
        }
    }

}
