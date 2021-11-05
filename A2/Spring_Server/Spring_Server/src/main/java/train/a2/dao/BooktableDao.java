package train.a2.dao;

import org.springframework.stereotype.Repository;
import train.a2.pojo.Booktable;

import java.sql.Date;
import java.util.List;

@Repository
public interface BooktableDao {
    public Integer getBookNumByDate(String hotelname, String suitetype, Date date);
    public int insertBooktable(Booktable b);
    public List<Booktable> getBooktableByName(String username);
}
