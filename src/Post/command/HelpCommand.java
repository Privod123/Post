package Post.command;

import Post.LetterBox;

public class HelpCommand implements UserCommand {
    @Override
    public void execute(LetterBox letterBox) {
        System.out.println("You can use following command:");
        System.out.println("list");
        System.out.println("send");
        System.out.println("add");
        System.out.println("edit <<id>>");
        System.out.println("remove <<id>>");
        System.out.println("help");
        System.out.println("exit");
    }

    @Override
    public String toString() {
        return "HelpCommand{}";
    }
}
