package com.magus.a4.service.impl;

import com.magus.a4.dao.AuctionMapper;
import com.magus.a4.dao.AuctionapplyMapper;
import com.magus.a4.dao.BidingpriceMapper;
import com.magus.a4.pojo.Auction;
import com.magus.a4.pojo.Auctionapply;
import com.magus.a4.pojo.Bidingprice;
import com.magus.a4.service.AuctioneerService;
import com.magus.a4.utils.UUIDGeneratorUtil;
import com.magus.a4.vo.SimpleAuction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctioneerServiceImplement implements AuctioneerService {
    @Autowired
    private AuctionMapper auctionMapper;

    @Autowired
    private AuctionapplyMapper auctionapplyMapper;

    @Autowired
    private BidingpriceMapper bidingpriceMapper;

    @Override
    public int addAuction(Auction auction) {
        UUIDGeneratorUtil uuidGeneratorUtil = new UUIDGeneratorUtil();
        String uid = uuidGeneratorUtil.getUUID();
        auction.setAuctionid(uid);
        return auctionMapper.insert(auction);
    }

    @Override
    public int refuseAuctionApply(String applyid) {
        return auctionapplyMapper.refuseByPrimarykey(applyid);
    }

    @Override
    public int modifyAuction(Auction auction) {
        return auctionMapper.updateByPrimaryKey(auction);
    }

    @Override
    public int suspendAuction(String auctionid, String username) {
        Auction temp = auctionMapper.selectByPrimaryKey(auctionid);
        if (temp.getAuctioneer().equals(username)){
            temp.setStatus((short) 0);//0暂停，1正常，2结束
            return auctionMapper.updateByPrimaryKey(temp);
        } else {
            return 0;
        }
    }

    @Override
    public int restartAuction(String auctionid, String username) {
        Auction temp = auctionMapper.selectByPrimaryKey(auctionid);
        if (temp.getAuctioneer().equals(username)){
            temp.setStatus((short) 1);//0暂停，1正常，2结束
            return auctionMapper.updateByPrimaryKey(temp);
        } else {
            return 0;
        }
    }

    @Override
    public int finishAuction(String auctionid, String username) {
        Auction temp = auctionMapper.selectByPrimaryKey(auctionid);
        if (temp.getAuctioneer().equals(username)){
            temp.setStatus((short) 2);//0暂停，1正常，2结束
            Bidingprice bidingprice = bidingpriceMapper.getMaxBidingPrice(auctionid);
            temp.setWinner(bidingprice.getUsername());
            return auctionMapper.updateByPrimaryKey(temp);
        } else {
            return 0;
        }
    }

    @Override
    public List<SimpleAuction> myAuctions(String username) {
        return null;
    }
}
