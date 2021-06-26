package callofthedragon.zuzu.main;

import javax.security.auth.login.LoginException;

import callofthedragon.zuzu.commands.AddContact;
import callofthedragon.zuzu.commands.Caller;
import callofthedragon.zuzu.commands.ListContacts;
import callofthedragon.zuzu.commands.resources.contactmanager.Contact;
import callofthedragon.zuzu.commands.resources.contactmanager.ContactListManager;
import callofthedragon.zuzu.config.ConfigParser;
import com.twilio.Twilio;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws LoginException{
        JDABuilder jda = JDABuilder.createDefault(ConfigParser.getBotToken());
        jda.setStatus(OnlineStatus.ONLINE);
        jda.setActivity(Activity.watching(ConfigParser.getPrefix() + "help"));
        jda.addEventListeners(new Caller());
        jda.addEventListeners(new AddContact());
        jda.addEventListeners(new ListContacts());
        ContactListManager.instantiate();

        Twilio.init(ConfigParser.getAccountSID(), ConfigParser.getAuthToken());

        jda.build();
    }
}
