package callofthedragon.zuzu.commands;


import callofthedragon.zuzu.commands.resources.MessageSender;
import callofthedragon.zuzu.commands.resources.contactmanager.Contact;
import callofthedragon.zuzu.commands.resources.contactmanager.ContactListManager;
import callofthedragon.zuzu.commands.resources.parsers.MessageParser;
import callofthedragon.zuzu.config.ConfigParser;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AddContact extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");
        if(args[0].equalsIgnoreCase(ConfigParser.getPrefix() + "addContact") && args.length > 1){
            User contactUser;
            if (!args[1].contains("[a-zA-Z]") && !isUser(args[1])){
                contactUser = event.getAuthor();
                if (args.length == 2){ //addContact NUMBER
                    ContactListManager.addContact(new Contact(contactUser, args[1]));
                    MessageSender.addContact(event, contactUser, args[1]);
                }
                else if (args.length == 3) { //addContact NUMBER NICK
                    ContactListManager.addContact(new Contact(contactUser, args[1], args[2]));
                    MessageSender.addContact(event, contactUser, args[1], args[2]);
                }
                else
                    MessageSender.errorMessage(event, new IllegalArgumentException("Invalid syntax"));
            }
            else if (isUser(args[1]) && !isUser(args[2])){
                try {
                    contactUser = User.fromId(args[1].replaceAll("[<@!>]", ""));
                    if (args.length == 3) { //addContact USER NUMBER
                        ContactListManager.addContact(new Contact(contactUser, args[2]));
                        MessageSender.addContact(event, contactUser, args[2]);
                    } else if (args.length == 4) { //addContact USER NUMBER NICK
                        ContactListManager.addContact(new Contact(contactUser, args[2], args[3]));
                        MessageSender.addContact(event, contactUser, args[2], args[3]);
                    } else
                        MessageSender.errorMessage(event, new IllegalArgumentException("Invalid syntax"));
                } catch (Exception e){
                        MessageSender.errorMessage(event, e);
                    }
            }
            else
                MessageSender.errorMessage(event, new IllegalArgumentException("Invalid syntax"));
        }
    }

    private static boolean isUser(String text){
        return MessageParser.regex("<@![0-9]*>", text);
    }
}
