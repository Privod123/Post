package Post.command;

import Post.LetterBox;

public class RemoveCommand implements UserCommand {

    private Long id;

    public RemoveCommand(Long id) {
        this.id = id;
    }

    @Override
    public void execute(LetterBox letterBox) {
        System.out.println("REMOVING " + id);
        boolean status = letterBox.deleteLetter(id);
        if (status){
            System.out.println("Letter " + id + " was successfully removed");
        }else {
            System.out.println("Letter " + id + " was not found");
        }
    }
}
