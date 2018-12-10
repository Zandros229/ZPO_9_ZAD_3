package com.company;

import java.util.Random;
import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public class Osoba implements Callable<Osoba> {
    int time;
    double prob;
    boolean flag=false;
    public Osoba(int time,double prob) {
        this.time=time;
        this.prob=prob;
    }


    @Override
    public Osoba call() throws Exception {
        System.out.println(time+" "+prob+" ");
        sleep((long) time * 1000);

        if(prob<=0.05)
            this.flag=true;
        return this;
    }
}
