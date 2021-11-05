package train.a2.dao;

import org.springframework.stereotype.Repository;
import train.a2.pojo.SysAdmin;

@Repository
public interface AdminDao {
    public SysAdmin getbyUsername(String username);
}
