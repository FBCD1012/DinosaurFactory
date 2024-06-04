package com.example.nftmarket;


import com.example.nftmarket.entity.Person;
import com.example.nftmarket.service.PersonContent;
import com.example.nftmarket.service.SaleContent;
import com.example.nftmarket.service.impl.PersonContentImpl;
import com.example.nftmarket.service.impl.SaleContentImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SaleTests {
    @Test
    void saleTest(){
        SaleContent saleContent=new SaleContentImpl();
        Person person=new Person();
        PersonContent personContent=new PersonContentImpl();
        personContent.addTheDinosaurEgg(person);
        System.out.println(person.getDinosaurEggsRepository());
        System.out.println(personContent.toHatchTheDinosaurEgg(person, 0));
        System.out.println(personContent.toHatchTheDinosaurEgg(person, 1));
        System.out.println(saleContent.setTheDinosaurSaleState(person, "FEMALE", 0));
        System.out.println(saleContent.getTheMarket().getDinosaurs());
        System.out.println(saleContent.getTheMarketAveragePrice());
    }
}
