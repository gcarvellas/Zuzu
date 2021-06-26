package callofthedragon.zuzu.commands.resources.contactmanager;
import callofthedragon.zuzu.commands.resources.parsers.MessageParser;
import net.dv8tion.jda.api.entities.User;

public class Contact {
    private User user;
    private String number;
    private String name;

    public Contact(User user, String phoneText, String name){
        this.user = user;
        this.number = phoneText;
        this.name = name;
    }

    public Contact(User user, String phoneText){
        this(user, phoneText, user.getName());
    }
    public Contact(String name, String phoneText){
        this(null, name, phoneText);
        this.name = name;
        this.number = phoneText;
    }

    public String getName(){
        return name;
    }

    public String getNumber(){
        return number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    @Override
    public String toString(){
        if (name == null)
            return user + " " + number;
        return user + "(" + name + ")" + " " + number;
    }

}
