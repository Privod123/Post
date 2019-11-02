package Post.command;

import Post.Letter;
import Post.LetterBox;

public class AddCommand implements UserCommand {
    private String sender;
    private String address;
    private String recipient;
    private Letter.LetterCategoria categoria;

    public AddCommand(String sender, String address, String recipient, Letter.LetterCategoria categoria) {
        this.sender = sender;
        this.address = address;
        this.recipient = recipient;
        this.categoria = categoria;
    }

    @Override
    public void execute(LetterBox letterBox) {
        letterBox.addLetter(categoria,sender,address,recipient);
    }
}
