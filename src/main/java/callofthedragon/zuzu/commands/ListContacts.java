package callofthedragon.zuzu.commands;

import callofthedragon.zuzu.commands.resources.contactmanager.Contact;
import callofthedragon.zuzu.commands.resources.contactmanager.ContactListManager;
import callofthedragon.zuzu.config.ConfigParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ListContacts extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(ConfigParser.getPrefix() + "listContacts")) {
            String prefix = ConfigParser.getPrefix();
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("Contacts:");
            info.setDescription(contactsAsString(event));
            info.setColor(0xa88785);
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();
        }
    }

    private String contactsAsString(GuildMessageReceivedEvent event){
        String result = "";
        for (Contact contact : ContactListManager.getContactList().values())
            result+=contact.toString()+"\n";
        return result;
    }
}