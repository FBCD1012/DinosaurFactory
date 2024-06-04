package com.example.nftmarket.service;

import com.example.nftmarket.entity.Market;
import com.example.nftmarket.entity.Person;

public interface SaleContent {
    //购买恐龙
    String purchasingTheDinosaur(Person person1,String Hash);
    //上架市场（恐龙）
    /**
     * 参数：传入用户地址。并且选择对应的恐龙进行售卖状态的替换
     * */
    String setTheDinosaurSaleState(Person person,String genderType,Integer index);
    //获取市场均价
    Double getTheMarketAveragePrice();
    //获取市场最高价
    Double getTheMarketHighestPrice();
    //获取市场最低价
    Double getTheLowestPrice();
    Market getTheMarket();
}
