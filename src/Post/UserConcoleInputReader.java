package Post;

import Post.command.*;

import java.util.Arrays;
import java.util.Scanner;

public class UserConcoleInputReader {

    private Scanner scanner;

    public UserConcoleInputReader() {
        scanner = new Scanner(System.in);
    }

    public UserCommand start(){
        String line = scanner.nextLine();
        String[] splitLine = line.trim().split(" ");
        // Проверяем что команда состоит из двух слов
        if (splitLine.length == 2 ){
            // проверяем какая команда была введена через разные if
            if (splitLine[0].equalsIgnoreCase("remove")){
                try {
                    Long id = Long.parseLong(splitLine[1]);
                    return new RemoveCommand(id);
                } catch (NumberFormatException e) {
                    return null;
                }
            } else if (splitLine[0].equalsIgnoreCase("edit")){
                try {
                    Long id = Long.parseLong(splitLine[1]);
                    return new EditCommand(id);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        } else if (line.equalsIgnoreCase("add")){
            return parseAdd();
        } else if (line.equalsIgnoreCase("send")){
            return new SendCommand();
        }else if (line.equalsIgnoreCase("list")){
            return new ListCommand();
        }else if (line.equalsIgnoreCase("help")){
            return new HelpCommand();
        }else if (line.equalsIgnoreCase("exit")){
            return new ExitCommand();
        } else if (line.equalsIgnoreCase("")){
            return new EmptyCommand();
        }
        return null;
    }

    public void readEdit(EditCommand editCommand,String sender, String address, String recipient,
                                                                                Letter.LetterCategoria categoria ) {
        // Изменяем отправителя
        System.out.print("Change sender (" + sender + ") if you want: ");
        editCommand.setSender(writeChange(sender));

        // Изменяем адресс
        System.out.print("Change address (" + address + ") if you want: ");
        editCommand.setAddress(writeChange(address));

        // Изменяем получателя
        System.out.print("Change recipient (" + recipient + ") if you want: ");
        editCommand.setRecipient(writeChange(recipient));

        // Изменяем класс письма которым оно отравленно
        System.out.print("Change categoria (" + categoria + ") if you want: ");
        editCommand.setCategoria(writeChange(categoria));
    }

    private AddCommand parseAdd() {
        System.out.println("ADDING... ");
        System.out.println("Please,enter sender:");
        String sender = scanner.nextLine();
        System.out.println("Please,enter address:");
        String address = scanner.nextLine();
        System.out.println("Please,enter recipient:");
        String recipient = scanner.nextLine();
        System.out.println("Please,enter categoria letter: " + Arrays.toString(Letter.LetterCategoria.values()));
        Letter.LetterCategoria categoria = readCategoria();
        return new AddCommand(sender,address,recipient,categoria);
    }

    private  Letter.LetterCategoria readCategoria() {
        Letter.LetterCategoria categoria;
        while (true){
            String cat = scanner.nextLine().trim();
            categoria = convertStringToCategoria(cat);
            if (categoria != null){
                break;
            } else {
                System.out.println("Please,enter categoria letter: " + Arrays.toString(Letter.LetterCategoria.values()));
            }
        }
        return categoria;
    }

    private Letter.LetterCategoria convertStringToCategoria(String cat) {
        switch (cat) {
            case "first_class":
                return Letter.LetterCategoria.FIRST_CLASS;
            case "special":
                return Letter.LetterCategoria.SPECIAL;
            case "regular":
                return Letter.LetterCategoria.REGULAR;
            default:
                return null;
        }
    }

    // Метод проверит было ли сделано изменение и вернет измененное значение либо старое
    private String writeChange(String previous) {
        String line = scanner.nextLine().trim();
        if (!line.isEmpty()){
            return line;
        }
        return previous;
    }

    private Letter.LetterCategoria writeChange(Letter.LetterCategoria previous) {
        Letter.LetterCategoria categoria = previous;
        while (true){
            String cat = scanner.nextLine().trim();
            // если ничего не введено то категория не измениться
            if (cat.isEmpty()) break;
            categoria = convertStringToCategoria(cat);
            if (categoria != null){
                break;
            } else {
                System.out.println("Please,enter categoria letter: " + Arrays.toString(Letter.LetterCategoria.values()));
            }
        }
        return categoria;
    }
}
