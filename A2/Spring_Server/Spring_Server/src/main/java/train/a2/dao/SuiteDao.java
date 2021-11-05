package train.a2.dao;

import org.springframework.stereotype.Repository;
import train.a2.pojo.Suiteinfo;

import java.util.List;

@Repository
public interface SuiteDao {
    public List<Suiteinfo> getByHotelname(String hotelname);
    public Suiteinfo getSuite(String hotelname, String suitetype);
    public int getPrice(String hotelname, String suitetype);
    public int addNum(String hotelname, String suitetype, int num);
}
