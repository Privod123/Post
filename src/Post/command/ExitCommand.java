package Post.command;

import Post.LetterBox;

public class ExitCommand implements UserCommand {
    @Override
    public void execute(LetterBox letterBox) {
        System.out.println("Exiting...");
        System.exit(0);
    }

    @Override
    public String toString() {
        return "ExitCommand{}";
    }
}
