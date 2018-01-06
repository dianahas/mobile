package hotels.repository;

import hotels.model.Hotel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class HotelRepositoryImpl implements HotelRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hotel> getAllHotels() {
        Query query = entityManager.createQuery("SELECT h FROM Hotel h");

        return query.getResultList();
    }

    @Override
    public void addHotel(Hotel hotel) {
        entityManager.persist(hotel);
    }

    @Override
    public void removeHotel(Hotel hotel) {
        entityManager.remove(hotel);
    }

    @Override
    public void updateHotel(Hotel hotel) {
        entityManager.merge(hotel);
    }

    @Override
    public Hotel getHotelById(Integer hotelId) {
        return entityManager.find(Hotel.class, hotelId);
    }
}
