package com.magus.a4.pojo;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Bidingprice {
    private String bidinguid;

    private String bidingno;

    private String auctionid;

    private String username;

    private BigDecimal price;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date bidingtime;
}