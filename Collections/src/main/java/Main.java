import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        Theatre theatre = new Theatre("Olympian", 8, 12);
        reserveSeat(theatre, "D12");
        reserveSeat(theatre, "D12");
        reserveSeat(theatre, "B13");

        List<Theatre.Seat> reversedSeats = new ArrayList<>(theatre.getSeats());
        Collections.reverse(reversedSeats);
        printSeats(reversedSeats);

        List<Theatre.Seat> priceSeats = new ArrayList<>(theatre.getSeats());
        priceSeats.add(theatre.new Seat("B00", 13.00));
        priceSeats.add(theatre.new Seat("A00", 13.00));
        Collections.sort(priceSeats, Theatre.SEAT_PRICE_ORDER);
        printSeats(priceSeats);

    }

    private static void reserveSeat(Theatre theatre, String seatNumber) {
        Objects.requireNonNull(theatre, "Theatre can't be null for seat reservation");
        Objects.requireNonNull(seatNumber, "Seat number can't be null for seat reservation");

        if (theatre.reserveSeat(seatNumber)) {
            System.out.println("Please pay");
        } else {
            System.out.println("Sorry, seat is taken");
        }
    }

    private static void printSeats(List<Theatre.Seat> seats) {
        System.out.println("================================================================");
        seats.forEach(System.out::println);
        System.out.println("================================================================");
    }
}
