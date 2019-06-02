package com.shop.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shop.pojo.Auction;
import com.shop.pojo.AuctionCustomer;
import com.shop.pojo.Auctionrecord;
import com.shop.pojo.User;
import com.shop.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class AuctionController {
    // 每页记录数
    private static final  int PAGE_SIZE = 5;

    @Autowired
    private AuctionService auctionService;

    @RequestMapping(value = "/queryAllAuctions")
    public ModelAndView queryAllAuctions(@ModelAttribute("condition") Auction auction,
                                         @RequestParam(name = "pageNum",
                                                 required = false,
                                                 defaultValue = "1") int pageNum)
    {
        ModelAndView modelAndView = new ModelAndView();
        //分页处理

        PageHelper.startPage(pageNum,PAGE_SIZE);

        List<Auction> auctionList = this.auctionService.queryAllAuctions(auction);


        // 获取分页的数据
        PageInfo page  = new PageInfo(auctionList);

        // 上一页
        page.getPrePage();

        // 下一页
        page.getNextPage();

        // 总记录数
        page.getTotal();

        // 获取总页码
        page.getPages();

        // 获取当前页码
        page.getPageNum();

        modelAndView.addObject("auctionList",auctionList);

        modelAndView.addObject("page",page);


        modelAndView.setViewName("index");
        return modelAndView;
    }


    // 查询出竞拍详情
    @RequestMapping(value = "/findAuctionDetial/{auctionid}")
    public ModelAndView findAuctionDetial(@PathVariable int auctionid)
    {
        ModelAndView modelAndView = new ModelAndView();
        Auction auctionDetail = this.auctionService.selectAuctionAndAuctionRecordList(auctionid);


        System.out.println("auctionDetail-------------------------"+auctionDetail);


      modelAndView.addObject("auctionDetail",auctionDetail);
      modelAndView.setViewName("auctionDetail");
        return modelAndView;
    }


    @RequestMapping(value = "/saveAuctionRecord")
    //竞拍具体实现
    public String saveAuctionRecord(Auctionrecord auctionrecord,
                                    HttpSession session) throws Exception {
        // 封装userId
        User user = (User) session.getAttribute("user");

        auctionrecord.setUserid(user.getUserid());

        //封装time
        auctionrecord.setAuctiontime(new Date());


        this.auctionService.saveAuctionRecord(auctionrecord);

        return "redirect:/findAuctionDetial/"+auctionrecord.getAuctionid();

    }

    @RequestMapping(value = "/toAuctionResult")
    public ModelAndView toAuctionResult()
    {
        ModelAndView modelAndView = new ModelAndView();
        List<AuctionCustomer> endtimeList = this.auctionService.selectAuctionendtime();

        List<Auction> noendtimeList = this.auctionService.selectAuctionNoendtime();

        modelAndView.addObject("endtimeList",endtimeList);
        modelAndView.addObject("noendtimeList",noendtimeList);


        modelAndView.setViewName("auctionResult");

        return modelAndView;



    }

    // 跳转到发布拍卖品页面
    @RequestMapping(value = "/toAuctionPage")
    public String toAuctionPage()
    {
        return "addAuction";
    }

    // 发布商品实现
    @RequestMapping(value = "/publishAuctions")
    public String publishAuctions(Auction auction, MultipartFile pic) throws IOException {

        // 封装 图片的类型
        auction.setAuctionpictype(pic.getContentType());
        // 封装图片的名称
        //auction.setAuctionname(pic.getOriginalFilename());
        auction.setAuctionpic(pic.getOriginalFilename());


        File file = new File("E:\\pic\\"+pic.getOriginalFilename());
        // 上传
        pic.transferTo(file);

       this.auctionService.addAuction(auction);

       return "redirect:/queryAllAuctions";
    }

    @ResponseBody
    @RequestMapping("/deleteAuction")
    public String delteAuction(Integer auctionid){
        int row=this.auctionService.delteAuction(auctionid);
        System.out.println(row);
        if(row>0){
            return "OK";
        }else {
            return "FALL";
        }
    }
}
