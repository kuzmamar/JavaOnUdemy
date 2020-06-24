import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private final String theatreName;
    private List<Seat> seats = new ArrayList<>();

    public Theatre(final String theatreName, int numRows, int seatsPerRow) {
        this.theatreName = theatreName;
        initSeats(numRows, seatsPerRow);
    }

    public String getTheatreName() {
        return theatreName;
    }

    public boolean reserveSeat(String seatNumber) {
        Seat requestedSeat = null;

        for(Seat seat : seats) {
            if (seat.getNumber().equals(seatNumber)) {
                requestedSeat = seat;
                break;
            }
        }

        if (requestedSeat == null) {
            System.out.println("There is no seat " + seatNumber);
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

    private class Seat {
        private final String number;
        private boolean reserved = false;

        public Seat(final String number) {
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
}
