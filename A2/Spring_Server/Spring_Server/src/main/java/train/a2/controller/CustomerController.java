package train.a2.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import train.a2.dao.SuiteDao;
import train.a2.others.SnowFlake;
import train.a2.pojo.Booktable;
import train.a2.pojo.Customer;
import train.a2.pojo.Hotel;
import train.a2.pojo.Suiteinfo;
import train.a2.service.CustomerService;
import train.a2.vo.hotelVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService = null;

    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String username, String passwd, HttpServletRequest request){
        String md5Password = DigestUtils.md5DigestAsHex(passwd.getBytes());
        int status = customerService.customerLogin(username, md5Password);
        if (status == 0) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("msg", "用户名错误");
            return map;
        } else if (status == 1) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("msg", "密码错误");
            return map;
        } else {
            request.getSession().setAttribute("users", username);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("msg", "yes");
            return map;
        }
    }

    @RequestMapping("/customerhome")
    public ModelAndView customerhome(ModelAndView mv){
        mv.setViewName("customer_index");
        return mv;
    }

    @RequestMapping("/detail")
    public ModelAndView detail(ModelAndView mv, HttpServletRequest request){
        Object object = request.getSession().getAttribute("users");
        String username = (String) object;
        Customer c = customerService.getByUsername(username);
        mv.addObject("user", c);
        mv.setViewName("member-edit");
        return mv;
    }

    @RequestMapping("/updateinfo")
    @ResponseBody
    public int updateinfo(@RequestParam("username") String username,
                          @RequestParam("passwd") String passwd,
                          @RequestParam("truename") String truename,
                          @RequestParam("tel") String tel,
                          @RequestParam("gender") char gender,
                          @RequestParam("birthday") String birthday) throws ParseException {
        Customer temp = customerService.getByUsername(username);
        temp.setTruename(truename);
        temp.setTel(tel);
        temp.setGender(gender);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date bd = new Date(sdf.parse(birthday).getTime());
        temp.setBirthday(bd);
        if (!passwd.equals("1")){
            String md5Password = DigestUtils.md5DigestAsHex(passwd.getBytes());
            temp.setPasswd(md5Password);
        }
        int status = customerService.updateCustomer(temp);
        return status;
    }

    @RequestMapping("/regist")
    public void register(@RequestParam("username") String username,
                         @RequestParam("passwd") String passwd,
                         @RequestParam("truename") String truename,
                         @RequestParam("gender") char gender,
                         @RequestParam("tel") String tel,
                         @RequestParam("birthday") String birthday,
                         HttpServletResponse response) throws IOException, ParseException {
        //获得信息后密码用md5加密，然后调用mybatis存储，最后转到页面中去
        Customer c = new Customer();
        c.setUsername(username);
        String md5Password = DigestUtils.md5DigestAsHex(passwd.getBytes());
        c.setPasswd(md5Password);
        c.setTruename(truename);
        c.setGender(gender);
        c.setTel(tel);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date bd = new Date(sdf.parse(birthday).getTime());
        c.setBirthday(bd);
        Customer temp = customerService.getByUsername(c.getUsername());
        if (temp!=null){
            response.sendRedirect("/regpage");
        } else {
            int status = customerService.insertCustomer(c);
            if(status==1){
                response.sendRedirect("/");
            } else {
                response.sendRedirect("/regpage");
            }
        }
    }

    @RequestMapping("/hotellist")
    @ResponseBody
    public ModelAndView getHotelList(ModelAndView mv){
        List<hotelVo> hvos = customerService.getHotelList();
        mv.addObject("hotels", hvos);
        mv.setViewName("hotel-list");
        return mv;
    }

    @RequestMapping("/hoteldetails")
    @ResponseBody
    public ModelAndView getHotelDetails(String hotelname, ModelAndView mv){
        Hotel h = customerService.getHotel(hotelname);
        List<Suiteinfo> suites = customerService.getSuiteByHotelname(hotelname);
        mv.addObject("h", h);
        mv.addObject("s", suites);
        mv.setViewName("hotel-details");
        return mv;
    }

    @RequestMapping("/booktable")
    public ModelAndView hotelBooktable(String hotelname, String suitetype, ModelAndView mv, HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("users");
        Suiteinfo s = customerService.getSuite(hotelname, suitetype);
        mv.addObject("username", username);
        mv.addObject("hotelname", hotelname);
        mv.addObject("suitetype", suitetype);
        mv.addObject("price", s.getPrice());
        mv.setViewName("booktable");
        return mv;
    }

    @RequestMapping("/book")
    public ModelAndView bookHotel(@RequestParam("hotelname") String hotelname,
                         @RequestParam("suitetype") String suitetype,
                         @RequestParam("intime") String intime,
                         @RequestParam("outtime") String outtime,
                         @RequestParam("peoplenum") int peoplenum,
                         @RequestParam("child") int child,
                         @RequestParam("payment") float payment,
                         @RequestParam("count") int count, ModelAndView mv, HttpServletRequest request) throws ParseException {
        mv.setViewName("book-result");

        Suiteinfo s = customerService.getSuite(hotelname, suitetype);
        SnowFlake snowFlake = new SnowFlake(1, 1);
        Booktable b = new Booktable();
        b.setUsername((String) request.getSession().getAttribute("users"));
        b.setBooknum(snowFlake.nextId());
        b.setHotelname(hotelname);
        b.setSuitetype(suitetype);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date bd = new Date(sdf.parse(intime).getTime());
        b.setIntime(bd);
        bd = new Date(sdf.parse(outtime).getTime());
        b.setOuttime(bd);

        b.setPeoplenum(peoplenum);
        b.setChild(child);
        b.setPayment(s.getPrice()*count);
        b.setCount(count);

        Calendar dd = Calendar.getInstance();
        java.util.Date temp = b.getIntime();
        dd.setTime(b.getIntime());
        while (temp.getTime()<b.getOuttime().getTime()) {
            if (customerService.suiteRemain(hotelname, suitetype, new java.sql.Date(temp.getTime()))<1){
                mv.addObject("msg", "预定失败");
                return mv;
            }
            dd.add(Calendar.DAY_OF_MONTH, 1);
            temp = dd.getTime();
        }

        int status = customerService.insertBooktable(b);
        if (status==1){
            mv.addObject("msg", "预定成功");
        } else {
            mv.addObject("msg", "预定失败");
        }

        return mv;
    }

    @RequestMapping("/booklist")
    @ResponseBody
    public ModelAndView getBooktableList(ModelAndView mv, HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("users");
        List<Booktable> bl = customerService.getBooktableByName(username);
        mv.addObject("bl", bl);
        mv.setViewName("book-list");
        return mv;
    }


}
