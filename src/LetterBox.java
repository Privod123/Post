import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LetterBox {

    private MainOfficePost mainOfficePost;
    private List<Letter> listLetterBox = new ArrayList<>(); // Место куда складывают все письма
    private static long id = 0;                             // Номер которое присвоется письму на почте

    public LetterBox() {
        mainOfficePost = new MainOfficePost();
    }

    // метод addLetter добавляет новое письмо в почтовое отделение
    public void addLetter(Letter.LetterCategoria categoria, String sender, String adress, String recipient){
        Letter letter = new Letter(categoria,sender,adress,recipient);
        letter.setId(id++);
        letter.setDate(new Date());
        listLetterBox.add(letter);
    }

    // Удаление письма из списка писем почтового отделения
    public boolean deleteLetter(int id){
        Letter letter = findLetter(id);
        return listLetterBox.remove(letter);
    }

    // возвращает список всех писем,которые есть на почте
    public List<Letter> list(){
        return new ArrayList<>(listLetterBox);
    }

    // Возвращает список id писем которые отправлены в главный офис
    public List<Long> sendToMainOffice(){
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
                System.out.println("Письма с таким номером нет на почте");
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
