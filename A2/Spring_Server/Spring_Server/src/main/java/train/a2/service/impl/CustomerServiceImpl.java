package train.a2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import train.a2.dao.BooktableDao;
import train.a2.dao.CustomerDao;
import train.a2.dao.HotelDao;
import train.a2.dao.SuiteDao;
import train.a2.others.SnowFlake;
import train.a2.pojo.Booktable;
import train.a2.pojo.Customer;
import train.a2.pojo.Hotel;
import train.a2.pojo.Suiteinfo;
import train.a2.service.CustomerService;
import train.a2.vo.hotelVo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao = null;

    @Autowired
    private HotelDao hotelDao = null;

    @Autowired
    private SuiteDao suiteDao = null;

    @Autowired
    private BooktableDao booktableDao = null;

    @Override
    public int insertCustomer(Customer c) {
        return customerDao.insertCustomer(c);
    }

    @Override
    public int updateCustomer(Customer c) {
        return customerDao.updateCustomer(c);
    }

    @Override
    public Customer getByUsername(String username) {
        return customerDao.getByUsername(username);
    }

    @Override
    public int customerLogin(String username, String passwd) {
        Customer c = customerDao.getByUsername(username);
        if (c == null) {
            return 0;
        } else if (username.equals(c.getUsername()) && !passwd.equals(c.getPasswd())) {
            return 1;
        } else { //查询用户列表，得到用户名list，转到视图admin-index
            return 2;
        }
    }

    @Override
    public List<hotelVo> getHotelList() {
        List<Hotel> hotels = hotelDao.getAll();
        List<hotelVo> hvos = new ArrayList<hotelVo>();
        for (int i=0; i<hotels.size(); i++){
            hotelVo temp = new hotelVo();
            temp.setHotelname(hotels.get(i).getHotelname());
            temp.setAddr(hotels.get(i).getAddr());
            temp.setTel(hotels.get(i).getTel());
            hvos.add(temp);
        }
        return hvos;
    }

    @Override
    public Hotel getHotel(String hotelname) {
        return hotelDao.getByHotelname(hotelname);
    }

    @Override
    public List<Suiteinfo> getSuiteByHotelname(String hotelname) {
        return suiteDao.getByHotelname(hotelname);
    }

    @Override
    public Suiteinfo getSuite(String hotelname, String suitetype) {
        return suiteDao.getSuite(hotelname, suitetype);
    }

    @Override
    public int insertBooktable(Booktable b) {
        return booktableDao.insertBooktable(b);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int suiteRemain(String hotelname, String suitetype, Date date) {
        Suiteinfo suite = suiteDao.getSuite(hotelname, suitetype);
        Integer booked = booktableDao.getBookNumByDate(hotelname, suitetype, date);
        if (booked==null) booked = 0;

        return suite.getNum()-booked;
    }

    @Override
    public List<Booktable> getBooktableByName(String username) {
        return booktableDao.getBooktableByName(username);
    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, timeout = 1)
    public int seckill(String username, String hotelname, String suitetype, Date date) {
        Suiteinfo suite = suiteDao.getSuite(hotelname, suitetype);
        Integer booked = booktableDao.getBookNumByDate(hotelname, suitetype, date);
        if (booked==null) booked = 0;
        int num = suite.getNum()-booked;
        if (num > 0){
            SnowFlake snowFlake = new SnowFlake(1, 1);
            Booktable b = new Booktable();
            b.setUsername(username);
            b.setBooknum(snowFlake.nextId());
            b.setHotelname(hotelname);
            b.setSuitetype(suitetype);
            b.setIntime(date);
            b.setPeoplenum(1);
            Calendar dd = Calendar.getInstance();
            dd.setTime(date);
            dd.add(Calendar.DAY_OF_MONTH, 1);
            java.util.Date temp = dd.getTime();
            b.setOuttime(new java.sql.Date(temp.getTime()));
            b.setCount(1);
            b.setPayment(suiteDao.getPrice(hotelname, suitetype));
            booktableDao.insertBooktable(b);
            return 1;
        } else
            return 0;
    }
}
