package train.a2.service;

import train.a2.pojo.Booktable;
import train.a2.pojo.Customer;
import train.a2.pojo.Hotel;
import train.a2.pojo.Suiteinfo;
import train.a2.vo.hotelVo;

import java.sql.Date;
import java.util.List;


public interface CustomerService {
    public int insertCustomer(Customer c);
    public int updateCustomer(Customer c);
    public Customer getByUsername(String username);
    public int customerLogin(String username, String passwd);
    public List<hotelVo> getHotelList();
    public Hotel getHotel(String hotelname);
    public List<Suiteinfo> getSuiteByHotelname(String hotelname);
    public Suiteinfo getSuite(String hotelname, String suitetype);
    public int insertBooktable(Booktable b);
    public int suiteRemain(String hotelname, String suitetype, Date date);
    public List<Booktable> getBooktableByName(String username);
    public int seckill(String username, String hotelname, String suitetype, Date date);
}
