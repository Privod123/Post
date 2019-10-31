import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserConcoleInputReader {

    private Scanner scanner;
    private LetterBox letterBox;

    public UserConcoleInputReader(LetterBox letterBox) {
        this.letterBox = letterBox;
        scanner = new Scanner(System.in);
    }

    public void start(){
        while (true){
            String line = scanner.nextLine();
            String[] splitLine = line.trim().split(" ");
            // Проверяем что команда состоит из двух слов
            if (splitLine.length == 2 ){
                // проверяем какая команда была введена через разные if
                if (splitLine[0].equalsIgnoreCase("remove")){
                    try {
                        remove(Long.parseLong(splitLine[1]));
                    } catch (NumberFormatException e) {
                        processUnknowComnand(line);
                        continue;
                    }
                } else if (splitLine[0].equalsIgnoreCase("edit")){
                    try {
                        edit(Long.parseLong(splitLine[1]));
                    } catch (NumberFormatException e) {
                        processUnknowComnand(line);
                        continue;
                    }
                }else {
                    processUnknowComnand(line);
                }
            } else if (line.equalsIgnoreCase("add")){
                add();
            } else if (line.equalsIgnoreCase("send")){
                send();
            }else if (line.equalsIgnoreCase("list")){
                list();
            }else if (line.equalsIgnoreCase("help")){
                help();
            }else if (line.equalsIgnoreCase("exit")){
                exit();
            } else {
                processUnknowComnand(line);
            }
        }
    }

    private void help() {
        System.out.println("You can use following command:");
        System.out.println("list");
        System.out.println("send");
        System.out.println("add");
        System.out.println("edit <<id>>");
        System.out.println("remove <<id>>");
        System.out.println("exit");
    }

    private void list() {
        System.out.println("LISTING... ");
        System.out.println("Letters ib box:");
        List<Letter> letterList = letterBox.list();
        for (Letter letter : letterList) {
            System.out.println(letter);
        }
    }

    private void send() {
        System.out.println("SENDING... ");
        List<Long> idLetters = letterBox.sendToMainOffice();
        System.out.println("Following letters were sent " + idLetters);
    }

    private void add() {
        System.out.println("ADDING... ");
        System.out.println("Please,enter sender:");
        String sender = scanner.nextLine();
        System.out.println("Please,enter address:");
        String address = scanner.nextLine();
        System.out.println("Please,enter recipient:");
        String recipient = scanner.nextLine();
        System.out.println("Please,enter categoria letter: " + Arrays.toString(Letter.LetterCategoria.values()));
        String cat = scanner.nextLine().trim();
        Letter.LetterCategoria categoria = parseCategoria(cat);
        letterBox.addLetter(categoria,sender,address,recipient);

    }

    private Letter.LetterCategoria parseCategoria(String cat) {
        Letter.LetterCategoria categoria;
        switch (cat){
            case "first_class":
                categoria = Letter.LetterCategoria.FIRST_CLASS;
                break;
            case "special":
                categoria = Letter.LetterCategoria.SPECIAL;
                break;
            case "regular":
                default:
                categoria = Letter.LetterCategoria.REGULAR;
                break;
        }
        return categoria;
    }

    private void edit(long id) {
        System.out.println("EDITING " + id);
        Letter letter = letterBox.findLetter(id);
        if (letter == null){
            System.out.println("letter " + id + " was not found");
        }else {
            // Изменяем отправителя
            System.out.print("Change sender (" + letter.getSender() + ") if you want: ");
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()){
                letter.setSender(line);
            }
            // Изменяем адресс
            System.out.print("Change address (" + letter.getAdress() + ") if you want: ");
            line = scanner.nextLine().trim();
            if (!line.isEmpty()){
                letter.setAdress(line);
            }
            // Изменяем получателя
            System.out.print("Change recipient (" + letter.getRecipient() + ") if you want: ");
            line = scanner.nextLine().trim();
            if (!line.isEmpty()){
                letter.setRecipient(line);
            }
            // Изменяем класс письма которым оно отравленно
            System.out.print("Change categoria (" + letter.getCategoria() + ") if you want: ");
            line = scanner.nextLine().trim();
            if (!line.isEmpty()){
                letter.setCategoria(parseCategoria(line));
            }
        }
    }

    private void remove(Long id) {
        System.out.println("REMOVING " + id);
        boolean status = letterBox.deleteLetter(id);
        if (status){
            System.out.println("Letter " + id + " was successfully removed");
        }else {
            System.out.println("Letter " + id + " was not found");
        }
    }

    private void processUnknowComnand(String line){
        System.out.println("Unknown command: " + line);
    }

    private void exit(){
        System.out.println("Exiting...");
        System.exit(0);
    }
}
