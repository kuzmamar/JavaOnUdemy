import java.util.*;

public class Theatre {
    private final String theatreName;
    private List<Seat> seats = new ArrayList<>();

    static final Comparator<Seat> SEAT_PRICE_ORDER = new Comparator<Seat>() {

        @Override
        public int compare(Seat a, Seat b) {
            if (a.getPrice() < b.getPrice()) {
                return -1;
            }
            if (a.getPrice() > b.getPrice()) {
                return 1;
            }
            return 0;
        }
    };

    public Theatre(String theatreName, int numRows, int seatsPerRow) {
        this.theatreName = theatreName;
        initSeats(numRows, seatsPerRow);
    }

    public String getTheatreName() {
        return theatreName;
    }

    public boolean reserveSeat(String seatNumber) {
        Seat requestedSeat = findSeat(seatNumber);

        if (requestedSeat == null) {
            System.out.println("[RESERVE SEAT] There is not seat " + seatNumber);
            return false;
        }
        return requestedSeat.reserve();
    }

    // for testing
    public void printSeats() {
        for (Seat seat: seats) {
            System.out.println(seat.getNumber());
        }
    }

    public class Seat implements Comparable<Seat> {
        private final String number;
        private final double price;
        private boolean reserved = false;

        public Seat(String number, double price) {
            Objects.requireNonNull(number);
            this.number = number;
            this.price = price;
        }

        public String getNumber() {
            return number;
        }

        public double getPrice() {
            return price;
        }

        public boolean reserve() {
            if (reserved) {
                System.out.println("[RESERVE] Seat " + number + " already reserved");
                return false;
            }
            reserved = true;
            System.out.println("[RESERVE] Seat " + number + " reserved");
            return true;
        }

        public boolean cancel() {
            if (!reserved) {
                System.out.println("[CANCEL] Seat not reserved");
                return false;
            }

            reserved = false;
            System.out.println("[CANCEL] Seat reservation cancelled");
            return false;
        }

        @Override
        public int compareTo(Seat seat) {
            return this.number.compareToIgnoreCase(seat.number);
        }

        @Override
        public String toString() {
            return "{" + "number: " + this.number + ", price: " + this.price + "}";
        }
    }

    public Collection<Seat> getSeats() {
        return seats;
    }

    private void initSeats(int numRows, int seatsPerRow) {
        int lastRow = 'A' + (numRows - 1);

        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                double seatPrice = determineSeatPrice(row, seatNum);
                String seatNumber = row + String.format("%02d", seatNum);
                Seat seat = new Seat(seatNumber, seatPrice);
                seats.add(seat);
            }
        }
    }

    private Seat findSeat(String seatNumber) {
        Seat seatToFind = new Seat(seatNumber, 0);
        int seatToFindPos = Collections.binarySearch(seats, seatToFind);

        if (seatToFindPos < 0) {
            return null;
        }

        return seats.get(seatToFindPos);
    }

    private static double determineSeatPrice(int row, int seatNum) {
        double price = 12.00;

        if ((row < 'D') && (seatNum >= 4 && seatNum <= 9)) {
            price = 14.00;
        } else if ((row > 'F') || (seatNum < 4 || seatNum > 9)) {
            price = 7.00;
        }

        return price;
    }
}
