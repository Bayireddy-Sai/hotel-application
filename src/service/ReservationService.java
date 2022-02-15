package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService Instance;
    private ReservationService(){}
    public static ReservationService getInstance(){
        if (Instance == null){
            Instance = new ReservationService();
        }
        return Instance;
    }
    Collection<Reservation> reservations = new HashSet<>();
    Map<String,IRoom>  rooms = new HashMap<>();




    public void addRoom(IRoom room){
        rooms.put(room.getRoomNumber(),room);
    }


    public IRoom getARoom(String roomId){
        return rooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate,Date checkOutDate){

        Reservation res = new Reservation(customer,room,checkInDate,checkOutDate);

        reservations.add(res);

        return res;
    }




    public Collection <IRoom> findRooms(Date checkInDate,Date checkOutDate){
        Collection<IRoom> allRooms = rooms.values();
        Collection<Reservation> allReservations = reservations;
        Collection<IRoom> not_availabe = new HashSet<>();

        for(Reservation bookRoom : reservations){
            if(checkInDate.before(bookRoom.getCheckOutDate()) && checkOutDate.after(bookRoom.getCheckInDate())){
                not_availabe.add(bookRoom.getRoom());
            }
        }

        for (IRoom room: not_availabe){

            for (IRoom r : allRooms) {
                if (r == room){
                    allRooms.remove(room);
                }
            }
        }

        return allRooms;

    }

    public Collection<Reservation>getCustomerReservation(Customer customer){
        List<Reservation> list = new ArrayList<>();
        Collection<Reservation> res = reservations;

        for (Reservation res1 : res){
            if(res1.getCustomer() == customer){
                list.add(res1);
            }
        }
        return list;
    }

    public Collection<IRoom> getAllRooms(){
        return rooms.values();
    }

    public void displayReservation (){
        System.out.println(reservations);
    }
}
