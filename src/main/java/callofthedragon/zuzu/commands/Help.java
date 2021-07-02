package callofthedragon.zuzu.commands;

import callofthedragon.zuzu.config.ConfigParser;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Help extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(ConfigParser.getPrefix() + "help")) {
            String prefix = ConfigParser.getPrefix();
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("Commands:");
            info.setDescription("*" + prefix
                    + "listContacts*  - Lists contacts.\n  *" + prefix
                    + "addContact @user PHONE_NUMBER* - Adds user to contact list.\n  *" + prefix
                    + "removeContact @user* - Removes contact.\n  *" + prefix
                    + "setName @user NAME* - Sets a nickname to an existing contact.\n  *" + prefix
                    + "call @user MESSAGE* - Calls a user that is in the contact list.\n  *"
                    + prefix + "call PHONE_NUMBER MESSAGE* - Calls a user with the given phone number.\n  *" + prefix
                    + "message @user MESSAGE* - Messages a user that is in the contact list.\n  *"
                    + prefix + "message PHONE_NUMBER MESSAGE* - Messages a user with the given phone number.");
            info.setAuthor("Dragon", null,
                    "https://avatarfiles.alphacoders.com/246/thumb-246621.png");
            info.setColor(0xa88785);
            info.setImage("https://media.tenor.com/images/c0b1d8638f86281e94aadc88eda4351a/tenor.gif");
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();
        }
    }
}
