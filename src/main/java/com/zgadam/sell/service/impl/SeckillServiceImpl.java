package com.zgadam.sell.service.impl;

import com.zgadam.sell.exception.SellException;
import com.zgadam.sell.service.SeckillService;
import com.zgadam.sell.utils.KeyUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * @author yd
 * @create 2019-03-20 10:09
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    /**
     * 国庆活动，皮蛋粥特价，限量100000份
     */
    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;
    static {
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456", 100000);
        stock.put("123456", 100000);
    }

    private String queryMaP(String productId){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("国庆特价活动，皮蛋粥特价，限量份")
                .append(products.get(productId))
                .append("还剩：")
                .append(stock.get(productId))
                .append(" 份")
                .append(" ,该商品成功下单用户数目：")
                .append(orders.size())
                .append(" 人");

        return stringBuffer.toString();
    }



    @Override
    public String querySeckillProductInfo(String productId) {
        return queryMaP(productId);
    }

    @Override
    public void orderProductMockDiffUser(String productId) {
        //查询该商品库存，为0则活动结束
        int stockNum = stock.get(productId);
        if (stockNum == 0){
            throw  new SellException(100, "活动结束");
        }else {
            //2.下单（模拟不同用户openid）
            orders.put(KeyUtil.genUniqueKey(), productId);
            //3.减库存
            stockNum -= 1;
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }
    }
}
