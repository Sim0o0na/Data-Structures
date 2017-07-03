import com.sun.beans.editors.StringEditor;
import org.magicwerk.brownies.collections.BigList;

import java.util.Stack;

/**
 * Created by Simona Simeonova on 6/19/2017.
 */
public class StringTextEditor implements TextEditor {
    private Trie<BigList<Character>> userStrings;
    private Trie<Stack<String>> userStack;

    public StringTextEditor(){
        this.userStack = new Trie<>();
        this.userStrings = new Trie<>();
    }

    @Override
    public void login(String username) {
        if(!userStrings.contains(username)){
            userStrings.insert(username, new BigList<>());
            userStack.insert(username, new Stack<>());
        }
    }

    @Override
    public void logout(String username) {
        userStrings.delete(username);
        userStack.delete(username);
    }

    @Override
    public void prepend(String username, String string) {

    }

    @Override
    public void insert(String username, int index, String string) {
        if(!this.userStrings.contains(username)){
            return;
        }

    }

    @Override
    public void substring(String username, int startIndex, int length) {

    }

    @Override
    public void delete(String username, int startIndex, int length) {

    }

    @Override
    public void clear(String username) {

    }

    @Override
    public int length(String username) {
        return 0;
    }

    @Override
    public String print(String username) {
        return null;
    }

    @Override
    public void undo(String username) {

    }

    @Override
    public Iterable<String> users(String prefix) {
        return null;
    }
}
