package com.guosen;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        System.out.println("使用第一种构造函数：");
        StringTokenizer st1 = new StringTokenizer("172.18.38.206         90-fb-a6-0d-3b-73     动态", " ");
        while (st1.hasMoreTokens())
            System.out.println(st1.nextToken());

        System.out.println("使用第二种构造函数：");
        StringTokenizer st2 = new StringTokenizer("JAVA : Code : String", " :");
        while (st2.hasMoreTokens())
            System.out.println(st2.nextToken());

        System.out.println("使用第三种构造函数：");
        StringTokenizer st3 = new StringTokenizer("JAVA : Code : String", " :",  true);
        while (st3.hasMoreTokens())
            System.out.println(st3.nextToken());
    }
}
