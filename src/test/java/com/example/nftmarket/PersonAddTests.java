//package com.example.nftmarket;
//
//
//import com.example.nftmarket.entity.Dinosaur;
//import com.example.nftmarket.entity.Person;
//import com.example.nftmarket.service.Breeding;
//import com.example.nftmarket.service.PersonContent;
//import com.example.nftmarket.service.impl.BreedingImpl;
//import com.example.nftmarket.service.impl.PersonContentImpl;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class PersonAddTests {
//    @Test
//    public void addThePerson(){
//        PersonContent initPerson=new PersonContentImpl();
//        Breeding breeding=new BreedingImpl();
//        Person person=new Person();
//        System.out.println(initPerson.addTheDinosaurEgg(person));
//        System.out.println(person.getDinosaurEggsRepository());
//        //下标传入一定是需要进行减一操作的，主要是集合的元素删除的元素下标是会进行更改的
//        System.out.println(initPerson.toHatchTheDinosaurEgg(person, 0));
//        System.out.println(initPerson.toHatchTheDinosaurEgg(person, 1));
//        System.out.println();
//        System.out.println(person.getMaleDinosaurRepository());
//        System.out.println(person.getFeMaleDinosaurRepository());
//        System.out.println(breeding.creatDinosaurEgg(person, breeding.getTheDinosaurMother(person, 0),breeding.getTheDinosaurFather(person, 0)));
//        System.out.println(person.getDinosaurEggsRepository());
//    }
//}
