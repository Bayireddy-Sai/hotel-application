import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    static Scanner sc = new Scanner(System.in);
    public static AdminResource adminResource = AdminResource.getInstance();

    public static void adminMenu() throws ParseException {
        System.out.println("""
                Welcome To Hotel Reservation Applictaion
                ************* Admin Menu ***************
                1. check all customers
                2. check all rooms
                3. check all reservations 
                4. add a room
                5. main menu
                  
                Select an option from the menu
                """);

        int option = Integer.parseInt(sc.nextLine());

        switch (option) {
            case 1 -> checkAllCustomers();
            case 2 -> checkAllRooms();
            case 3 -> checkAllReservations();
            case 4 -> addARoom();
            case 5 -> MainMenu.mainMenu();
        }
    }

    private static void addARoom() throws ParseException {
        try {
            List<IRoom> room = new ArrayList<>();

            System.out.println("Enter Room NO:");
            String roomNo = sc.nextLine();

            System.out.println("Enter The Price:");
            String price = sc.nextLine();
            Double cost = Double.valueOf(price);

            System.out.println("Enter Room Type : SINGLE/ DOUBLE");
            String roomType = sc.nextLine();


            Room rrrr = new Room(roomNo,cost, RoomType.valueOf(roomType));
            room.add(rrrr);

            adminResource.addRoom(room);

            adminMenu();
        }catch (Exception e){
            System.out.println("pls provide valid input ");
            addARoom();
        }

    }

    private static void checkAllReservations() throws ParseException {
        adminResource.displayAllResrvations();
        adminMenu();
    }

    private static void checkAllRooms() throws ParseException {

        Collection<IRoom> rooms = adminResource.getAllRooms();

//        List<IRoom> rooms = new ArrayList<>(roms);

        if(rooms.isEmpty()){
            System.out.println("No rooms available: ");
            adminMenu();
        }
        else {
            for(IRoom room: rooms){
                System.out.println("Room: " + room.toString());
            }
            adminMenu();
        }

    }

    private static void checkAllCustomers() throws ParseException {
        Collection<Customer> collection = adminResource.getAllCustomers();

        List<Customer> list = new ArrayList<>(collection);

        if(list.isEmpty()){
            System.out.println("NO customers");
            adminMenu();
        }
        else{
            System.out.println(list);
            adminMenu();
        }
    }
}
