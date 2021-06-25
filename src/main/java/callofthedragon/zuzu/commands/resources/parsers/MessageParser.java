package callofthedragon.zuzu.commands.resources.parsers;

import callofthedragon.zuzu.commands.resources.gagawa.Play;
import callofthedragon.zuzu.commands.resources.gagawa.Response;
import callofthedragon.zuzu.commands.resources.gagawa.Say;
import com.hp.gagawa.java.FertileNode;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageParser {

    public static String convertToTwiml(String[] args){
        Response head = new Response();
        FertileNode childReference = head;
        for (String arg: args){
            if (isURL(arg)){
                childReference = new Play().appendText(arg);
                head.appendChild(childReference);
            }
            else {
                if (childReference instanceof Say)
                    ((Say) childReference).appendText(" " + arg);
                else {
                    childReference = new Say().appendText(arg);
                    head.appendChild(childReference);
                }
            }
        }
        return head.write();
    }

    private static boolean isURL(String text){
        try{
            new URL(text);
            return true;
        } catch (MalformedURLException m) {
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean regex(String regex, String text){
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}