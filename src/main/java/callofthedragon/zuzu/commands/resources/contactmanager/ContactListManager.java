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

    public static Contact getContactByID(String ID) throws IllegalArgumentException{
        for (Contact contact: contactList){
            if (contact.getUser().getId().equals(ID))
                return contact;
        }
        throw new IllegalArgumentException("No contact found. (ID)");
    }

    public static Contact getContactByName(String name) throws IllegalArgumentException{
        for (Contact contact: contactList){
            if (contact.getName().equals(name))
                return contact;
        }
        throw new IllegalArgumentException("No contact found. (Name)");
    }

    public static void removeContact(Contact contact){
        contactList.remove(contact);
    }

    public static HashSet<Contact> getContactList() {
        return contactList;
    }
}
