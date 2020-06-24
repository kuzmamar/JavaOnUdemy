import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        Theatre theatre = new Theatre("Olympian", 8, 12);
        theatre.printSeats();
        reserveSeat(theatre, "A11");
        reserveSeat(theatre, "A11");
    }

    private static void reserveSeat(Theatre theatre, String seatNumber) {
        Objects.requireNonNull(theatre, "Theatre can't be null for seat reservation");
        Objects.requireNonNull(seatNumber, "Seat number can't be null for seat reservation");

        if (theatre.reserveSeat("A11")) {
            System.out.println("Please pay");
        } else {
            System.out.println("Sorry, seat is taken");
        }
    }
}
