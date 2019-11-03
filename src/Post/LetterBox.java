package Post;

import org.apache.log4j.Logger;

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
    }

    // метод addLetter добавляет новое письмо в почтовое отделение
    public long addLetter(Letter.LetterCategoria categoria, String sender, String address, String recipient){
        Letter letter = new Letter(categoria,sender,address,recipient);
        letter.setId(id++);
        letter.setDate(new Date());
        listLetterBox.add(letter);
        log.debug("Add following letter: categoria - " + categoria + ", sender - " + sender + ", address - " + address + ", recipient - " + recipient);
        return letter.getId();
    }

    // Удаление письма из списка писем почтового отделения
    public boolean deleteLetter(long id){
        Letter letter = findLetter(id);
        boolean statusDelete = listLetterBox.remove(letter);
        if (statusDelete){
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
        return ids;
    }



    // Поиск письма в почтовом отделении
    public Letter findLetter(long id){
        for (int i = 0; i < listLetterBox.size(); i++) {
            if (listLetterBox.get(i).getId() == id){
                return listLetterBox.get(i);
            }else {
                System.out.println("Письма с таким id нет");
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
}
