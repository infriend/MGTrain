package train.a2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import train.a2.dao.AdminDao;
import train.a2.dao.CustomerDao;
import train.a2.pojo.SysAdmin;
import train.a2.service.AdminService;

import java.util.List;
import java.util.Random;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao= null;

    @Autowired
    private CustomerDao customerDao = null;

    @Override
    public int adminLogin(String username, String passwd) {
        SysAdmin admin = adminDao.getbyUsername(username);
        if (admin == null) {
            return 0;
        } else if (username.equals(admin.getUsername()) && !passwd.equals(admin.getPasswd())) {
            return 1;
        } else { //查询用户列表，得到用户名list，转到视图admin-index
            return 2;
        }
    }

    @Override
    public int deleteCustomer(String username) {
        int res = customerDao.deleteCustomer(username);
        return res;
    }

    @Override
    public String resetPasswd(String username) {
        String newpasswd = null;
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<10;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        newpasswd = sb.toString();
        String md5new = DigestUtils.md5DigestAsHex(newpasswd.getBytes());
        int status = customerDao.updatePasswd(username, md5new);
        if (status == 1) {
            return newpasswd;
        } else {
            return "修改失败";
        }
    }

    @Override
    public List<String> getAllUsername() {
        return customerDao.getAllUsername();
    }
}
