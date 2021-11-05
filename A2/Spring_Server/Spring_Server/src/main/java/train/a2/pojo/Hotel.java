package train.a2.pojo;

import org.apache.ibatis.type.Alias;

@Alias(value = "hotel")
public class Hotel {
    private String hotelname;
    private String addr;
    private String intro;
    private String tel;
    private String shop;
    private String star;
    private int parklot;
    private int elec_park;
    private int wifi;
    private int wakeservice;
    private int luggage;
    private int elevator;
    private int aircon;

    public int getParklot() {
        return parklot;
    }

    public void setParklot(int parklot) {
        this.parklot = parklot;
    }

    public int getElec_park() {
        return elec_park;
    }

    public void setElec_park(int elec_park) {
        this.elec_park = elec_park;
    }

    public int getWifi() {
        return wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public int getWakeservice() {
        return wakeservice;
    }

    public void setWakeservice(int wakeservice) {
        this.wakeservice = wakeservice;
    }

    public int getLuggage() {
        return luggage;
    }

    public void setLuggage(int luggage) {
        this.luggage = luggage;
    }

    public int getElevator() {
        return elevator;
    }

    public void setElevator(int elevator) {
        this.elevator = elevator;
    }

    public int getAircon() {
        return aircon;
    }

    public void setAircon(int aircon) {
        this.aircon = aircon;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
