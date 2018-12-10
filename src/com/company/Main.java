package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws Exception {
        AtomicReference<Integer> amount= new AtomicReference<Integer>(0);
        AtomicReference<Integer> left= new AtomicReference<Integer>(0);
        Random random=new Random();
         ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(100);
        List<Callable<Osoba>> list=new ArrayList<Callable<Osoba>>();
        for(int i=0;i<100;i++)
            list.add(new Osoba(random.nextInt(4)+1,random.nextDouble()));
        List<Future<Osoba>> temp = newFixedThreadPool.invokeAll(list);
        newFixedThreadPool.shutdown();
        while (!(newFixedThreadPool.isShutdown())) {
            System.out.println("XD");
            //sleep(10);
        }
        temp.forEach(o -> {
            try {
                if(o.get().flag==true)
                    amount.set(amount.get()+1);
            } catch(Exception e){
                e.printStackTrace();
            }
        });
        if(amount.get()<5)
            System.out.println("Przpraszamy  filmu nie będzie");
        else{
            newFixedThreadPool=Executors.newFixedThreadPool(amount.get());
            List<Callable<Widz>> list2=new ArrayList<Callable<Widz>>();
            for(int i=0;i<amount.get();i++)
                list2.add(new Widz(random.nextDouble()));
            List<Future<Widz>> temp2 = newFixedThreadPool.invokeAll(list2);

            while (!(newFixedThreadPool.isShutdown())) {
                temp2.forEach(w->{
                    try {
                        if(w.get().flag==true){
                            left.set(left.get()+1);
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                });
                //System.out.println(amount.get()+" "+left.get());
                if(amount.get()-left.get()<5) {

                    System.out.println("Obraźliwy Komunikat");
                    newFixedThreadPool.shutdownNow();
                }
                //sleep(10);
            }
        }


    }

}
