package callofthedragon.zuzu.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigParser {
    private static Properties defaultProps;
    private static final String PROP_FILE_NAME = "config.properties";

    public static void loadProperties() {
        try {
            defaultProps = new Properties();
            InputStream in = ConfigParser.class.getClassLoader().getResourceAsStream(PROP_FILE_NAME);
            defaultProps.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBotToken(){
        checkPropertiesLoaded();
        return defaultProps.getProperty("botToken");
    }

    public static String getAccountSID(){
        checkPropertiesLoaded();
        return defaultProps.getProperty("accountSID");
    }

    public static String getAuthToken(){
        checkPropertiesLoaded();
        return defaultProps.getProperty("authToken");
    }

    public static String getPrefix(){
        checkPropertiesLoaded();
        return defaultProps.getProperty("commandPrefix");
    }

    public static String getPhoneNumber(){
        checkPropertiesLoaded();
        return defaultProps.getProperty("hostPhoneNumber");
    }

    public static String getAuthorId(){
        checkPropertiesLoaded();
        return defaultProps.getProperty("authorId");
    }

    public static String getMongoURI(){
        checkPropertiesLoaded();
        return defaultProps.getProperty("mongoURI");
    }

    public static String getDatabaseName(){
        checkPropertiesLoaded();
        return defaultProps.getProperty("database");
    }

    public static String getCollectionName(){
        checkPropertiesLoaded();
        return defaultProps.getProperty("collection");
    }

    private static void checkPropertiesLoaded(){
        if (defaultProps == null)
            loadProperties();
    }

    public static String getOperaDirectory(){
        checkPropertiesLoaded();
        return defaultProps.getProperty("operaDirectory");
    }

    public static String getOperaBinary(){
        checkPropertiesLoaded();
        return defaultProps.getProperty("operaBinary");
    }

    public static boolean isStealthMode(){
        checkPropertiesLoaded();
        return Boolean.parseBoolean(defaultProps.getProperty("stealthCalls"));
    }

    public static void setStealthMode(boolean value){
        defaultProps.setProperty("stealthCalls", Boolean.toString(value));
    }
}
