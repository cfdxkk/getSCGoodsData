package MISC;

import java.util.Scanner;

public class lever {
    public static int lever(){
        int lever = 0;
        justALine.reallyALine();
        System.out.println("输入0从头开始解包，输入1在已完成的解包文件中搜索");
        System.out.print(">");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()){
            int numcache = sc.nextInt();
            if (numcache == 0 || numcache ==1){
                lever = numcache;
            } else {
                System.out.println("请输入0或1，禁止输入其他数字");
                System.exit(1);
            }
        } else {
            System.out.println("请输入一个数字");
            System.exit(1);
        }

        return lever;
    }
    public static int lever2(){
        int lever = 0;
        justALine.reallyALine();
        System.out.println("输入0检索所有载具信息，输入1按载具名字搜索，输入2检索所有支持的载具英文名");
        System.out.print(">");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()){
            int numcache = sc.nextInt();
            if (numcache == 0 || numcache == 1 || numcache == 2){
                lever = numcache;
            } else {
                System.out.println("请输入0、1或2，禁止输入其他数字");
                System.exit(1);
            }
        } else {
            System.out.println("请输入一个数字");
            System.exit(1);
        }

        return lever;
    }
    public static int lever3(){
        int lever = 0;
        justALine.reallyALine();
        System.out.println("输入0退出，输入1重新运行本程序");
        System.out.print(">");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()){
            int numcache = sc.nextInt();
            if (numcache == 0 || numcache ==1){
                lever = numcache;
            } else {
                System.out.println("请输入0或1，禁止输入其他数字");
                System.exit(1);
            }
        } else {
            System.out.println("请输入一个数字");
            System.exit(1);
        }

        return lever;
    }
}
