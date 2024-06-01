package com.example.nftmarket;


import com.example.nftmarket.entity.Person;
import com.example.nftmarket.service.Hatched;
import com.example.nftmarket.service.InitPersonContent;
import com.example.nftmarket.service.impl.HatchedImpl;
import com.example.nftmarket.service.impl.InitPersonContentImpl;
import com.example.nftmarket.utils.DinosaurRandomUtils;
import com.example.nftmarket.utils.RandomDinosaurEgg;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonAddTests {
    @Test
    public void addThePerson(){
        InitPersonContent initPerson=new InitPersonContentImpl();
        Person person=new Person();
        System.out.println(initPerson.addTheDinosaurEgg(person));
        System.out.println(initPerson.toHatchTheDinosaurEgg(person, 0));
    }
}
