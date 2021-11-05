package com.magus.a4.pojo;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Auction {
    private String auctionid;

    private String lotname;

    private BigDecimal startingprice;

    private BigDecimal reserveprice;

    private BigDecimal deposit;

    private BigDecimal increment;

    private String lawinfo;

    private String description;

    private String salecondition;

    private Short status;

    private String auctioneer;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date starttime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date endtime;

    private String winner;
}