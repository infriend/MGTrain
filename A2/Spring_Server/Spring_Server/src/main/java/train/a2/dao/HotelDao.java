package train.a2.dao;

import org.springframework.stereotype.Repository;
import train.a2.pojo.Hotel;

import java.util.List;

@Repository
public interface HotelDao {
    public List<Hotel> getAll();
    public Hotel getByHotelname(String hotelname);
}
