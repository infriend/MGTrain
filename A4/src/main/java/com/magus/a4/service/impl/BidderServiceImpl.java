package com.magus.a4.service.impl;

import com.magus.a4.dao.AuctionMapper;
import com.magus.a4.dao.BidingpriceMapper;
import com.magus.a4.dao.EnrollmentMapper;
import com.magus.a4.pojo.Auction;
import com.magus.a4.pojo.Bidingprice;
import com.magus.a4.pojo.Enrollment;
import com.magus.a4.service.BidderService;
import com.magus.a4.utils.UUIDGeneratorUtil;
import com.magus.a4.vo.SimpleAuction;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class BidderServiceImpl implements BidderService {
    @Autowired
    private EnrollmentMapper enrollmentMapper;

    @Autowired
    private BidingpriceMapper bidingpriceMapper;

    @Autowired
    private AuctionMapper auctionMapper;

    @Override
    public int enrollAuction(String auctionid, String username) {
        //先查是否已经报名了，再进行插入，事实上应该先查报名，然后重定向支付接口，再由支付接口跳转这里
        UUIDGeneratorUtil uuidGeneratorUtil = new UUIDGeneratorUtil();
        Enrollment enrollment = enrollmentMapper.getByAuctionidAndUsername(auctionid, username);
        if (enrollment==null){
            Enrollment newenrollment = new Enrollment();
            newenrollment.setAuctionid(auctionid);
            newenrollment.setUsername(username);
            String uid = uuidGeneratorUtil.getUUID();
            newenrollment.setEnrolluid(uid);
            return enrollmentMapper.insert(newenrollment);
        }else {
            return 0;
        }

    }

    @Override
    public int bidding(String auctionid, String username, BigDecimal price) {
        //先查出价列表最高的，再检查当前出价是否更高，是否是自增倍数，再检查项目截止时间，如果截止时间小于5min，自动延长5min，最后再提交
        Bidingprice bidingprice = bidingpriceMapper.getMaxBidingPrice(auctionid);
        Auction auction = auctionMapper.selectByPrimaryKey(auctionid);
        UUIDGeneratorUtil uuidGeneratorUtil = new UUIDGeneratorUtil();
        BigDecimal mod = price.divideAndRemainder(auction.getIncrement())[1];

        if (price.compareTo(bidingprice.getPrice())==1 && mod.equals(0)){
            Bidingprice newbiding = new Bidingprice();
            newbiding.setAuctionid(auctionid);
            newbiding.setUsername(username);
            newbiding.setPrice(price);
            newbiding.setBidingtime(new Date());
            String uid = uuidGeneratorUtil.getUUID();
            newbiding.setBidinguid(uid);
            String bidingno = RandomStringUtils.randomAlphanumeric(10);
            newbiding.setBidingno(bidingno);

            long nd = 1000 * 24 * 60 * 60;
            long nh = 1000 * 60 *60;
            long nm = 1000 * 60;
            Date endtime = auction.getEndtime();
            Date nowtime = new Date();
            long diff = endtime.getTime() - nowtime.getTime();
            if (diff > 0){ //时间没有超过截止时间
                long minute = diff/nm;
                if (minute < 5){ //时间小于5分钟
                    nowtime = new Date(nowtime.getTime()+300000);
                    auction.setEndtime(nowtime);
                    auctionMapper.updateByPrimaryKey(auction);
                }
                return bidingpriceMapper.insert(newbiding);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public int confirmHammer(String auctionid, String username, int status) {
        //依然不写支付接口，直接确认已经付了钱或者反悔
        Auction auction = auctionMapper.selectByPrimaryKey(auctionid);
        if (status == 1){
            if (auction.getWinner().equals(username)){
                auction.setStatus((short) 3);
                return auctionMapper.updateByPrimaryKey(auction);
            } else {
                return 0;
            }
        } else {
            auction.setStatus((short) 4);
            return auctionMapper.updateByPrimaryKey(auction);
        }
    }

    @Override
    public List<SimpleAuction> getMyAuctions(String username) {
        //查所有该用户名的Enrollment，根据auctionid查找
        
        return null;
    }
}
