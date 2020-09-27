import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    static Theatre screeningRoom1 = new Theatre("Screening Room 1", 8, 12);
    static Theatre screeningRoom2 = new Theatre("Screening Room 2", 8, 12);
    static Theatre screeningRoom3 = new Theatre("Screening Room 3", 8, 12);

    static Movie forrestGumpMovie = new ForrestGumpMovie();
    static Movie goodfellasMovie = new GoodfellasMovie();
    static Movie interstellarMovie = new InterstellarMovie();

    public static void main(String[] args) {

        mainMenu();
    }

    public static void mainMenu(){

        boolean quitMainMenu = false;
        int command;
        System.out.println("Tonight is movie night! ");
        mainMenuCommands();

        while(!quitMainMenu){
            command = scanner.nextInt();
            scanner.nextLine();

            switch (command){
                case 0:
                    mainMenuCommands();
                    break;
                case 1:
                    displayMovies();
                    break;
                case 2:
                    forrestGumpMovie.getMovieInfo();
                    break;
                case 3:
                    goodfellasMovie.getMovieInfo();
                    break;
                case 4:
                    interstellarMovie.getMovieInfo();
                    break;
                case 5:{
                    System.out.println("Forrest Gump is running in Screening Room 1 tonight. These are the available seats: ");
                    screeningRoom1.getSeats();
                    System.out.println();
                    secondaryMenuCommands();
                    boolean quitSecondaryMenu = false;
                    while(!quitSecondaryMenu){
                        int command2 = scanner.nextInt();
                        scanner.nextLine();

                        switch(command2){
                            case 0:
                                secondaryMenuCommands();
                                break;
                            case 1:
                                System.out.println("Enter the seat number you would like to reserve for tonight: ");
                                String seatNumber1 = scanner.nextLine();
                                screeningRoom1.reservedSeat(seatNumber1);
                                break;
                            case 2:
                                System.out.println("Enter the seat number you would like to cancel the reservation for: ");
                                String seatNumber2 = scanner.nextLine();
                                screeningRoom1.canceledSeat(seatNumber2);
                                break;
                            case 3:
                                screeningRoom1.getSeats();
                                break;
                            case 4:
                                quitSecondaryMenu = true;
                                mainMenuCommands();
                                break;
                            default:
                                System.out.println("Wrong command entered! Try again!");
                                break;
                        }
                    }
                    break;
                }
                case 6:{
                    System.out.println("Goodfellas is running in Screening Room 2 tonight. These are the available seats: ");
                    screeningRoom2.getSeats();
                    System.out.println();
                    secondaryMenuCommands();
                    boolean quitSecondaryMenu = false;
                    while(!quitSecondaryMenu){
                        int command2 = scanner.nextInt();
                        scanner.nextLine();

                        switch(command2){
                            case 0:
                                secondaryMenuCommands();
                                break;
                            case 1:
                                System.out.println("Enter the seat number you would like to reserve for tonight: ");
                                String seatNumber1 = scanner.nextLine();
                                screeningRoom2.reservedSeat(seatNumber1);
                                break;
                            case 2:
                                System.out.println("Enter the seat number you would like to cancel the reservation for: ");
                                String seatNumber2 = scanner.nextLine();
                                screeningRoom2.canceledSeat(seatNumber2);
                                break;
                            case 3:
                                screeningRoom2.getSeats();
                                break;
                            case 4:
                                quitSecondaryMenu = true;
                                mainMenuCommands();
                                break;
                            default:
                                System.out.println("Wrong command entered! Try again!");
                                break;
                        }
                    }
                    break;
                }
                case 7:{
                    System.out.println("Interstellar is running in Screening Room 3 tonight. These are the available seats: ");
                    screeningRoom3.getSeats();
                    System.out.println();
                    secondaryMenuCommands();
                    boolean quitSecondaryMenu = false;
                    while(!quitSecondaryMenu){
                        int command2 = scanner.nextInt();
                        scanner.nextLine();

                        switch(command2){
                            case 0:
                                secondaryMenuCommands();
                                break;
                            case 1:
                                System.out.println("Enter the seat number you would like to reserve for tonight: ");
                                String seatNumber1 = scanner.nextLine();
                                screeningRoom3.reservedSeat(seatNumber1);
                                break;
                            case 2:
                                System.out.println("Enter the seat number you would like to cancel the reservation for: ");
                                String seatNumber2 = scanner.nextLine();
                                screeningRoom3.canceledSeat(seatNumber2);
                                break;
                            case 3:
                                screeningRoom3.getSeats();
                                break;
                            case 4:
                                quitSecondaryMenu = true;
                                mainMenuCommands();
                                break;
                            default:
                                System.out.println("Wrong command entered! Try again!");
                                break;
                        }
                    }
                    break;
                }
                case 8:
                    quitMainMenu = true;
                    System.out.println("Quitting main menu...");
                    break;
                default:
                    System.out.println("Wrong command entered! Try again!");
                    break;
            }
        }

    }

    public static void mainMenuCommands(){
        System.out.println("Choose your option: Press : \n" +
                "0 - To display the menu again.\n" +
                "1 - To display the movies running tonight.\n" +
                "2 - To get more info about Forrest Gump. \n" +
                "3 - To get more info about Goodfellas. \n" +
                "4 - To get more info about Interstellar. \n" +
                "5 - To choose Forrest Gump and visualize the theatre seats available: \n" +
                "6 - To choose Goodfellas and visualize the theatre seats available: \n" +
                "7 - To choose Interstellar and visualize the theatre seats available: \n" +
                "8 - To quit main menu.");
    }

    public static void secondaryMenuCommands(){
        System.out.println("Options : Press : \n" +
                "0 - To display the secondary menu again.\n" +
                "1 - To reserve a seat.\n" +
                "2 - To cancel a seat reservation.\n" +
                "3 - To visualize the seats representation again.\n" +
                "4 - To quit this secondary menu.\n");
    }

    public static void displayMovies(){
        System.out.println("-> Forrest Gump ");
        System.out.println("-> Goodfellas ");
        System.out.println("-> Interstellar ");
    }

}

