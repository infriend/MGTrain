package train.a2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import train.a2.dao.SuiteDao;
import train.a2.service.CustomerService;
import train.a2.service.ManagerService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/concurrent")
public class ConcurrentController {
    @Autowired
    private CustomerService customerService = null;

    @Autowired
    private ManagerService managerService =null;

    @RequestMapping("/customer")
    public ModelAndView seckill(ModelAndView mv) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date bd = new Date(sdf.parse("2021-09-01").getTime());
        int num = customerService.suiteRemain("北京丰大国际大酒店", "豪华双床间", bd);
        mv.addObject("num", num);
        mv.setViewName("customer-seckill");
        return mv;
    }

    @RequestMapping("/customerbuy")
    @ResponseBody
    public ResponseEntity<String> customerbuy() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date bd = new Date(sdf.parse("2021-09-01").getTime());
        int res = customerService.seckill("a5", "北京丰大国际大酒店", "豪华双床间", bd);
        if (res==1){
            System.out.println(res);
            return ResponseEntity.status(200).body("success");
        } else {
            return ResponseEntity.status(500).body("fail");
        }
    }

    @RequestMapping("/manager")
    public ModelAndView managerpage(ModelAndView mv) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date bd = new Date(sdf.parse("2021-09-01").getTime());
        int num = customerService.suiteRemain("北京丰大国际大酒店", "豪华双床间", bd);
        mv.addObject("num", num);
        mv.setViewName("addinventory");
        return mv;
    }

    @RequestMapping("/addinventory")
    @ResponseBody
    public ResponseEntity<String> addinventory(@RequestParam("num") int num){
        int res = managerService.addinventory("北京丰大国际大酒店", "豪华双床间", num);
        if (res==1){
            return ResponseEntity.status(200).body("success");
        } else {
            return ResponseEntity.status(500).body("fail");
        }
    }

    @RequestMapping("/managerbuy")
    @ResponseBody
    public ResponseEntity<String>    managerbuy() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date bd = new Date(sdf.parse("2021-09-01").getTime());
        int res = customerService.seckill("a5", "北京丰大国际大酒店", "豪华双床间", bd);
        if (res==1){
            return ResponseEntity.status(200).body("success");
        } else {
            return ResponseEntity.status(500).body("fail");
        }
    }
}
