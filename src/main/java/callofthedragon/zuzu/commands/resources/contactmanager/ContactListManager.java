package callofthedragon.zuzu.commands.resources.contactmanager;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.Serializable;
import java.util.HashMap;

public class ContactListManager extends ListenerAdapter{
    private static HashMap<String, Contact> contactList;

    public static void instantiate(){
        contactList = new HashMap<String, Contact>();
    }

    public static void addContact(Contact contact){
        if (contactList.containsValue(contact))
            throw new IllegalArgumentException("Contact already exists!");
        contactList.put(contact.getUser().getId(), contact);
    }

    public static void removeContact(Contact contact){
        if (!contactList.containsValue(contact))
            throw new NullPointerException("Contact does not exist!");
        contactList.remove(contact.getUser().getId());
    }

    public static Contact getContact(String ID){
        return contactList.get(ID.replaceAll("[<@!>]", ""));
    }

    public static Contact getContactByName(String name){
        for (Contact contact: contactList.values()){
            if (contact.getName().equals(name))
                return contact;
        }
        throw new NullPointerException("No contact found.");
    }

    public static void setName(String ID, String name){
        Contact result = contactList.get(ID.replaceAll("[<@!>]", ""));
        if (result == null)
            throw new NullPointerException("No contact found.");
        result.setName(name);
        contactList.replace(ID, result);
    }

    public static void loadContactList(HashMap<String, Contact> newContactList){
        contactList = newContactList;
    }



    public static HashMap<String, Contact> getContactList() {
        return contactList;
    }

}
