import java.util.*;

public class Theatre {
    private final String theatreName;
    private List<Seat> seats = new ArrayList<>();

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

    private class Seat implements Comparable<Seat> {
        private final String number;
        private boolean reserved = false;

        public Seat(String number) {
            Objects.requireNonNull(number);
            this.number = number;
        }

        public String getNumber() {
            return number;
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
    }

    private void initSeats(int numRows, int seatsPerRow) {
        int lastRow = 'A' + (numRows - 1);

        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                Seat seat = new Seat(row + String.format("%02d", seatNum));
                seats.add(seat);
            }
        }
    }

    private Seat findSeat(String seatNumber) {
        Seat seatToFind = new Seat(seatNumber);
        int seatToFindPos = Collections.binarySearch(seats, seatToFind);

        if (seatToFindPos < 0) {
            return null;
        }

        return seats.get(seatToFindPos);
    }
}
