package core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import MISC.justALine;
import org.dom4j.DocumentException;
import org.dom4j.tree.AbstractAttribute;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import MISC.lever;



public class dataCollation {
    public static void startHere() throws IOException {
        getPrice.startSearch();

    }

}


















class getPrice {

    static int m=1;
    static String searchBasicPrice(File a,String x) throws IOException{   //在文件a中的每行中查找x
        Scanner scan1 = new Scanner(a,"utf-8");
        int k1 = 0;
        while(true){
            if(scan1.hasNext()==false)
                {
                    break;
                } else {
                    String s1 = scan1.nextLine();
                    k1++;
                    if( s1.contains(x) || s1.toLowerCase().contains(x.toLowerCase()) ){
                        int indexof = s1.indexOf("BasePrice=\"") ;
                        if(indexof == -1){
                            // do nothing
                        } else {
                            String basicPrice = strCut.getBasicPrice(s1);
                            double check0 = Double.valueOf(basicPrice);
                            if(check0 == 0) {
                                //do nothing
                            } else {
                                if (check0 == 7000.0 || check0 == 6600 || check0 == 4000){    //太傻逼了
                                    scan1.nextLine();
                                    continue;
                                }
                                return basicPrice;
                            }
                        }
                }

            }

        }
        System.out.println("未找到您输入的载具或载具名有误，请检查程序版本是否与您的游戏版本匹配或查看支持的载具名称并再试一次");
        return "读取失败，没找到基本价格";
    }

    static String searchNoteID(File a,String x) throws IOException{   //在文件a中的每行中查找x
        Scanner scan1 = new Scanner(a,"utf-8");
        int k1 = 0;
        while(true){
            if(scan1.hasNext()==false)
            {
                break;
            } else {
                String s1 = scan1.nextLine();
                k1++;
                if( s1.contains(x) || s1.toLowerCase().contains(x.toLowerCase()) ){
                    int indexof = s1.indexOf("BasePrice=\"") ;
                    if(indexof == -1){
                        // do nothing
                    } else {
                        String basicPrice = strCut.getBasicPrice(s1);
                        double check0 = Double.valueOf(basicPrice);

                        if(check0 == 0) {
                            //do nothing
                        } else {
                            if (check0 == 7000.0 || check0 == 6600 || check0 == 4000){
                                scan1.nextLine();
                                continue;
                            }
                            String nodeID = strCut.getNodeID(s1);
                            return nodeID;
                        }
                    }
                }

            }

        }
        System.out.println("未找到您输入的载具或载具名有误，请检查程序版本是否与您的游戏版本匹配或查看支持的载具名称并再试一次");
        return "读取失败，没找到物品ID";
    }




    static String searchFullName(File a,String x) throws IOException{   //在文件a中的每行中查找x
        Scanner scan1 = new Scanner(a,"utf-8");
        int k1 = 0;

        while(true){
            if(scan1.hasNext()==false)
            {
                break;
            } else {
                String s1 = scan1.nextLine();
                k1++;
                if( s1.contains(x) || s1.toLowerCase().contains(x.toLowerCase()) ){
                    int indexof = s1.indexOf("BasePrice=\"") ;
                    if(indexof == -1){
                        // do nothing
                    } else {
                        String basicPrice = strCut.getBasicPrice(s1);
                        Double check0 = Double.valueOf(basicPrice);
                        if(check0 == 0) {
                            //do nothing
                        } else {
                            if (check0 == 7000.0 || check0 == 6600 || check0 == 4000){
                                scan1.nextLine();
                                continue;
                            }

                            String fullName = strCut.getFullName(s1);
                            return fullName;
                        }
                    }
                }

            }

        }
        System.out.println("未找到您输入的载具或载具名有误，请检查程序版本是否与您的游戏版本匹配或查看支持的载具名称并再试一次");
        return "读取失败，没找到全名";
    }

    public static String checkBuyment(File a,String x) throws IOException{


        Scanner scan1 = new Scanner(a,"utf-8");
        int lines = 0;
        String str = "";

        while(scan1.hasNext()){

            lines++;

            String s1 = scan1.nextLine();

            if( s1.contains(x) || s1.toLowerCase().contains(x.toLowerCase()) ){
                String shopInventoryNodeID = strCut.getShopInventoryNodeID(s1);
                String priceFloat = strCut.getBasePriceOffsetPercentage(s1);

                int stopLine = lines+2;
                while (true){
                    if( scan1.hasNext()){
                        s1 = scan1.nextLine();
                        lines++;
                        if (lines == stopLine){
                            String rentOrBuy = strCut.getRentOrBuy(s1);
                            String shopID = getPrice.getShopID(x,shopInventoryNodeID,rentOrBuy);
                            String shopName = getPrice.getShopName(shopID);
                            if(rentOrBuy.equals("<![CDATA[Buy]]>")){

                                str = str + "---出售---"+shopInventoryNodeID+"---"+priceFloat+"---"+shopID+"---"+shopName+"---\n";
                            } else {
                                str = str + "---租赁---"+shopInventoryNodeID+"---"+priceFloat+"---"+shopID+"---"+shopName+"---\n";
                            }
                            break;
                        }
                    }


                }



            }

        }
        return str;




    }



