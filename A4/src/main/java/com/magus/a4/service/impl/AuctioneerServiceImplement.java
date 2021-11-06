package com.magus.a4.service.impl;

import com.magus.a4.dao.AuctionMapper;
import com.magus.a4.dao.BidingpriceMapper;
import com.magus.a4.pojo.Auction;
import com.magus.a4.pojo.Bidingprice;
import com.magus.a4.service.AuctioneerService;
import com.magus.a4.utils.UUIDGeneratorUtil;
import com.magus.a4.vo.AuctioneerAuction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AuctioneerServiceImplement implements AuctioneerService {
    @Autowired
    private AuctionMapper auctionMapper;

    @Autowired
    private BidingpriceMapper bidingpriceMapper;

    @Override
    public int addAuction(Auction auction) {
        UUIDGeneratorUtil uuidGeneratorUtil = new UUIDGeneratorUtil();
        String uid = uuidGeneratorUtil.getUUID();
        auction.setAuctionid(uid);
        if (auction.getEndtime().before(auction.getStarttime())){
            return 0; //不能让endtime小于starttime，自动检测服务会报错
        }
        return auctionMapper.insert(auction);
    }

    @Override
    public int modifyAuction(Auction auction) {
        Auction oldauction = auctionMapper.selectByPrimaryKey(auction.getAuctionid());
        if (oldauction.getStarttime().before(new Date())){
            return auctionMapper.updateByPrimaryKey(auction);
        } else return 0;

    }

    @Override
    public int suspendAuction(String auctionid, String username) {
        Auction temp = auctionMapper.selectByPrimaryKey(auctionid);
        if (temp.getAuctioneer().equals(username)){
            temp.setStatus((short) 0);//0暂停，1正常，2结束但未支付，3结束已支付，4反悔，5取消
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
    public List<AuctioneerAuction> myAuctions(String username) {
        return auctionMapper.getAuctionsByAuctioneer(username);
    }
}
