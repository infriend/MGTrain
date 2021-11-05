package train.a2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import train.a2.service.AdminService;
import train.a2.service.CustomerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService = null;

    @Autowired
    private CustomerService customerService = null;

    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String username, String passwd, HttpServletRequest request){
        String md5Password = DigestUtils.md5DigestAsHex(passwd.getBytes());
        int status = adminService.adminLogin(username, md5Password);
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

    @RequestMapping("/adminhome")
    public ModelAndView adminhome(ModelAndView mv){
        mv.setViewName("admin_index");
        return mv;
    }

    @RequestMapping("/memberlist")
    public ModelAndView memberlist(ModelAndView mv){
        List<String> usernamelist = adminService.getAllUsername();
        mv.addObject("users", usernamelist);
        mv.setViewName("member-list");
        return mv;
    }

    @RequestMapping("/deluser")
    @ResponseBody
    public int delUser(String username){
        return adminService.deleteCustomer(username);
    }

    @RequestMapping("/randpasswd")
    public ModelAndView randPasswd(String username, ModelAndView mv){
        String passwd = adminService.resetPasswd(username);
        mv.addObject("passwd", passwd);
        mv.setViewName("randompasswd");
        return mv;
    }

}
