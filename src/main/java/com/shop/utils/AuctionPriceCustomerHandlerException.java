package com.shop.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 现在我们已经定义好了一个类实现了HandlerExceptionResolver
 *
 * 所以该类就已经具备了拦截异常的功能了
 *
 *
 *
 * exception  -> 存放了被拦截的各种异常机制信息
 *
 *
 *
 *
 */


@Component
public class AuctionPriceCustomerHandlerException implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o,
                                         Exception exception) {

        AuctionPriceException ex = null;

        if(exception instanceof AuctionPriceException)
        {
           ex = (AuctionPriceException) exception;
        }
        else

        {
            System.out.println(exception);
            ex = new AuctionPriceException("未知异常");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMsg",ex.getMessage());
        modelAndView.setViewName("errorPage");

        return modelAndView;
    }
}
