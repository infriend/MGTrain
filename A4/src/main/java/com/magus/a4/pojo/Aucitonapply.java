package com.magus.a4.pojo;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Aucitonapply {
    private String applyid;

    private String lotname;

    private BigDecimal reserveprice;

    private String username;

    private String description;

    private Short status;
}