    public static String getShopID(String goodID, String shopInventoryNodeID, String buyOrRent){
        try {



            SAXReader sax = new SAXReader();
            // 获得dom4j的文档对象
            Document   root = sax.read(new File("Data\\Libs\\Subsumption\\Shops\\ShopLayouts.xml"));
            // 得到database节点



            String str = "//ShopInventoryNode[@ID='"+shopInventoryNodeID+"']";



            Element ShopInventoryNode = (Element) root.selectSingleNode(str);
            Element SIN_F = ShopInventoryNode.getParent().getParent();
            String shopID = SIN_F.attributeValue("ID");
            return shopID;






            } catch (Exception e){
            System.out.println("未找到您输入的载具或载具名有误，请检查程序版本是否与您的游戏版本匹配或查看支持的载具名称并再试一次");
            e.printStackTrace();
        }
        return "error";
    }




    public static String getShopName(String shopID){
        // 获得dom4j的文档对象
        try {
            SAXReader sax = new SAXReader();
            // 获得dom4j的文档对象
            Document   root = sax.read(new File("Data\\Libs\\Subsumption\\Shops\\ShopLayouts.xml"));
            // 得到database节点



            String str = "//ShopLayoutNode[@ID='"+shopID+"']";



            Element ShopLayoutNode = (Element) root.selectSingleNode(str);
            String shopName = ShopLayoutNode.attributeValue("Name");
            return shopName;


        } catch (DocumentException e) {
            System.out.println("未找到您输入的载具或载具名有误，请检查程序版本是否与您的游戏版本匹配或查看支持的载具名称并再试一次");
            e.printStackTrace();
        }
        return "获取商店名失败";








    }



//
//
//    static Map<String,String> searchEachShopPriceFloat(File a,String x) throws IOException{   //在文件a中的每行中查找x
//        Scanner scan1 = new Scanner(a,"utf-8");
//        Map<String,String> map1 = new HashMap<String,String>();
//        int count = 0;
//        while(true){
//            if(scan1.hasNext()==false)
//            {
//                break;
//            } else {
//                String s1 = scan1.nextLine();
//
//                if( s1.contains(x) || s1.toLowerCase().contains(x.toLowerCase()) ){
//                    String nodeID = strCut.getInventoryID(s1);
//                    String priceFloat = strCut.getBasePriceOffsetPercentage(s1);
//
//                        map1.put(nodeID+"---"+count,priceFloat);
//                        count++;
//
//
//                }
//
//            }
//
//        }
//        return map1;
//    }
//
//
//
//    static Map<String,String> searchShopName(File a,String x) throws IOException{   //在文件a中的每行中查找x
//        Scanner scan1 = new Scanner(a,"utf-8");
//        int k1 = 0;
//        Map<String,String> map1 = new HashMap<String,String>();
//        while(true){
//            if(scan1.hasNext()==false)
//            {
//                break;
//            } else {
//                String s1 = scan1.nextLine();
//                k1++;
//                if( s1.contains(x) || s1.toLowerCase().contains(x.toLowerCase()) ){
//
//
//
////                                String ss1 = m + ".文件:" + a.getPath() + " 第" + k1 + "行 \n  截取到的价格为" + basicPrice;
////                                System.out.println(ss1);
//                    String nodeID = strCut.getInventoryID(s1);
//                    String shopID = strCut.getShopNodeID(s1);
//                    map1.put(nodeID,shopID);
//
//
//                }
//
//            }
//
//        }
//        return map1;
//    }







//    //暂时用不上的遍历文件夹方法
//    static void f(File a,String s)throws IOException{//在a下所有文件中查找含有s的行
//
//        File[] ff = a.listFiles();
//        if(ff==null){
//            System.out.println("找不到文件");
//            System.exit(0);
//        }
//        for(File it : ff){
//            if(it.isFile()){//若a是文件，直接查找
//                searchBasicPrice(it,s);
//            }
//            if(it.isDirectory()){//若a是目录，则对其目录下的目录或文件继续查找
//                f(it,s);
//            }
//        }
//    }



