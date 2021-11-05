package train.a2.pojo;

import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Alias("suite")
public class Suiteinfo {
    private String hotelname;
    private String suitetype;
    private int price;
    private int num;

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getSuitetype() {
        return suitetype;
    }

    public void setSuitetype(String suitetype) {
        this.suitetype = suitetype;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
