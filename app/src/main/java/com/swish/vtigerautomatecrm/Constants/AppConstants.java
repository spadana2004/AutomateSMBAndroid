package com.swish.vtigerautomatecrm.Constants;

public class AppConstants {
    final public static String NETWORKTAG="VOLLEY REQUEST";

    //Api Link Local
    final public static String baseUrl="https://dev2.automatesmb.net/nilay/swiss/vtiger710/";
    final public static String webserviceExtention="webservice.php";
    final public static String challange=baseUrl+"webservice.php?operation=getchallenge&username=";
    final public static String login=baseUrl+"/webservice.php";
    final public static String getList=baseUrl+"webservice.php?operation=listtypes&sessionName=";
    final public static String retrieve="?operation=retrieve&sessionName=";
    final public static String describeOperationsPart1=baseUrl+"webservice.php?operation=describe&sessionName=";
    final public static String describeOperationsPart2="&elementType=";
    //Shared Prefs
    final public static String AppPrefs="AutomateSMB";

}

