package com.zgadam.sell.controller;

import com.zgadam.sell.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 秒杀控制器
 * @author yd
 * @create 2019-03-20 09:59
 */
@RestController
@RequestMapping("/skill")
@Slf4j
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    /**
     * 查询秒杀的活动特价商品的信息
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId) throws Exception{
        return seckillService.querySeckillProductInfo(productId);
    }

    /**
     * 秒杀，没有抢到获得“哎呦喂，太拥挤了”， 抢到了会返回剩余的库存量
     * @param productId
     * @return
     * @throws Exception
     */
    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId) throws  Exception{
        log.info("@Skill resquest, productId：" + productId);
        seckillService.orderProductMockDiffUser(productId);
        return seckillService.querySeckillProductInfo(productId);
    }

}
