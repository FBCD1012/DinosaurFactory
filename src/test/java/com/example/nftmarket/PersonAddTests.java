package com.example.nftmarket;


import com.example.nftmarket.entity.Person;
import com.example.nftmarket.service.PersonContent;
import com.example.nftmarket.service.impl.PersonContentImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonAddTests {
    @Test
    public void addThePerson(){
        PersonContent initPerson=new PersonContentImpl();
        Person person=new Person();
        System.out.println(initPerson.addTheDinosaurEgg(person));
        System.out.println(initPerson.toHatchTheDinosaurEgg(person, 0));
//        System.out.println(initPerson.toHatchTheDinosaurEgg(person, 1));
        System.out.println(person.getDinosaurEggsRepository());
        System.out.println(person.getMaleDinosaurRepository());
        System.out.println(person.getFeMaleDinosaurRepository());
        System.out.println(initPerson.getDinosaurCounts(person));
    }
}
