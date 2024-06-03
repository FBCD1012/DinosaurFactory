package com.example.nftmarket;


import com.example.nftmarket.entity.Dinosaur;
import com.example.nftmarket.entity.DinosaurEgg;
import com.example.nftmarket.entity.Person;
import com.example.nftmarket.service.Breeding;
import com.example.nftmarket.service.PersonContent;
import com.example.nftmarket.service.TheTreeRelation;
import com.example.nftmarket.service.impl.BreedingImpl;
import com.example.nftmarket.service.impl.PersonContentImpl;
import com.example.nftmarket.service.impl.TheTreeRelationImpl;
import com.example.nftmarket.structs.Node;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.Hash;

import java.util.List;

@SpringBootTest
public class HatchTests {
    @Test
    void hatchTest(){
        Person person=new Person();
        PersonContent personContent=new PersonContentImpl();
        Dinosaur dinosaur=Dinosaur
                .builder()
                .build()
                .setDinosaurId(Hash.sha3String("fbcd"))
                .setIsBreeding(true)
                .setDinosaurPhotoUri(null)
                .setDinosaurSex("MALE")
                .setSourceHash(null)
                .setSaleSate("空闲")
                .setDinosaurOwner(Hash.sha3String(String.valueOf(System.identityHashCode(person))));
        System.out.println(dinosaur);
        Dinosaur dinosaur2=Dinosaur
                .builder()
                .build()
                .setDinosaurId(Hash.sha3String("fbcd2"))
                .setIsBreeding(true)
                .setDinosaurPhotoUri(null)
                .setDinosaurSex("FEMALE")
                .setSourceHash(null)
                .setSaleSate("空闲")
                .setDinosaurOwner(Hash.sha3String(String.valueOf(System.identityHashCode(person))));
        person.getMaleDinosaurRepository().add(dinosaur);
        person.getFeMaleDinosaurRepository().add(dinosaur2);
        Breeding breeding=new BreedingImpl();
        breeding.creatDinosaurEgg(person, breeding.getTheDinosaurMother(person, 0),breeding.getTheDinosaurFather(person, 0));
        List<DinosaurEgg> dinosaurEggsRepository = person.getDinosaurEggsRepository();
        Dinosaur dinosaur3=Dinosaur
                .builder()
                .build()
                .setDinosaurId(Hash.sha3String("fbcd3"))
                .setIsBreeding(true)
                .setDinosaurPhotoUri(null)
                .setDinosaurSex("FEMALE")
                .setSourceHash(null)
                .setSaleSate("空闲")
                .setSourceHash(dinosaurEggsRepository.get(0).getEggId())
                .setDinosaurOwner(Hash.sha3String(String.valueOf(System.identityHashCode(person))));
        Dinosaur dinosaur4=Dinosaur
                .builder()
                .build()
                .setDinosaurId(Hash.sha3String("fbcd4"))
                .setIsBreeding(true)
                .setDinosaurPhotoUri(null)
                .setDinosaurSex("MALE")
                .setSourceHash(null)
                .setSaleSate("空闲")
                .setSourceHash(null)
                .setDinosaurOwner(Hash.sha3String(String.valueOf(System.identityHashCode(person))));
        person.getFeMaleDinosaurRepository().add(dinosaur3);
        person.getMaleDinosaurRepository().add(dinosaur4);
        System.out.println(person.getMaleDinosaurRepository());
        System.out.println(person.getFeMaleDinosaurRepository());
        person.getDinosaurEggsRepository().get(0).setChildHash(dinosaur3.getDinosaurId());
        breeding.creatDinosaurEgg(person, breeding.getTheDinosaurMother(person, 1),breeding.getTheDinosaurFather(person, 1));
        System.out.println(person.getDinosaurEggsRepository());


        TheTreeRelation theTreeRelation=new TheTreeRelationImpl();
        Node<DinosaurEgg> dinosaurEgg=new Node<>(person.getDinosaurEggsRepository().get(1),null);
        System.out.println(theTreeRelation.setTheSimpleNodeChain(dinosaurEggsRepository.get(1), dinosaur4, dinosaur3, dinosaurEgg));


    }
}
