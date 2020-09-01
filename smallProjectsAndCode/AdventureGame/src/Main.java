import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<Integer, Location> locations = new HashMap<>();
    private static Map<String, String> decodedLocations = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String,Integer> tempExit = new HashMap<>();
        locations.put(0, new Location(0, "Exiting the game...",tempExit));

        tempExit = new HashMap<>();
        tempExit.put("W",2);
        tempExit.put("E", 3);
        tempExit.put("S", 4);
        tempExit.put("N", 5);
        locations.put(1, new Location(1, "1. Road",tempExit));

        tempExit = new HashMap<>();
        tempExit.put("N", 5);
        locations.put(2, new Location(2, "2. Hill",tempExit));

        tempExit = new HashMap<>();
        tempExit.put("W", 1);
        locations.put(3, new Location(3, "3. Building",tempExit));

        tempExit = new HashMap<>();
        tempExit.put("N", 1);
        tempExit.put("W", 2);
        locations.put(4, new Location(4, "4. Valley",tempExit));

        tempExit = new HashMap<>();
        tempExit.put("S", 1);
        tempExit.put("W", 2);
        locations.put(5, new Location(5, "5. Forrest",tempExit));

        decodedLocations.put("SOUTH", "S");
        decodedLocations.put("NORTH", "N");
        decodedLocations.put("WEST", "W");
        decodedLocations.put("EAST", "E");
        decodedLocations.put("QUIT", "Q");

        int loc = 1;
        while (true) {
            System.out.println(locations.get(loc).getDescription());
            if (loc == 0) {
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are : ");
            for (String exit : exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            String fullDirection = scanner.nextLine().toUpperCase();
            if (fullDirection.length() > 1) {
                String[] splitDirection = fullDirection.split(" ");
                for (String s : splitDirection) {
                    if (decodedLocations.containsKey(s)) {
                        fullDirection = decodedLocations.get(s);
                        break;
                    }
                }
            }
            if (exits.containsKey(fullDirection)) {
                loc = exits.get(fullDirection);
            } else {
                System.out.println("You cannot move in that direction!");
            }
        }
    }
}
