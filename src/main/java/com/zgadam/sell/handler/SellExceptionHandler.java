package com.zgadam.sell.handler;

import com.zgadam.sell.VO.ResultVO;
import com.zgadam.sell.config.ProjectUrlConfig;
import com.zgadam.sell.exception.SellException;
import com.zgadam.sell.exception.SellerAuthorizeException;
import com.zgadam.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:
 * 异常处理器
 * @author yd
 * @create 2019-03-09 22:05
 */
@ControllerAdvice
@Slf4j
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    //http://sell.natapp4.cc/sell/wechat/qrAuthorize?returnUrl=http://sell.natapp4.cc/sell/seller/login
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    //@ResponseStatus(HttpStatus.FORBIDDEN)//设置返回码
    public ResultVO handlerSellException(SellException e){
        log.warn("捕获到SellException异常");
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
