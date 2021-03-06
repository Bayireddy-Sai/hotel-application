package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResources {
    private static HotelResources Instance ;
    private HotelResources(){}
    public static HotelResources getInstance() {
        if (Instance == null) {
            Instance = new HotelResources();
        }
        return Instance;
    }
    public ReservationService reservationService = ReservationService.getInstance();
    public CustomerService customerService = CustomerService.getInstance();

    public Customer getACustomer(String email){
        return customerService.getCustomer(email);
    }
    public void createACustomer(String email,String firstName,String lastName){
        customerService.addCustomer(email,firstName,lastName);

    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
       return reservationService.reserveARoom(customerService.getCustomer(customerEmail),room,checkInDate,checkOutDate);
    }

    public Collection<Reservation>getCustomerReservation(String customerEmail){
       return reservationService.getCustomerReservation(customerService.getCustomer(customerEmail));
    }
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn,checkOut);
    }

}
