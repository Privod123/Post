import java.util.List;

public class Main {
    public static void main(String[] args) {
        LetterBox letterBox = new LetterBox();
        UserConcoleInputReader userConcoleInputReader = new UserConcoleInputReader(letterBox);
        userConcoleInputReader.start();
    }
}