    public static void startSearch()throws IOException {

        String RetailProductPrices = getString.getThisFileRoute()+"\\Data\\Libs\\Subsumption\\Shops\\RetailProductPrices.xml";
        String ShopLayouts = getString.getThisFileRoute()+"\\Data\\Libs\\Subsumption\\Shops\\ShopLayouts.xml";
        String[] keywordSTR = {"ptv","GRIN_ROC","buccaneer","dragonfly","cutlass_black","cutlass_red","cutlass_blue","caterpillar","herald","cyclone","cyclone_aa","cyclone_rc","cyclone_rn","cyclone_tr","mpuv","mpuv_Transport","mole","blade","prowler","prospector","razor","razor_ex","razor_lx","freelancer_dur","freelancer_MAX","freelancer_mis","freelancer","reliant","reliant_mako","reliant_sen","reliant_tana","starfarer","starfarer_gemini","c8x","Hornet_F7C","f7cs","f7cr","f7cm","hurricane","ANVL_Arrow","gladiator","hawk","terrapin","ANVL_Valkyrie","carrack","ballista","aurora_ln","aurora_cl","aurora_es","aurora_mr","aurora_lx","mantis","andromeda","aquila","phoenix","ursa_rover","avenger_titan","avenger_stalker","avenger_warlock","vanguard_sentinel","vanguard_harbinger","vanguard","vanguard_hoplite","hammerhead","gladius","sabre","eclipse","retaliator","reclaimer","ORIG_m50","ORIG_85X","100i","125a","135c","300i","315p","325a","350r","600i","600i_tour","890jump","mustang_alpha","mustang_beta","mustang_gamma","mustang_delta","nox","XIAN_Scout","p52_merlin","defender"};

        int Lever2 = lever.lever2();
        if (Lever2 == 2){
            for (int i=0;i<keywordSTR.length;i++){
                System.out.println(i+1+". "+keywordSTR[i]);
            }
        }
        if(Lever2 == 1 || Lever2 == 2){
            String keyWord = getString.getKeyWord();
            System.out.println("正在搜索含有 "+keyWord+" 关键词的文件.....");
            justALine.reallyALine();

            String GID = searchNoteID(new File(RetailProductPrices),keyWord);   //商品ID
            String basicPrice = searchBasicPrice(new File(RetailProductPrices),keyWord);
            String fullName = searchFullName(new File(RetailProductPrices),keyWord);
            String shipInfo = checkBuyment(new File(ShopLayouts),GID);

            System.out.println(resultProcessing.strCut(basicPrice,fullName,shipInfo));   //最终输出
            justALine.reallyALine();
            int Lever3 =lever.lever3();
            if (Lever3 == 0){
                System.exit(1);
            } else if (Lever3 == 1){
                startSearch();
            }
        } else if( Lever2 == 0 ){

            for (int i=0;i<keywordSTR.length;i++){
                System.out.println("正在搜索所有载具信息.....");
                String keyWord = keywordSTR[i];
                System.out.println("现在正在搜索的载具是："+keyWord);
                justALine.reallyALine();


                String GID = searchNoteID(new File(RetailProductPrices),keyWord);   //商品ID
                String basicPrice = searchBasicPrice(new File(RetailProductPrices),keyWord);
                String fullName = searchFullName(new File(RetailProductPrices),keyWord);
                String shipInfo = checkBuyment(new File(ShopLayouts),GID);

                System.out.println(resultProcessing.strCut(basicPrice,fullName,shipInfo));   //最终输出
                justALine.reallyALine();
            }


        }






//        Map<String,String> map1 = searchFullName(new File(RetailProductPrices),keyWord);
//
//
//        Iterator<Map.Entry<String, String>> entries = map1.entrySet().iterator();
//        while (entries.hasNext()) {
//            Map.Entry<String, String> entry = entries.next();
////            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//
//
//            System.out.println("格式：{载具ID=价格浮动系数}"+searchEachShopPriceFloat(new File(ShopLayouts),entry.getKey()));
//            System.out.println("格式：{载具ID=商店ID}"+searchShopName(new File(ShopLayouts),entry.getKey()));
//        }


    }

}

















class getString{

    static String getKeyWord(){
        System.out.println("输入搜索的关键词（载具英文名称，如果不知道名称请重启程序后在上一步输入2）");
        System.out.print(">");
        Scanner sc = new Scanner(System.in);
        String keyWord = sc.next();
        return keyWord;
    }

    static String getThisFileRoute(){
        return System.getProperty("user.dir");
    }
}







class strCut{

    public static String getBasicPrice(String s1){
        int indexof = s1.indexOf("BasePrice=\"") ;

            String strCut1 = s1.substring(indexof + 11);
            String strCut2 = strCut1.substring(0,1);
            String shipBasicPrice = strCut1.substring(0,strCut1.indexOf("\""));


            if(strCut2 == "\""){
                // do nothing
            }else {
                return shipBasicPrice;
            }

        return "未找到该关键词的基础价格";
    }

