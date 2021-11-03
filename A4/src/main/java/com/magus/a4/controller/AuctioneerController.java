package com.magus.a4.controller;

import com.magus.a4.pojo.Auction;
import com.magus.a4.pojo.Result;
import com.magus.a4.service.AuctioneerService;
import com.magus.a4.utils.JwtUtil;
import com.magus.a4.utils.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("auctioneer")
public class AuctioneerController {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private AuctioneerService auctioneerService;

    @RequestMapping(value = "/addauction", method = RequestMethod.POST)
    @RequiresRoles("auctioneer")
    public Result addAuction(@RequestBody Auction auction){
        int s = auctioneerService.addAuction(auction);
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("添加失败");
        }
    }

    @RequestMapping(value = "/refuseapply", method = RequestMethod.POST)
    @RequiresRoles("auctioneer")
    public Result refuseAuctionApply(String applyid){
        int s = auctioneerService.refuseAuctionApply(applyid);
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("拒绝失败");
        }

    }

    @RequestMapping(value = "/modifyauction", method = RequestMethod.POST)
    @RequiresRoles("auctioneer")
    public Result modifyAuction(@RequestBody Auction auction){
        int s = auctioneerService.modifyAuction(auction);
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("修改失败");
        }
    }

    @RequestMapping(value = "/suspendauction", method = RequestMethod.POST)
    @RequiresRoles("auctioneer")
    public Result suspendAuction(String auctionid){
        //获得当前用户名，查找拍卖，对应用户名，即可修改状态，否则error
        String username = jwtUtil.getUnionid();
        int s = auctioneerService.suspendAuction(auctionid, username);
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("暂停失败");
        }
    }

    @RequestMapping(value = "/restartauction", method = RequestMethod.POST)
    @RequiresRoles("auctioneer")
    public Result restartAuction(String auctionid){
        String username = jwtUtil.getUnionid();
        int s = auctioneerService.restartAuction(auctionid, username);
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("恢复失败");
        }
    }

    @RequestMapping(value = "/finishauction", method = RequestMethod.POST)
    @RequiresRoles("auctioneer")
    public Result finishAuction(String auctionid){
        String username = jwtUtil.getUnionid();
        int s = auctioneerService.finishAuction(auctionid, username);
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("结束失败");
        }
    }

    @RequestMapping(value = "/myauctions", method = RequestMethod.POST)
    @RequiresRoles("auctioneer")
    public ModelAndView myAuctions(){
        return null;
    }


}
