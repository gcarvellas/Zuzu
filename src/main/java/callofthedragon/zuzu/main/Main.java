package callofthedragon.zuzu.main;

import javax.security.auth.login.LoginException;

import callofthedragon.zuzu.commands.Caller;
import callofthedragon.zuzu.config.ConfigParser;
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

        Twilio.init(ConfigParser.getAccountSID(), ConfigParser.getAuthToken());

        jda.build();
    }
}