    public static String getFullName(String s1){
        int indexof = s1.indexOf("Name=\"") ;

        String strCut1 = s1.substring(indexof + 6);
        String strCut2 = strCut1.substring(0,1);
        String shipFullName = strCut1.substring(0,strCut1.indexOf("\""));


        if(strCut2 == "\""){
            // do nothing
        }else {
            return shipFullName;
        }

        return "未找到该关键词的全名";
    }

    public static String getNodeID(String s1){
        int indexof = s1.indexOf("<Node ID=\"") ;

        String strCut1 = s1.substring(indexof + 10);
        String strCut2 = strCut1.substring(0,1);
        String shipNodeID = strCut1.substring(0,strCut1.indexOf("\""));


        if(strCut2 == "\""){
            // do nothing
        }else {
            return shipNodeID;
        }

        return "未找到该关键词的全名";
    }

    public static String getInventoryID(String s1){
        int indexof = s1.indexOf("InventoryID=\"") ;

        String strCut1 = s1.substring(indexof + 13);
        String strCut2 = strCut1.substring(0,1);
        String shipNodeID = strCut1.substring(0,strCut1.indexOf("\""));


        if(strCut2 == "\""){
            // do nothing
        }else {
            return shipNodeID;
        }

        return "未找到该关键词的全名";
    }


    public static String getBasePriceOffsetPercentage(String s1){
        int indexof = s1.indexOf("BasePriceOffsetPercentage=\"") ;

        String strCut1 = s1.substring(indexof + 27);
        String strCut2 = strCut1.substring(0,1);
        String shipNodeID = strCut1.substring(0,strCut1.indexOf("\""));


        if(strCut2 == "\""){
            // do nothing
        }else {
            return shipNodeID;
        }

        return "未找到该关键词的全名";
    }


    public static String getShopInventoryNodeID(String s1){
        int indexof = s1.indexOf("Node ID=\"") ;

        String strCut1 = s1.substring(indexof + 9);
        String strCut2 = strCut1.substring(0,1);
        String shipNodeID = strCut1.substring(0,strCut1.indexOf("\""));


        if(strCut2 == "\""){
            // do nothing
        }else {
            return shipNodeID;
        }

        return "未找到该关键词的全名";
    }


    public static String getRentOrBuy(String s1){

        int indexof = s1.indexOf("<TransactionType>") ;
        String strCut1 = s1.substring(indexof + 17);
        String strCut2 = strCut1.substring(0,1);
        String rentOrBuy = strCut1.substring(0,strCut1.indexOf("</TransactionType>"));

        if(strCut2 == "<"){
            // do nothing
        }else {

            return rentOrBuy;
        }

        return "未找到该关键词的全名";

    }


}

class resultProcessing{
    public static String strCut(String basicPrice,String fullName,String shipInfo){
        String outStr = "";
        outStr = outStr+"载具全名是："+fullName+"  基础售价为："+basicPrice+"aUEC";

        String[] temp1;
        String delimeter1 = "\n";
        temp1 = shipInfo.split(delimeter1);
        for (int i =0;i<temp1.length;i++){
            String[] temp2;
            String delimeter2 = "---";
            temp2 = temp1[i].split(delimeter2);
            for (int j = 0; j<temp2.length;j++){
//                System.out.println(temp2[j]);
                String locol = temp2[5];
                double dbasicPrice = Double.valueOf(basicPrice);
                if(temp2[j].equals("出售")){

                    double priceFloat = Double.valueOf(temp2[j+2]);

                    long realPrice = Math.round(dbasicPrice + (dbasicPrice*priceFloat)/100);
//                    System.out.println("在 "+locol+" 商店的实际价格是："+realPrice+"aUEC");
                    outStr = outStr + "\n在 "+locol+" 商店的实际售价为："+realPrice+"aUEC";
                    continue;
                } else if (temp2[j].equals("租赁")){
                    if (locol.equals("AC_Inventory")){
//                        System.out.println("在 AC模式 的一日租金为："+(dbasicPrice*3)/100+"REC");
                        outStr = outStr + "\n在 AC模式 24h的租金为："+(dbasicPrice*3)/100+"REC";
                    } else if(
                          locol.equals("RegalLuxury_NewBabbage")||locol.equals("VantageRentals_Lorville")||locol.equals("TravelerRentals_Area18")||locol.equals("CargoOffice_Rentals")||locol.equals("Refinery_Rentals")
                          )
                    {
//                        System.out.println("在 "+locol+" 商店的一日租金参考值1为："+(dbasicPrice*3)/100+"aUEC，参考值2为："+(dbasicPrice*2)/100+"aUEC");
                        outStr = outStr + "\n在 "+locol+" 商店的24h租金参考值1为："+(dbasicPrice*3)/100+"aUEC，参考值2为："+(dbasicPrice*2)/100+"aUEC";
                    }


                }
            }
        }


        return outStr;

    }
}















