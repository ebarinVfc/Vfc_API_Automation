package com.vf.vfcApiEndPoints.consumers_eapi.qa.v2;

import java.util.Scanner;

public class MultipleConsumerCreat {

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number of consumers to create: ");
            int numOfConsumers = scanner.nextInt();

            for (int i = 1; i <= numOfConsumers; i++) {
                Sign_Up_Post signUpPost = new Sign_Up_Post();
                signUpPost.ConsumerSignUp();
                System.out.println("Consumer #" + i + " created: " + signUpPost.getConsumerId() + " " + signUpPost.getConsumerEmail());
            }
        }

}
