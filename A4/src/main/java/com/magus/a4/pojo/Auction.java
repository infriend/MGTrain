package com.magus.a4.pojo;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

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

    private Date starttime;

    private Date endtime;

    private String winner;
}