package Post.command;

import Post.LetterBox;

import java.util.List;

public class SendCommand implements UserCommand {
    @Override
    public void execute(LetterBox letterBox) {
        System.out.println("SENDING... ");
        List<Long> idLetters = letterBox.sendToMainOffice();
        System.out.println("Following letters were sent " + idLetters);
    }
}
