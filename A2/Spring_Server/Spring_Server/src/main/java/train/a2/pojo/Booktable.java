package train.a2.pojo;

import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Alias(value = "booktable")
public class Booktable {
    private String username;
    private String hotelname;
    private long booknum;
    private Date intime;
    private Date outtime;
    private String suitetype;
    private int peoplenum;
    private int child;
    private float payment;
    private int count;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public long getBooknum() {
        return booknum;
    }

    public void setBooknum(long booknum) {
        this.booknum = booknum;
    }

    public Date getIntime() {
        return intime;
    }

    public void setIntime(Date intime) {
        this.intime = intime;
    }

    public Date getOuttime() {
        return outtime;
    }

    public void setOuttime(Date outtime) {
        this.outtime = outtime;
    }

    public String getSuitetype() {
        return suitetype;
    }

    public void setSuitetype(String suitetype) {
        this.suitetype = suitetype;
    }

    public int getPeoplenum() {
        return peoplenum;
    }

    public void setPeoplenum(int peoplenum) {
        this.peoplenum = peoplenum;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
