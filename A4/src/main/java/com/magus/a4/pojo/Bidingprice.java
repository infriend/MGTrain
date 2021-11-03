package com.magus.a4.pojo;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class Bidingprice {
    private String bidinguid;

    private String bidingno;

    private String auctionid;

    private String username;

    private BigDecimal price;

    private Date bidingtime;
}