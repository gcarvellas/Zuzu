package callofthedragon.zuzu.commands.resources.contactmanager;

import java.util.HashSet;

public class ContactListManager {
    private static HashSet<Contact> contactList;

    public static void instantiate(){
        contactList = new HashSet<Contact>();
    }

    public static void addContact(Contact contact){
        contactList.add(contact);
    }

    public static void removeContact(Contact contact){
        contactList.remove(contact);
    }

    public static HashSet<Contact> getContactList() {
        return contactList;
    }
}
