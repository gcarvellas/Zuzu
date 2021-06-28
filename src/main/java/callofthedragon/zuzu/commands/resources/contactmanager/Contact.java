package callofthedragon.zuzu.commands.resources.contactmanager;
import net.dv8tion.jda.api.entities.User;

import java.io.Serializable;

public class Contact implements Comparable, Serializable {
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

    public String getName(){
        return name;
    }

    public String getNumber(){
        return number;
    }

    public User getUser() {
        return user;
    }

    public void setName(String name){ this.name = name; }

    @Override
    public String toString(){
        if (name == null)
            return user + " " + number;
        return user + "(" + name + ")" + " " + number;
    }

    @Override
    public boolean equals(Object obj){
        Contact contact = (Contact) obj;
        return this.user.equals(contact.getUser());
    }

    @Override
    public int compareTo(Object obj){
        Contact contact = (Contact) obj;
        return this.user.toString().compareTo(contact.getUser().toString());
    }

    @Override
    public int hashCode(){
        return user.hashCode();
    }
}
