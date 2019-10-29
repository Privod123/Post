import java.util.List;

public class Main {
    public static void main(String[] args) {
        LetterBox letterBox = new LetterBox();
        letterBox.addLetter(Letter.LetterCategoria.REGULAR,"Ivanov", "Volgograd","Petrov");
        letterBox.addLetter(Letter.LetterCategoria.REGULAR,"Sidorov", "Volgograd","Pytin");
        System.out.println(letterBox);
        System.out.println("-------------");
        List<Long> longs = letterBox.sendToMainOffice();
        System.out.println(longs);
        System.out.println(letterBox);
    }
}
