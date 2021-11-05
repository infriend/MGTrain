package com.magus.a4.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SimpleAuction {
    private String auctionid;
    private String lotname;
    private BigDecimal startingprice;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date starttime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date endtime;
}
