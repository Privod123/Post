package Post;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LetterBox {

    private static final Logger log =  Logger.getLogger(LetterBox.class);

    private MainOfficePost mainOfficePost;
    private List<Letter> listLetterBox = new ArrayList<>(); // Место куда складывают все письма
    private static long id = 0;                             // Номер которое присвоется письму на почте(хранилище)

    public LetterBox() {
        mainOfficePost = new MainOfficePost();
        List<Letter> loadLetters = read();
        listLetterBox.addAll(loadLetters);
        for (Letter letterBox : listLetterBox) {
            id = Math.max(id, letterBox.getId()) + 1;
        }

    }

    // метод addLetter добавляет новое письмо в почтовое отделение
    public long addLetter(Letter.LetterCategoria categoria, String sender, String address, String recipient){
        Letter letter = new Letter(categoria,sender,address,recipient);
        letter.setId(id++);
        letter.setDate(new Date());
        listLetterBox.add(letter);
        save();
        log.debug("Add following letter: categoria - " + categoria + ", sender - " + sender + ", address - " + address + ", recipient - " + recipient);
        return letter.getId();
    }

    // Удаление письма из списка писем почтового отделения
    public boolean deleteLetter(long id){
        Letter letter = findLetter(id);
        boolean statusDelete = listLetterBox.remove(letter);
        if (statusDelete){
            save();
            log.debug("Delete letter: id - " + id);
        } else {
            log.debug("Couldn't delete letter: id - " + id);
        }
        return statusDelete;
    }

    // возвращает список всех писем,которые есть на почте
    public List<Letter> list(){
        return new ArrayList<>(listLetterBox);
    }

    // Возвращает список id писем которые отправлены в главный офис
    public List<Long> sendToMainOffice(){
        log.debug("Sending letters to main office.");
        List<Long> ids = new ArrayList<>();
        Iterator<Letter> iterator = listLetterBox.iterator();
        while (iterator.hasNext()){
            Letter next = iterator.next();
            if (mainOfficePost.queue(next)){
                ids.add(next.getId());
                iterator.remove();
            }
        }
        save();
        return ids;
    }



    // Поиск письма в почтовом отделении
    public Letter findLetter(long id){
        for (int i = 0; i < listLetterBox.size(); i++) {
            if (listLetterBox.get(i).getId() == id){
                return listLetterBox.get(i);
            }
        }
        return null;
    }



    @Override
    public String toString() {
        return "LetterBox{" +
                "listLetterBox=" + listLetterBox +
                '}';
    }

    public void save(){
        try(PrintWriter writer = new PrintWriter(Paths.get("saveLetters.txt").toFile())){
            for (Letter letterBox : listLetterBox) {
                writer.print(letterBox.getId());
                writer.print("\t");
                writer.print(letterBox.getCategoria());
                writer.print("\t");
                writer.print(letterBox.getSender());
                writer.print("\t");
                writer.print(letterBox.getAdress());
                writer.print("\t");
                writer.print(letterBox.getRecipient());
                writer.print("\t");
                writer.print(letterBox.getDate().getTime());
                writer.println();
            }
        } catch (FileNotFoundException e) {
            log.error("Error saving file ", e);
        }

    }

    public List<Letter> read (){
        List<Letter> letters = new ArrayList<>();
        try {
            List<String> listSaveLetters = Files.readAllLines(Paths.get("saveLetters.txt"));
            for (String listSaveLetter : listSaveLetters) {
                String[] token = listSaveLetter.trim().split("\t");
                long id = Long.parseLong(token[0].trim());
                Letter.LetterCategoria categoria = Letter.LetterCategoria.valueOf(token[1]);
                Date date = new Date(Long.parseLong(token[5]));
                letters.add(new Letter(categoria,token[2],token[3],token[4],id,date));
            }
        } catch (IOException e) {
            log.error("Error reading file ", e);
        }

        return letters;
    }

    public void update(Letter.LetterCategoria categoria, String sender, String address, String recipient, long id) {
        Letter letter = findLetter(id);
        if (letter == null){
            System.out.println("Letter " + id + " was not found");
        } else {
            letter.setSender(sender);
            letter.setAdress(address);
            letter.setRecipient(recipient);
            letter.setCategoria(categoria);
        }
        save();
    }
}
