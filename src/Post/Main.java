package Post;

import Post.command.*;

public class Main {
    public static void main(String[] args) {
        LetterBox letterBox = new LetterBox();
        UserConcoleInputReader userConcoleInputReader = new UserConcoleInputReader();
        while (true){
            UserCommand command = userConcoleInputReader.start();
            if ((command != null) && (command instanceof UserCommand)){
                starCommand(command, letterBox,userConcoleInputReader);
            }else {
                System.out.println("Unknown command");
            }
        }
    }

    private static void starCommand(UserCommand command, LetterBox letterBox, UserConcoleInputReader userConcoleInputReader) {
        if (command instanceof  EditCommand){
            EditCommand editCommand = (EditCommand) command;
            Letter letter = letterBox.findLetter(editCommand.getId());
            if (letter != null){
                userConcoleInputReader.readEdit(editCommand,letter.getSender(),letter.getAdress(),
                                                                         letter.getRecipient(),letter.getCategoria());
            }

        }
        command.execute(letterBox);
    }
}