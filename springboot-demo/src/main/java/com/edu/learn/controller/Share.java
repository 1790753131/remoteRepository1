package com.edu.learn.controller;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;


public  class  Share {

    public List<Integer>  getSoluaion(int peopleNum,int totalNum){
        List<Integer> list= new ArrayList<>();
        Integer restAccount = totalNum;
        Integer restPeopleNum= peopleNum;
        Random random  = new Random();
        for (int i = 0; i <restPeopleNum-1 ; i++) {
            int account = random.nextInt((restAccount/restPeopleNum)*2-1)+1;
            restAccount-= totalNum;
            restPeopleNum--;
//            list.add(totalNum);
        }
        list.add(restAccount);
        return  list;
    }

 }



//    public static LinkedList  getNum() throws InterruptedException {
//        LinkedList <Integer> list = new LinkedList();
//        for (int i = 0; i <100 ; i++) {
//            list.push(i);
//        }
//        return  list;
//    }
//
//    private  int flag = 1 ;
//    public  synchronized  void PrintA() throws InterruptedException {
//        while (flag != 1 ){
//            this.wait();
//        }
//        LinkedList  list = getNum();
//        Integer a = (Integer) list.peek();
//        System.out.println("线程"+Thread.currentThread().getName()+"---->"+a);
//        flag = 2;
//        this.notifyAll();
//    }
//
//    public  synchronized  void PrintB() throws InterruptedException {
//        while (flag != 2 ){
//            this.wait();
//        }
//        LinkedList  list = getNum();
//        Integer a = (Integer) list.peek();
//        System.out.println("线程"+Thread.currentThread().getName()+"---->"+a);
//        flag = 3;
//        this.notifyAll();
//    }
//
//    public  synchronized  void PrintC() throws InterruptedException {
//        while (flag != 3 ){
//            this.wait();
//        }
//        LinkedList  list = getNum();
//        Integer a = (Integer) list.peek();
//        System.out.println("线程"+Thread.currentThread().getName()+"---->"+a);
//        flag = 1;
//        this.notifyAll();
//    }



