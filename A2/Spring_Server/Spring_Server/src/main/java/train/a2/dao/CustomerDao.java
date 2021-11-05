package train.a2.dao;

import org.springframework.stereotype.Repository;
import train.a2.pojo.Customer;

import java.util.List;

@Repository
public interface CustomerDao {
    public int insertCustomer(Customer c);
    public int deleteCustomer(String username);
    public int updateCustomer(Customer c);
    public Customer getByUsername(String username);
    public List<String> getAllUsername();
    public int updatePasswd(String username, String passwd);
}
