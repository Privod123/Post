package Post.command;

import Post.LetterBox;

public class EmptyCommand implements UserCommand {
    @Override
    public void execute(LetterBox letterBox) {

    }

    @Override
    public String toString() {
        return "EmptyCommand{}";
    }
}
