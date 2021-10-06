package HomeWork_12;
/*Необходимо написать два метода, которые делают следующее:
        1) Создают одномерный длинный массив, например:
            static final int SIZE = 10 000 000;
            static final int HALF = size / 2;
            float[] arr = new float[size].
        2) Заполняют этот массив единицами.
        3) Засекают время выполнения: long a = System.currentTimeMillis().
        4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
        arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        5) Проверяется время окончания метода System.currentTimeMillis().
        6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).
        Отличие первого метода от второго:
        Первый просто бежит по массиву и вычисляет значения.
        Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.*/

import java.util.Scanner;

public class HomeWork_12v3 {
    public static void main(String[] args) {

        Thread_1_Massive();
        TwoThreads_Massive();

    }
    static void Thread_1_Massive() { // запуск в одном потоке
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Введите величину массива для расчета одним потоком ");
        int size = sc1.nextInt();
        long start5 = System.currentTimeMillis();
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long finish5 = System.currentTimeMillis();
        System.out.println(finish5 - start5 + " <-- время на заполнение единицами всего массива при 1 потоке --> " + Thread.currentThread().getName());
        long start6 = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long finish6 = System.currentTimeMillis();
        System.out.println(finish6 - start6 + " <-- время на расчет всего массива при 1 потоке --> " + Thread.currentThread().getName());

    }    // запуск в одном потоке



    public static void TwoThreads_Massive(){
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Введите величину массива для расчета двумя потоками ");
        int size = sc2.nextInt();

        Thread MyThread_1 = new Thread(new Runnable() {
            @Override
            public void run() {
                long start1 = System.currentTimeMillis();
                float[] arr = new float[size];
                for (int i = 0; i < (arr.length)/2; i++) {
                    arr[i] = 1;
                }
                long finish1 = System.currentTimeMillis();
                System.out.println(finish1 - start1 + " Время затраченное на заполнение единицами от 0 до середины --> "+ Thread.currentThread().getName());

                long start2 = System.currentTimeMillis();
                for (int i = 0; i < (arr.length)/2; i++) {
                    arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                long finish2 = System.currentTimeMillis();
                System.out.println(finish2 - start2 + " Время затраченное на расчет первой половины массива --> "+ Thread.currentThread().getName());
            }
        });
        MyThread_1.start();

        Thread MyThread_2 = new  Thread(new Runnable() {  // запуск в двух потоках
            @Override
            public void  run() {
                long start3 = System.currentTimeMillis();
                float[] arr = new float[size];
                for (int i = (arr.length)/2 + 1; i < arr.length; i++) {
                    arr[i] = 1;
                }
                long finish3 = System.currentTimeMillis();
                System.out.println(finish3 - start3 + " Время затраченное на заполнение единицами от середины до границы массива --> "+ Thread.currentThread().getName());

                long start4 = System.currentTimeMillis();
                for (int i = (arr.length)/2 + 1; i < arr.length; i++) {
                    arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                long finish4 = System.currentTimeMillis();
                System.out.println(finish4 - start4 + " Время затраченное на расчет второй половины массива --> " + Thread.currentThread().getName());
            }
        });
        MyThread_2.start();
    }// запуск в двух потоках

}