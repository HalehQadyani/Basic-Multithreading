package sbu.cs;

/*
    In this exercise, you must write a multithreaded program that finds all
    integers in the range [1, n] that are divisible by 3, 5, or 7. Return the
    sum of all unique integers as your answer.
    Note that an integer such as 15 (which is a multiple of 3 and 5) is only
    counted once.

    The Positive integer n > 0 is given to you as input. Create as many threads as
    you need to solve the problem. You can use a Thread Pool for bonus points.

    Example:
    Input: n = 10
    Output: sum = 40
    Explanation: Numbers in the range [1, 10] that are divisible by 3, 5, or 7 are:
    3, 5, 6, 7, 9, 10. The sum of these numbers is 40.

    Use the tests provided in the test folder to ensure your code works correctly.
 */

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FindMultiples
{

    // TODO create the required multithreading class/classes using your preferred method.


    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */
    static class DivisorTask implements Runnable {
        private int n;
        private int divisor;
        private int sum = 0;
        public DivisorTask(int n, int divisor) {
            this.n = n;
            this.divisor = divisor;
        }
        public void run() {
            for (int i = 1; i <= n; i++) {
                if (i % divisor == 0) {
                    sum += i;
                }
            }
        }
        public int Sum() {
            return sum;
        }
    }

    public static int getSum(int n) {
        int nThreads = 3;
        // number of threads to be created
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        // create three tasks for each divisor
        DivisorTask task1 = new DivisorTask(n, 3);
        DivisorTask task2 = new DivisorTask(n, 5);
        DivisorTask task3 = new DivisorTask(n, 7);
        DivisorTask task4 = new DivisorTask(n, 21);
        DivisorTask task5 = new DivisorTask(n, 15);
        DivisorTask task6 = new DivisorTask(n, 35);
        DivisorTask task7 = new DivisorTask(n, 105);
        // submit the tasks to the executor
        executor.submit(task1);
        executor.submit(task2);
        executor.submit(task3);
        executor.submit(task4);
        executor.submit(task5);
        executor.submit(task6);
        executor.submit(task7);
        // shutdown the executor after all tasks are completed
        executor.shutdown();
        // wait for all tasks to complete
        while(!executor.isTerminated()) {
            Thread.yield();
        }
        int Total = task1.Sum() + task2.Sum() + task3.Sum()
                - task4.Sum() - task5.Sum() - task6.Sum() - task7.Sum();
        return Total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        getSum(n);
    }
}
