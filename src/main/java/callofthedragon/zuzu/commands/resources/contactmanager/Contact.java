package callofthedragon.zuzu.commands.resources.contactmanager;
import net.dv8tion.jda.api.entities.User;

public class Contact {
    private User user;
    private String number;
    private String name;

    public Contact(User user, String phoneText, String name) throws IllegalArgumentException{
        this.user = user;
        this.number = phoneText;
    }

    public Contact(User user, String phoneText){
        this(user, user.getName(), phoneText);
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
}
