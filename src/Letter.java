import java.util.Date;

public class Letter {

    enum LetterCategoria {
        REGULAR, SPECIAL, FIRST_CLASS
    }

    private LetterCategoria categoria;  // Каким классом послать письмо
    private String sender;              // Отправитель
    private String adress;              // Место назначение
    private String recipient;           // Получатель письма
    private long id;                    // Номер присвоенный письму на почте оператором
    private Date date;                  // Дата отправки письма

    public Letter(LetterCategoria categoria, String sender, String adress, String recipient) {
        this.categoria = categoria;
        this.sender = sender;
        this.adress = adress;
        this.recipient = recipient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LetterCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(LetterCategoria categoria) {
        this.categoria = categoria;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "categoria=" + categoria +
                ", sender='" + sender + '\'' +
                ", adress='" + adress + '\'' +
                ", recipient='" + recipient + '\'' +
                ", id=" + id +
                ", date=" + date +
                '}';
    }
}
