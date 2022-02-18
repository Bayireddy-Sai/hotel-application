package model;

import java.util.Objects;

public class Room implements IRoom{
     private final String roomNumber;
     Double price;
     RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return false;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj.getClass() != this.getClass()){
            return false;
        }
        Room room = (Room)obj;

        return Objects.equals(room.roomNumber, this.roomNumber) && Objects.equals(room.price, this.price) && Objects.equals(this.enumeration, room.enumeration);
    }

    @Override
    public int hashCode(){
        return Objects.hash(roomNumber, price, enumeration);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", enumeration=" + enumeration +
                '}';
    }
}
