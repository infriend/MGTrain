package com.magus.a4.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SimpleAuction {
    private String auctionid;
    private String lotname;
    private BigDecimal startingprice;
    private Date starttime;
    private Date endtime;
}
