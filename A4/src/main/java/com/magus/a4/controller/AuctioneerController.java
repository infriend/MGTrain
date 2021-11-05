package com.magus.a4.controller;

import com.magus.a4.pojo.Auction;
import com.magus.a4.pojo.Result;
import com.magus.a4.service.AuctioneerService;
import com.magus.a4.service.CommonService;
import com.magus.a4.utils.ResultUtil;
import com.magus.a4.vo.SimpleAuction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("auctioneer")
public class AuctioneerController {
    @Autowired
    private AuctioneerService auctioneerService;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/addauction", method = RequestMethod.POST)
    @ResponseBody
    public Result addAuction(Auction auction, HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        auction.setAuctioneer(username);
        int s = auctioneerService.addAuction(auction);
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("添加失败");
        }
    }

    @RequestMapping(value = "/modifyauction", method = RequestMethod.POST)
    public Result modifyAuction(@RequestBody Auction auction){
        int s = auctioneerService.modifyAuction(auction);
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("修改失败");
        }
    }

    @RequestMapping(value = "/suspendauction", method = RequestMethod.POST)
    public Result suspendAuction(String auctionid, HttpServletRequest request){
        //获得当前用户名，查找拍卖，对应用户名，即可修改状态，否则error
        String username = (String) request.getSession().getAttribute("username");
        int s = auctioneerService.suspendAuction(auctionid, username);
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("暂停失败");
        }
    }

    @RequestMapping(value = "/restartauction", method = RequestMethod.POST)
    public Result restartAuction(String auctionid, HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        int s = auctioneerService.restartAuction(auctionid, username);
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("恢复失败");
        }
    }

    @RequestMapping(value = "/finishauction", method = RequestMethod.POST)
    public Result finishAuction(String auctionid, HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        int s = auctioneerService.finishAuction(auctionid, username);
        if (s != 0) {
            return ResultUtil.success();
        }else {
            return ResultUtil.error("结束失败");
        }
    }

    @RequestMapping(value = "/myauctions")
    public ModelAndView myAuctions(HttpServletRequest request, ModelAndView mv){
        String username = (String) request.getSession().getAttribute("username");
        List<SimpleAuction> simpleAuctionList = auctioneerService.myAuctions(username);
        mv.addObject("auctions", simpleAuctionList);
        mv.setViewName("AuctioneerAuctionList");
        return mv;
    }

    @RequestMapping(value = "/home")
    public ModelAndView auctioneerHomePage(ModelAndView mv){
        mv.setViewName("auctioneer_index");
        return mv;
    }

    @RequestMapping(value = "/addAuctionPage")
    public ModelAndView addAuctionPage(ModelAndView mv){
        mv.setViewName("auction-add");
        return mv;
    }

    @RequestMapping(value = "/auctiondetails")
    public String auctionDetails(String auctionid){
        return "redirect:/auctiondetails";
    }

    @RequestMapping(value = "/editAuctionPage")
    public ModelAndView editAuctionPage(String auctionid, ModelAndView mv){
        Auction a = commonService.auctionDetails(auctionid);
        mv.addObject("a", a);
        mv.setViewName("auction-edit");
        return mv;
    }

}
