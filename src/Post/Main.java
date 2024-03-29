package Post;

import Post.command.*;
import org.apache.log4j.Logger;

public class Main {

    // Инициализация логера
    private static final Logger commandLog =  Logger.getLogger("CommandLog");

    public static void main(String[] args) {

        commandLog.debug("Start work");
        LetterBox letterBox = new LetterBox();
        UserConcoleInputReader userConcoleInputReader = new UserConcoleInputReader();
        while (true){
            UserCommand command = userConcoleInputReader.start();
            if (command != null){
                commandLog.debug(command.toString());
                if (command instanceof ExitCommand){
                    commandLog.debug("Finish work");
                    commandLog.debug(" --------------------------------- ");
                }
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
