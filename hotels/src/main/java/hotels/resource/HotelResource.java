package hotels.resource;

import hotels.model.Hotel;
import hotels.model.User;
import hotels.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HotelResource {

    private static final Logger log = LoggerFactory.getLogger(HotelResource.class);

    @Autowired
    private HotelRepository hotelRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/api/hotels")
    public List<Hotel> getHotels() {
        log.info("get all hotels:  ");
        List<Hotel> hotels = hotelRepository.getAllHotels();
        for(Hotel hotel : hotels)
            log.info(hotel.getName());

        return hotels;
    }

    @RequestMapping(value = "api/hotel/{hotelId}", method = RequestMethod.GET)
    public Hotel getHotelById(@Valid @PathVariable Integer hotelId) {

        log.info("Get hotel with id:  " + hotelId);
        return hotelRepository.getHotelById(hotelId);
    }

    @PostMapping(value = "/api/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> addHotel(@RequestBody Hotel hotel) {
        ResponseEntity<?> response;

        try {
            hotel.setId(null);
            hotelRepository.addHotel(hotel);
            response = new ResponseEntity<>(null, HttpStatus.OK);

        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @PostMapping(value = "/api/hotel/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> deleteHotel(@PathVariable Integer hotelId, @RequestBody Hotel hotel) {
        ResponseEntity<?> response;

        try {
            log.info("deleting stuff");
            hotelRepository.removeHotel(hotel);
            response = new ResponseEntity<>(null, HttpStatus.OK);

        } catch (Exception e) {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @PostMapping(value = "/api/hotel", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> updateHotel(@RequestBody Hotel hotel) {
        ResponseEntity<?> response;

        try {
            hotelRepository.updateHotel(hotel);
            response = new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.OK);

        } catch (Exception e) {
            response = new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

        return response;
    }
}
