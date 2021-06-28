package callofthedragon.zuzu.events.database;

import callofthedragon.zuzu.commands.resources.contactmanager.Contact;
import callofthedragon.zuzu.commands.resources.contactmanager.ContactListManager;
import callofthedragon.zuzu.config.ConfigParser;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.Document;

public class Mongo extends ListenerAdapter {

    private static MongoClient client;
    private static MongoDatabase database;
    private static MongoCollection<Document> contactCollection;

    public static void start(){
        client = MongoClients.create(ConfigParser.getMongoURI());
        MongoDatabase database = client.getDatabase(ConfigParser.getDatabaseName());
        contactCollection = database.getCollection(ConfigParser.getCollectionName());
    }

    @Override
    public void onShutdown(ShutdownEvent e){
        BasicDBObject document = new BasicDBObject();
        contactCollection.deleteMany(document);
        for (Contact contact: ContactListManager.getContactList().values()){
            contactCollection.insertOne(convert(contact));
        }
        System.out.println("Successfully saved to mongo!");
    }

    @Override
    public void onReady(ReadyEvent e){
        for (Document doc : contactCollection.find()){
            String userId = doc.get("UserID").toString();
            String name = doc.get("Name").toString();
            String number = doc.get("Number").toString();
            ContactListManager.addContact(new Contact(e.getJDA().retrieveUserById(userId).complete(), number, name));
        }
        System.out.println("Successfully loaded data from mongo database");
    }

    public static Document convert(Contact contact){
        return new Document("UserID", contact.getUser().getId())
                .append("Name", contact.getName())
                .append("Number", contact.getNumber());
    }
}
