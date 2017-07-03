package com.github.xiao.sync;
/**
 * Copyright (C) 2017 Feifan, Inc. All Rights Reserved.
 */

/**
 * @auther 肖高许
 * @date 2017/6/22
 * @version 1.0
 */
public class Ticket {

    private int numbers;


    public synchronized   void allocte(){
        try {
            Thread.sleep(100l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(numbers==0){
            System.out.println("售完了");
        }else {
            System.out.println(Thread.currentThread().getName()+":"+numbers--);
        }
    }

    public synchronized static   void inc(){
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("增加库存");

    }


    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }
}
