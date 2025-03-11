import java.util.ArrayList;
import java.util.List;

// User class
class User {
    private String name;
    private String email;
    
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }
}

// Room class
class Room {
    private int roomNumber;
    private String type;  // Single, Double, Suite
    private double price;
    private boolean isBooked;

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }

    public boolean isAvailable() {
        return !isBooked;
    }

    public void bookRoom() {
        isBooked = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}

// Hotel class (contains rooms and reservation system)
class Hotel {
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Room findAvailableRoom(String type) {
        for (Room room : rooms) {
            if (room.isAvailable() && room.getType().equalsIgnoreCase(type)) {
                return room;
            }
        }
        return null;
    }

    public void showAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room " + room.getRoomNumber() + " - " + room.getType() + " - $" + room.getPrice());
            }
        }
    }
}

// Payment class
class Payment {
    public static void processPayment(User user, Room room, String method) {
        System.out.println(user.getName() + " has paid $" + room.getPrice() + " via " + method);
    }
}

// Main class
public class HotelReservationSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        
        // Add rooms
        hotel.addRoom(new Room(101, "Single", 100));
        hotel.addRoom(new Room(102, "Double", 150));
        hotel.addRoom(new Room(103, "Suite", 300));

        // Show available rooms
        hotel.showAvailableRooms();

        // User wants to book a room
        User user = new User("Alice", "alice@example.com");
        Room selectedRoom = hotel.findAvailableRoom("Double");

        if (selectedRoom != null) {
            selectedRoom.bookRoom();
            System.out.println("Room " + selectedRoom.getRoomNumber() + " booked successfully!");
            Payment.processPayment(user, selectedRoom, "Credit Card");
        } else {
            System.out.println("No available rooms of the selected type.");
        }

        // Show updated availability
        hotel.showAvailableRooms();
    }
}
