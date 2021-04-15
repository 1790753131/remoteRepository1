package com.edu.learn.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 *  问题：银行用户账户有一个金额属性，
 *  请实现存钱，取钱，转账三个功能，并
 *  演示并发场景下，
 *  银行账户A和账户B之间互相转账1元的情景。
 */

public class TestControlller {
    public static void main(String[] args){
        ReentrantLock lock = new  ReentrantLock();
        //转账金额
        int transMoney = 1;
       // A账户向B账户转入
        new Thread(()->{
            lock.lock();
            try{
                //B账户转入1元
                BankAccountB  bankAccountB = new  BankAccountB();
                bankAccountB.transAccount(transMoney,0);
                //A账户转出1元
                BankAccountA  bankAccounta = new  BankAccountA();
                bankAccounta.transAccount(transMoney,1);
            }finally {
                lock.unlock();
            }
        },"银行账户A").start();
        // B账户向A账户转入1元
        new Thread(()->{
            lock.lock();
            try {
                //A账户转入1元
                BankAccountA  bankAccountA = new  BankAccountA();
                bankAccountA.transAccount(transMoney,0);

                //B账户转入1元
                BankAccountB  bankAccountB = new  BankAccountB();
                bankAccountB.transAccount(transMoney,1);
            }finally {
                lock.unlock();
            }
        },"银行账户B").start();
    }




   static class BankAccountA{
       private static int accountA=1;
        //存钱
        public  String setAccount(int money){
            try{
                int cruAccountA = accountA + money;
                System.out.println("A账户原来金额"+accountA+"存入后金额"+cruAccountA);
                accountA=cruAccountA;
                return "存入成功";
            }catch (Exception e ){
                e.printStackTrace();
                return "存入失败";
            }
        }
        //取钱
        public  String getAccount(int money){
            try{
                if(accountA<money){
                    return  "账户余额不足";
                }
                accountA = accountA-money;
                System.out.println("A账户取出后金额"+accountA);
                return  String.valueOf(accountA);
            }catch (Exception e){
                e.printStackTrace();
                return "取钱失败";
            }
        }
        //转账
        public   String transAccount(int money, int flag){
            try {
                if (flag == 1){//转出
                    if(accountA<money){
                        return  "账户余额不足";
                    }
                }
                int  beforeAccountA = accountA;
                if(flag == 0 ){//转入
                    accountA = accountA+money;
                    System.out.println(Thread.currentThread().getName()+"======A账户转入前金额："+beforeAccountA+"元"+"A账户转入后金额：" + accountA+"元");
                    return  "账户转入成功";
                }else{
                    accountA = accountA-money;
                    System.out.println(Thread.currentThread().getName()+"======A账户转出前金额："+beforeAccountA+"元"+"A账户转出后金额：" + accountA+"元");
                    return  "账户转出成功";
                }
            }catch (Exception e){
                e.printStackTrace();
                if(flag == 1 ){
                    return  "账户转出失败";
                }else{
                    return  "账户转入失败";
                }
            }
        }
    }

    static class BankAccountB {
        private static int accountB=1;
        //存钱
        public  String setAccount(int money) {
            try {
                int cruAccountB = accountB + money;
                System.out.println("B账户原来金额" + accountB + "存入后金额" + cruAccountB);
                accountB = cruAccountB;
                return "存入成功";
            } catch (Exception e) {
                e.printStackTrace();
                return "存入失败";
            }
        }

        //取钱
        public  String getAccount(int money) {
            try {
                if (accountB < money) {
                    return "账户余额不足";
                }
                accountB = accountB - money;
                System.out.println("B账户取出后金额" + accountB);
                return String.valueOf(accountB);
            } catch (Exception e) {
                e.printStackTrace();
                return "取钱失败";
            }
        }



        //转账
        public  String transAccount(int money, int flag) {
            try {
                if (flag == 1) {//转出
                    if (accountB < money) {
                        return "账户余额不足";
                    }
                }
                int beforeAccountB = accountB;
                if (flag == 0) {//转入
                    accountB = accountB + money;
                    System.out.println(Thread.currentThread().getName()+"======B账户转入前金额：" +beforeAccountB+"元"+"B账户转入后金额：" + accountB+"元");
                    return "账户转入成功";
                } else {
                    accountB = accountB - money;
                    System.out.println(Thread.currentThread().getName()+"======B账户转出前金额：" +beforeAccountB+"元"+"B账户转出后金额：" + accountB+"元");
                    return "账户转出成功";
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (flag == 1) {
                    return "账户转出失败";
                } else {
                    return "账户转入失败";
                }
            }
        }
    }
 }

