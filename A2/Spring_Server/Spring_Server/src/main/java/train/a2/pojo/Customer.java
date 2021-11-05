package train.a2.pojo;

import org.apache.ibatis.type.Alias;

import java.sql.Date;

@Alias(value = "customer")
public class Customer extends User{
    private String truename;
    private char gender;
    private String tel;
    private Date birthday;

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
