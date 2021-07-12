package callofthedragon.zuzu.main;

import javax.security.auth.login.LoginException;

import callofthedragon.zuzu.commands.*;
import callofthedragon.zuzu.commands.resources.contactmanager.ContactListManager;
import callofthedragon.zuzu.commands.resources.web.YoutubeHandler;
import callofthedragon.zuzu.config.ConfigParser;
import callofthedragon.zuzu.events.database.Mongo;
import com.twilio.Twilio;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Main {

    public static void main(String[] args) throws LoginException{
        JDABuilder jda = JDABuilder.createDefault(ConfigParser.getBotToken());
        jda.setStatus(OnlineStatus.ONLINE);
        jda.setActivity(Activity.watching(ConfigParser.getPrefix() + "help"));
        jda.addEventListeners(new Caller());
        jda.addEventListeners(new AddContact());
        jda.addEventListeners(new SetName());
        jda.addEventListeners(new ListContacts());
        jda.addEventListeners(new RemoveContact());
        jda.addEventListeners(new Shutdown());
        jda.addEventListeners(new Mongo());
        jda.addEventListeners(new Messenger());
        jda.addEventListeners(new Help());
        Mongo.start();
        ContactListManager.instantiate();

        System.setProperty("webdriver.opera.driver", ConfigParser.getOperaDirectory());
        YoutubeHandler.setOptions();

        Twilio.init(ConfigParser.getAccountSID(), ConfigParser.getAuthToken());
        jda.setEnableShutdownHook(true);
        jda.build();
    }
}
