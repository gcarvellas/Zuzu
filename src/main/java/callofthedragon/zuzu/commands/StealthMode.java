package callofthedragon.zuzu.commands;

import callofthedragon.zuzu.commands.resources.MessageSender;
import callofthedragon.zuzu.config.ConfigParser;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class StealthMode extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(ConfigParser.getPrefix() + "stealthMode")) {
            if (args.length == 1) {
                MessageSender.stealthModeStatus(event);
            }
            else {
                try {
                    ConfigParser.setStealthMode(Boolean.parseBoolean(args[1]));
                    MessageSender.changeStealthStatus(event, Boolean.parseBoolean(args[1]));
                } catch (Exception e) {
                    MessageSender.errorMessage(event, e);
                }
            }
        }
    }
}