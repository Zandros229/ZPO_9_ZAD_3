package com.company;

import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public class Widz implements Callable<Widz> {
    double prob;
    Boolean flag=true;

    public Widz(double prob) {
        this.prob = prob;
    }



    @Override
    public Widz call() throws Exception {
        System.out.println(prob+" ");
        sleep(2*1000);
        if(prob>0.3)
            this.flag=false;
        return this;
    }
}
