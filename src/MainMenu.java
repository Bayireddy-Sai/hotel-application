import api.HotelResources;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

public class MainMenu {
    public static HotelResources hotelResources = HotelResources.getInstance();
    static Scanner sc = new Scanner(System.in);

    public static void mainMenu() throws ParseException {
        System.out.println("""
                Welcome To Hotel Reservation Applictaion
                ************* Main Menu ***************
                1. Find and reseve a room
                2. See my reservations
                3. Create an account
                4. Admin
                5. Exit
                  
                Select an option from the menu
                """);

        int option = Integer.parseInt(sc.nextLine());

        switch (option) {
            case 1 -> findAndReserveARoom();
            case 2 -> seeMyReservation();
            case 3 -> CreateAccount();
            case 4 -> AdminMenu.adminMenu();
            case 5 -> Exit();

            default -> mainMenu();

        }
    }

    private static void Exit() {
        System.out.println("Exit");
    }

    private static void CreateAccount() throws ParseException {

            System.out.println("Enter Email Id:");
            String email = sc.nextLine();
            System.out.println("Enter First Name:");
            String firstName = sc.nextLine();
            System.out.println("Enter Last Name: ");
            String lastName = sc.nextLine();

            hotelResources.createACustomer(email,firstName,lastName);

            System.out.println("Account has been successfully created:");

            mainMenu();

    }

    private static void seeMyReservation() throws ParseException {
        System.out.println("pls enter your email: ");
        String email = sc.nextLine();

        List<Reservation> list = new ArrayList<>();

         hotelResources.reservationService.getCustomerReservation(hotelResources.customerService.getCustomer(email));

         mainMenu();
    }

    private static void findAndReserveARoom() throws ParseException {
        System.out.println("Enter Check In Date DD/MM/YYYY");
        String inDate = sc.nextLine();
        Date checkIn = null;

        checkIn = new SimpleDateFormat("dd/MM/yyyy").parse(inDate);

        // check -- out -- date
        System.out.println("Enter Check Out Date DD/MM/YYYY");
        String outDate = sc.nextLine();
        Date checkOut = null;

        checkOut = new SimpleDateFormat("dd/MM/yyyy").parse(outDate);

        Collection<IRoom>getAllRooms = hotelResources.findARoom(checkIn,checkOut);
        if (getAllRooms.isEmpty()){
            System.out.println("Room not available at given date");
            roomForAlternateDay(checkIn, checkOut);

        }
        else {
            for(IRoom room : getAllRooms){
                System.out.println(room);
            }
            reserveARoom(checkIn,checkOut,getAllRooms);

        }
    }

    private static void reserveARoom(Date checkIn, Date checkOut, Collection<IRoom> getAllRooms) throws ParseException {
        System.out.println("Do you want to reserve a room: y/n ");
        String resChoice = sc.nextLine();
        if(resChoice.equals("y")){

            System.out.println("Do you have an account: ");
            String havAcc = sc.nextLine();

            if(havAcc.equals("y")){
                System.out.println("pls enter your email: ");
                String email = sc.nextLine();
                if(hotelResources.getACustomer(email) == null){
                    System.out.println("Customer Not Found: ");
                    mainMenu();
                }
                else {
                    System.out.println("Enter room no you want to rserve: ");
                    String roomNo = sc.nextLine();

                    for(IRoom room: getAllRooms){
                        if(room.getRoomNumber().equals(roomNo)){

                            hotelResources.reservationService.reserveARoom(hotelResources.getACustomer(email), room, checkIn,checkOut);
                            System.out.println("room has been successfully reserved");
                            mainMenu();
                        }
                        else {
                            System.out.println("Please enter valid room No: ");
                            reserveARoom(checkIn,checkOut,getAllRooms);
                        }
                    }
                }


            }
            else {
                System.out.println("Pls create an account from main menu:");
                mainMenu();
            }
        }else {
            mainMenu();
        }

    }

    private static void roomForAlternateDay(Date checkIn, Date checkOut) throws ParseException {
        Date altCheckIn = add7days(checkIn);
        Date altCheckOut = add7days(checkOut);

        Collection<IRoom> altRooms = hotelResources.findARoom(altCheckIn,altCheckOut);

        System.out.println("Finding alternate Rooms :- ");

        if (altRooms.isEmpty()){
            System.out.println("Sorry Rooms Are Not Available: ");
            mainMenu();
        }
        else{
            System.out.println(altRooms);
            System.out.println("Do you want to book alternate room? ");
            System.out.println("y/n");

            String choice = sc.nextLine();
            if(choice == "y"){
                reserveARoom(altCheckIn,altCheckOut,altRooms);
            }
            else{
                mainMenu();
            }

        }
    }

    private static Date add7days(Date checkIn) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkIn);
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }
}
