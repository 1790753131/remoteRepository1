package com.edu.learn.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class TestRedPackage {

    public static void main(String[] args) {
        Share share = new Share();
       List <Integer> list  =share.getSoluaion(10,100);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("强到的金额:"+new BigDecimal(list.get(i)).divide(new BigDecimal(100)));
        }
    }
}
