package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import MISC.justALine;
import org.omg.CORBA.SystemException;

public class getFileRouteAndRunUnp4k {
    public static void Starthere(){
        String gameFileRoute = getRoute.getGameDataFileRoute();
        String unp4kExeRoute = getRoute.getUnp4kExeRoute();
        String fullunp4kExeRoute = unp4kExeRoute+"\\unp4k.exe";
        runUnp4k.runUnp4k(gameFileRoute,fullunp4kExeRoute);

        String unforgeExeRoute = unp4kExeRoute+"\\unforge.exe";
        unXML.unCryXML(unforgeExeRoute);


    }

}


class runUnp4k{
    public static void runUnp4k(String gameDataFileRoute, String unp4kExeRoute){
        System.out.println("Data的路径是"+gameDataFileRoute);
        System.out.println("unp4k的路径是"+unp4kExeRoute);
        justALine.reallyALine();
        String cmdCode = unp4kExeRoute+" "+gameDataFileRoute+" *.xml";
        System.out.println(cmdCode);
        try {
            String s;
            Process process = Runtime.getRuntime().exec(cmdCode);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while((s=bufferedReader.readLine()) != null)
            System.out.println(s);
            process.waitFor();
            checkXmlFileRoute.checkXmlFileRoute();
        } catch (Exception e){
            e.printStackTrace();
        }


    }
}






class getRoute{
    public static String getGameDataFileRoute(){

        System.out.print("请输入星际公民Data.p4k文件的路径;");
        System.out.println("例：C:\\Games\\Star_Citizen\\StarCitizen\\PTU\\Data.p4k");
        System.out.print("Please enter StarCitizen game file ”Data.p4k“'s route, ");
        System.out.println("eg：C:\\Games\\Star_Citizen\\StarCitizen\\PTU\\Data.p4k");
//        System.out.println("如果你只想要xml文件，请输入：”C:\\Games\\Star_Citizen\\StarCitizen\\PTU\\Data.p4k *.xml“, 此处路径详细使用方法请参考unp4k");
//        System.out.println("If you onln need xml file, please enter：”C:\\Games\\Star_Citizen\\StarCitizen\\PTU\\Data.p4k *.xml“, more info please see unp4k");
        justALine.reallyALine();
        System.out.print(">");
        Scanner sc1 = new Scanner(System.in);
        String gameDateFileRoute  = sc1.next();
        return gameDateFileRoute;
    }
    public static String getUnp4kExeRoute(){
        justALine.reallyALine();
        System.out.println("请输入unp4k的unp4k.exe文件所在的目录;");
        System.out.println("如果你的unp4k文件位置是：C:\\Games\\Star_Citizen\\StarCitizen\\unPackage\\setup\\unp4k-suite-v3.3.69-master\\unp4k.exe");
        System.out.println("那么你就填：C:\\Games\\Star_Citizen\\StarCitizen\\unPackage\\setup\\unp4k-suite-v3.3.69-master");

        System.out.print("Please enter unp4k file ”unp4k.exe“'s route, ");
        System.out.println("If your unp4k exe is in：C:\\Games\\Star_Citizen\\StarCitizen\\unPackage\\setup\\unp4k-suite-v3.3.69-master\\unp4k.exe");
        System.out.println("You must enter：C:\\Games\\Star_Citizen\\StarCitizen\\unPackage\\setup\\unp4k-suite-v3.3.69-master");
        System.out.println();
        System.out.println("没有unp4k? 在这里下载：https://github.com/dolkensp/unp4k/tree/master");
        System.out.println("Don't have unp4k? Download here：https://github.com/dolkensp/unp4k/tree/master");
        justALine.reallyALine();
        System.out.print(">");
        Scanner sc2 = new Scanner(System.in);
        String unp4kExeRoute  = sc2.next();
        return unp4kExeRoute;
    }
}


class checkXmlFileRoute{
    public static void checkXmlFileRoute(){
        //如果路径下有xxxx，则xxxxx，如果没有，报错
        String url1 = "Data\\Libs\\Subsumption\\Shops\\RetailProductPrices.xml" ;
        String url2 = "Data\\Libs\\Subsumption\\Shops\\ShopLayouts.xml" ;
        String url3 = "Data\\Libs\\Subsumption\\Shops\\Templates\\ShipRentalPeriods\\PU-OneDayRare.xml" ;
        String url4 = "Data\\Libs\\Subsumption\\Platforms\\PU\\System\\Stanton\\stantonsystem\\Layer0.xml";
        File file1 = new File(url1);
        File file2 = new File(url2);
        File file3 = new File(url3);
        File file4 = new File(url4);

        if ( file1.exists() && file2.exists() && file3.exists() && file4.exists() ) {
            System.out.println("xml文件已找到，正在分析数据...");

        } else {
            System.out.println("无法找到拆包文件，程序异常，请联络QQ：2567240058");
            System.exit(1);
        }
    }
}


class unXML{
    public static void unCryXML(String unforgeExeRoute){

        try {

            String cmdCode1 = unforgeExeRoute+" Data\\Libs\\Subsumption\\Shops\\RetailProductPrices.xml" ;
            String cmdCode2 = unforgeExeRoute+" Data\\Libs\\Subsumption\\Shops\\ShopLayouts.xml" ;
            String s;

            Process process1 = Runtime.getRuntime().exec(cmdCode1);
            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(process1.getInputStream()));
            while((s=bufferedReader1.readLine()) != null)
                System.out.println(s);
            process1.waitFor();

            Process process2 = Runtime.getRuntime().exec(cmdCode2);
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(process2.getInputStream()));
            while((s=bufferedReader2.readLine()) != null)
                System.out.println(s);
            process2.waitFor();



            System.out.println("xml文件已解码，正在筛选有效数据...");

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
