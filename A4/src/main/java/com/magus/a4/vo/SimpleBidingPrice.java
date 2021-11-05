package com.magus.a4.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SimpleBidingPrice {
    private String bidingno;
    private Long price;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date bidingtime;
}
