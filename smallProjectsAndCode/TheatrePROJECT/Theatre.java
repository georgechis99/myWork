import java.util.*;

//public class Theatre
public class Theatre {
    private final String theatreName;
    private List<Seat> seats = new ArrayList<>();
    private int seatsPerRow;

    //constructor
    public Theatre(String theatreName, int numRows, int seatsPerRow) {
        this.theatreName = theatreName;

        this.seatsPerRow = seatsPerRow;
        int lastRow = 'A' + (numRows - 1);
        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                Seat seat = new Seat(row + String.format("%02d", seatNum));
                seats.add(seat);
            }
        }
    }

    public String getTheatreName() {
        return theatreName;
    }

    //method to reserve a seat in the theatre
    public boolean reservedSeat(String seatNumber) {
        Seat requestedSeat = new Seat(seatNumber);
        //binary search is a more efficient method to search for an objet in a list
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
        if (foundSeat >= 0) {
            return seats.get(foundSeat).reserve();
        } else {
            System.out.println("There is no seat " + seatNumber);
            return false;
        }
    }

    //method to cancel a seat reservation in the theatre
    public boolean canceledSeat(String seatNumber) {
        Seat requestedSeat = new Seat('X' + seatNumber);
        //binary search is a more efficient method to search for an objet in a list
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
        if (foundSeat >= 0) {
            return seats.get(foundSeat).cancel();
        } else {
            System.out.println(seatNumber + " was not reserved.");
            return false;
        }
    }

    //method to display all the seats available
    public void getSeats() {

        //here I implemented the Iterator Design Pattern to iterate through the list
        CustomListIterator customListIterator = new CustomListIterator(seats);

        int seatCount = 0;
        for (Iterator iterator = customListIterator.getIterator(); iterator.hasNext(); ) {
            Seat seat = (Seat) iterator.next();
            System.out.print(seat.getSeatNumber() + "  ");
            seatCount++;
            if (seatCount % seatsPerRow == 0) {
                System.out.println();
            }
        }

        //this iteration could have simply be done like so , but for demonstrating
        //purposes I decided to do it in another way ( with a custom Iterator )
//        for(Seat seat : seats){
//            System.out.println(seat.getSeatNumber());
//        }
    }

    //inner class Seat
    public class Seat implements Comparable<Seat> {

        private String seatNumber;
        private boolean reserved = false;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }

        //method to reverse a seat
        public boolean reserve() {
            if (!this.reserved) {
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " reserved.");
                this.seatNumber = 'X' + seatNumber;
                return true;
            } else {
                return false;
            }
        }

        //method to cancel a seat reservation
        public boolean cancel() {
            if (this.reserved) {
                this.reserved = false;
                this.seatNumber = this.seatNumber.substring(1);
                System.out.println("Reservation of seat " + seatNumber + " canceled.");
                return true;
            } else {
                return false;
            }
        }

        public String getSeatNumber() {
            return seatNumber;
        }
    }
}
