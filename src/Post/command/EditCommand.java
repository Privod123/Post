package Post.command;

import Post.Letter;
import Post.LetterBox;

public class EditCommand implements UserCommand {

    private long id;
    private String sender;
    private String address;
    private String recipient;
    private Letter.LetterCategoria categoria;

    public EditCommand(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setCategoria(Letter.LetterCategoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public void execute(LetterBox letterBox) {
        letterBox.update(categoria,sender,address,recipient,id);
        System.out.println("letter id = " + id + " : changed");
    }

    @Override
    public String toString() {
        return "EditCommand{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", address='" + address + '\'' +
                ", recipient='" + recipient + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
