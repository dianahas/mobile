package hotels.repository;

import hotels.model.Hotel;

import java.util.List;

public interface HotelRepository {
    List<Hotel> getAllHotels();

    void addHotel(Hotel hotel);

    void removeHotel(Hotel hotel);

    void updateHotel(Hotel hotel);

    Hotel getHotelById(Integer hotelId);
}
