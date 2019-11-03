package Post.command;

import Post.Letter;
import Post.LetterBox;

import java.util.List;

public class ListCommand implements UserCommand {
    @Override
    public void execute(LetterBox letterBox) {
        System.out.println("LISTING... ");
        System.out.print("Letters in box: ");
        List<Letter> letterList = letterBox.list();
        if (letterList.isEmpty()){
            System.out.println("Empty");
        }else {
            System.out.println("");
        }
        for (Letter letter : letterList) {
            System.out.println(letter);
        }
    }

    @Override
    public String toString() {
        return "ListCommand{}";
    }
}
