package train.a2.service;

import java.util.List;

public interface AdminService {
    public int adminLogin(String username, String passwd);
    public int deleteCustomer(String username);
    public String resetPasswd(String username);
    public List<String> getAllUsername();
}
