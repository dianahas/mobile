package hotels;

import hotels.model.Hotel;
import hotels.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class Controller {
    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    List<Hotel> hotels = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public Controller() {
        users.add(new User(1, "aa@gmail.com", "aa", true));
        users.add(new User(2, "bb@gmail.com", "bb", false));
        users.add(new User(3, "cc@gmail.com", "cc", true));
        users.add(new User(4, "diana@gmail.com", "diana", true));
        users.add(new User(5, "dianaAdmin@gmail.com", "diana", true));

        hotels.add(new Hotel(1, "Helka", "Italy"));
        hotels.add(new Hotel(2, "AnaMaria", "Romania"));
        hotels.add(new Hotel(3, "Brita", "Italy"));
        hotels.add(new Hotel(4, "Hossu", "Germany"));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/login")
    ResponseEntity<?> login(@RequestBody User user) {
        for (User u : users) {
            if (u.getPassword().equals(user.getPassword()) && u.getEmail().equals(user.getEmail())) {
                log.info(user.getEmail() + "   " + user.getPassword());
                return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/hotels")
    public List<Hotel> getHotels() {
        log.info("get all hotels");
        return hotels;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/hotels")
    ResponseEntity<?> addHotel(@RequestBody Hotel hotel) {
        hotel.setId(new Random().nextInt(100));
        hotels.add(hotel);
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/hotel/{hotelId}")
    ResponseEntity<?> deleteHotel(@PathVariable int hotelId) {
        for (Hotel hotel : hotels) {
            if (hotel.getId().equals(hotelId)) {
                hotels.remove(hotel);
                return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/hotel/{hotelId}")
    ResponseEntity<?> updateHotel(@PathVariable int hotelId, @RequestBody Hotel hotel) {
        for (Hotel h : hotels) {
            if (h.getId().equals(hotelId)) {
                h.setName(hotel.getName());
                h.setLocation(hotel.getLocation());
                return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
