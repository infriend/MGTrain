package com.magus.a4.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SimpleBidingPrice implements Serializable {
    private String username;
    private BigDecimal price;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date bidingtime;
}
