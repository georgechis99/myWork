import java.util.*;

public class CustomListIterator implements Container {

    private List<Theatre.Seat> seats;

    public CustomListIterator(List<Theatre.Seat> seats) {
        this.seats = seats;
    }

    @Override
    public Iterator getIterator() {
        return new ListIteratorClass();
    }

    private class ListIteratorClass implements Iterator {

        int index;

        @Override
        public boolean hasNext() {
            if (index < seats.size()) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return seats.get(index++);
            }
            return null;
        }
    }
}
