package com.example.nftmarket.service.impl;

import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.Market;
import com.example.nftmarket.entity.Person;
import com.example.nftmarket.service.SaleContent;
import com.example.nftmarket.utils.HashUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Comparator;
import java.util.List;


//实现具体的商城逻辑
@Service
public class SaleContentImpl implements SaleContent {
    static Market market;
    static {
         market=new Market();
    }
    //此处需要对合约进行交互操作
    @Override
    public String purchasingTheDinosaur(Person person,String Hash) {
        List<Dinosaur> dinosaurs = market.getDinosaurs();
        for (Dinosaur dinosaur: dinosaurs) {
            if (dinosaur.getDinosaurId().equals(Hash) && person.getDinosaurCounts()>dinosaur.getDinosaurPrice()){
                String dinosaurSex = dinosaur.getDinosaurSex();
                switch (dinosaurSex){
                    case "MALE" -> {
                        dinosaur.setSaleSate("空闲");
                        dinosaur.setDinosaurOwner(HashUtils.getHashIndex(person));
                        person.getMaleDinosaurRepository().add(dinosaur);
                        return "交易成功";
                    }
                    case "FEMALE" -> {
                        dinosaur.setSaleSate("空闲");
                        dinosaur.setDinosaurOwner(HashUtils.getHashIndex(person));
                        person.getFeMaleDinosaurRepository().add(dinosaur);
                        return "交易成功";
                    }
                }
            }else {
                return "没有类似的恐龙或者钱不够";
            }
        }
        return "交易失败";
    }
    @Override
    public String setTheDinosaurSaleState(Person person,String genderType,Integer index) {
        Dinosaur dinosaurFather = person.getMaleDinosaurRepository().get(index);
        Dinosaur dinosaurMother = person.getMaleDinosaurRepository().get(index);
        switch (genderType){
            case "MALE"  -> {
                if (person.getMaleDinosaurRepository().get(index).getIsBreeding()){
                    //如果孵化状态是true，那么就能够进行上架操作，上架的操作必须是保证完善的
                    dinosaurFather.setSaleSate("售卖中");
                    //添加恐龙信息到售卖市场进行操作
                    person.getDinosaurSaleRepository().add(dinosaurFather);
                    person.getMaleDinosaurRepository().remove(dinosaurFather);
                    market.getDinosaurs().add(dinosaurFather);
                }
            }
            case "FEMALE"-> {
                if (person.getFeMaleDinosaurRepository().get(index).getIsBreeding()){
                    //如果孵化状态是true，那么就能够进行上架操作，上架的操作必须是保证完善的
                    dinosaurMother.setSaleSate("售卖中");
                    person.getDinosaurSaleRepository().add(dinosaurMother);
                    person.getFeMaleDinosaurRepository().remove(dinosaurMother);
                    market.getDinosaurs().add(dinosaurMother);
                }
            }
        }
        //检验是否上架成功
        if (market.getDinosaurs().contains(dinosaurFather) || market.getDinosaurs().contains(dinosaurMother)){
            return "上架成功";
        }
        return "上架失败";
    }

    @Override
    public Double getTheMarketAveragePrice() {
        Double averagePrice = 0.0;
        List<Dinosaur> dinosaurs = market.getDinosaurs();
        Assert.notNull(dinosaurs,"市场无任何NFT售卖中");
        for (Dinosaur dinosaur: dinosaurs) {
            averagePrice += dinosaur.getDinosaurPrice();
        }
        return averagePrice/dinosaurs.size();
    }

    /**
     * List<Employee> employees = new ArrayList<>();
     * // 假设employees集合中存储了 Employee 对象
     * Employee maxAgeEmployee = employees.stream()
     *         .max(Comparator.comparingInt(Employee::getAge))
     *         .orElseThrow(NoSuchElementException::new);
     * Employee minAgeEmployee = employees.stream()
     *         .min(Comparator.comparingInt(Employee::getAge))
     *         .orElseThrow(NoSuchElementException::new);
     * */
    @Override
    public Double getTheMarketHighestPrice() {
        Dinosaur dinosaur = market.getDinosaurs().stream()
                .max(Comparator.comparingDouble(Dinosaur::getDinosaurPrice))
                .orElse(null);
        Assert.notNull(dinosaur, "市场无任何NFT售卖中");
        return dinosaur.getDinosaurPrice();
    }

    @Override
    public Double getTheLowestPrice() {
        Dinosaur dinosaur = market.getDinosaurs().stream()
                .min(Comparator.comparingDouble(Dinosaur::getDinosaurPrice))
                .orElse(null);
        Assert.notNull(dinosaur, "市场无任何NFT售卖中");
        return dinosaur.getDinosaurPrice();
    }
}
