package spring.spring.Parrel;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelStream {
    public static void main(String[] args) {
        List<Integer> numList = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

        // Thread 크기의 제어 방식 3가지
        
        // 1. System property 설정
        // System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","6");
        
        // 2. ForkJoinPool 사용
        //List<Integer> numList = Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12});
        //
        //ForkJoinPool forkjoinPool = new ForkJoinPool(5);
        //forkjoinPool.submit(() -> {
        //    numList.parallelStream().forEach(index -> {
        //        System.out.println("Thread : " + Thread.currentThread().getName()
        //                + ", index + " + new Date());
        //        try{
        //            Thread.sleep(5000);
        //        } catch (InterruptedException e){
        //        }
        //    });
        //}).get();
        
        //3. 자바 8 이전 방식
        ExecutorService executor = Executors.newFixedThreadPool(4);
        int i = 1;
        for (Integer integer : numList) {
            final int index = i;
            executor.submit(()->{
                try{
                    Thread.sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ", index = " + index + ", ended at " + new Date());
            });
            i++;
        }
        executor.shutdown();
    }
}
