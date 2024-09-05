import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowDate {
    private int days;
    private LocalDate borrowDate;

    public int getDays() {
        return days;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getReturnDate() {
        return addDays(borrowDate, days);
    } //atao date de retour ilay daty no-calculer-na
    public static LocalDate addDays(LocalDate date, int days){
        return date.plus(days, ChronoUnit.DAYS);
        //ity fonction ity manao calcul ilay jour ao anatin'ilay date, ChronoUnit.Days
        //mandray ilay jour @ilay date ary mifanaraka @ 24h sy datin'ny system ka ampian'ilay plus @zay
        //minimiser-na aminio ilay code fa lavabe raha atao manuel

    }
    //constructeur handraisana ilay ilay calcul date
    public BorrowDate(LocalDate borrowDate, int days){
        this.borrowDate = borrowDate;
        this.days = days;
    }
    public String toString(){
        return "Borrow date : " + getBorrowDate() + "\nNormal Return Date : "+getReturnDate();
    }
}
