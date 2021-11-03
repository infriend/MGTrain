package com.magus.a4.service;

import com.magus.a4.dao.AuctionMapper;
import com.magus.a4.dao.BidingpriceMapper;
import com.magus.a4.pojo.Auction;
import com.magus.a4.pojo.Bidingprice;
import com.magus.a4.vo.SimpleAuction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ScheduleService {
    @Autowired
    private AuctionMapper auctionMapper;

    @Autowired
    private BidingpriceMapper bidingpriceMapper;

    @Scheduled(fixedDelay=1000)
    public void checkEnd(){
        List<Auction> auctions = auctionMapper.getAuctionsGoingOn();
        for (Auction a: auctions){
            if (a.getEndtime().before(new Date())){
                Bidingprice b = bidingpriceMapper.getMaxBidingPrice(a.getAuctionid());
                a.setStatus((short) 2);
                a.setWinner(b.getUsername());
            }
        }
    }
}
