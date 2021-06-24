package callofthedragon.zuzu.commands.resources.gagawa;

import com.hp.gagawa.java.FertileNode;
import com.hp.gagawa.java.Node;
import com.hp.gagawa.java.elements.Text;

import java.util.List;

public class Play extends FertileNode {
    public Play(){
        super("Play");
    }
    /**
     * Appends a child node to the end of this element's DOM tree
     * @param child node to be appended
     * @return the node
     */
    public Play appendChild(Node child){
        if(this == child){
            throw new Error("Cannot append a node to itself.");
        }
        child.setParent(this);
        children.add(child);
        return this;
    }
    /**
     * Appends a child node at the given index
     * @param index insert point
     * @param child node to be appended
     * @return the node
     */
    public Play appendChild(int index, Node child){
        if(this == child){
            throw new Error("Cannot append a node to itself.");
        }
        child.setParent(this);
        children.add(index, child);
        return this;
    }
    /**
     * Appends a list of children in the order given in the list
     * @param children nodes to be appended
     * @return the node
     */
    public Play appendChild(List<Node> children){
        if(children != null){;
            for(Node child: children){
                appendChild(child);
            }
        }
        return this;
    }
    /**
     * Appends the given children in the order given
     * @param children nodes to be appended
     * @return the node
     */
    public Play appendChild(Node... children){
        for(int i = 0; i < children.length; i++){
            appendChild(children[i]);
        }
        return this;
    }
    /**
     * Convenience method which appends a text node to this element
     * @param text the text to be appended
     * @return the node
     */
    public Play appendText(String text){
        return appendChild(new Text(text));
    }
}
