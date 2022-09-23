package com.kenzie.app.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class AddressFormatUtil {
    private static HashMap<String,String> codeTable = new HashMap<>();
    private static HashMap<String,String> cityCodeTable = new HashMap<>();
    private static ArrayList<ArrayList<String>> listItems;
    static{
        codeTable.put("ST","STREET");
        codeTable.put("street","STREET");
        codeTable.put("St.","STREET");
        codeTable.put("St","STREET");
        codeTable.put("ST.","STREET");
        codeTable.put("Ave.","AVENUE");
        codeTable.put("ave", "AVENUE");
        codeTable.put("Rd.","ROAD");
        codeTable.put("Rd","ROAD");
    }
    //declare properties

    public static void initCodeTable(){
        //enter values into HashMap
        codeTable.put("ST","STREET");
        codeTable.put("street","STREET");
        codeTable.put("St.","STREET");
        codeTable.put("St","STREET");
        codeTable.put("ST.","STREET");
        codeTable.put("Ave.","AVENUE");
        codeTable.put("ave", "AVENUE");
        codeTable.put("Rd.","ROAD");
        codeTable.put("Rd","ROAD");

    }

    public static void initCityCodeTable(){
        cityCodeTable.put("St","SAINT");
        cityCodeTable.put("St.","SAINT");
        cityCodeTable.put("ST","SAINT");
        cityCodeTable.put("")
    }
    //will take 123 Main St.
    //output 123 Main STREET
    public static String replaceAbbreviation(String input){
       String result= "";
        for (String currentKey: codeTable.keySet()) {
            if(input.contains(currentKey)){
                String currentValue = codeTable.get(currentKey);
                result = input.replace(currentKey,codeTable.get(currentKey));
            }
        }
       return result;
    }

    public static String replaceCityAbbreviation(String input){
        String result= "";
        for (String currentKey: codeTable.keySet()) {
            if(input.contains(currentKey)){
                String currentValue = codeTable.get(currentKey);
                result = input.replace(currentKey,codeTable.get(currentKey));
            }
        }
        return result;
    }
    public static String formatAddress(String input){
        return input.toUpperCase();
    }

    public static String formatStreetAddress(String input){
        return formatAddress(replaceAbbreviation(input));
    }
}
