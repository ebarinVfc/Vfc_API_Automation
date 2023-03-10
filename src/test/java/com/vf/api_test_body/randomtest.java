package com.vf.api_test_body;


import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

public class randomtest {
    @Test
    public void test(){

        Faker faker= new Faker();
        System.out.println(faker.number().numberBetween(1000000,9999999));
        System.out.println(faker.internet().emailAddress());
        System.out.println(faker.phoneNumber().cellPhone());


    }


}
