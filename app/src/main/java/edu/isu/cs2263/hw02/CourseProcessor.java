package edu.isu.cs2263.hw02;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.checkerframework.checker.signature.qual.ArrayWithoutPackage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

//This class stores and processes information

class CourseProcessor {


    static ArrayList courseList=new ArrayList();

    public static void saveCourse(String courseName, String courseNum, String noCredit, String depCode) throws IOException {
       String depCodeFin=getDepCode(depCode,"");
        Course course =new Course(courseName,Integer.parseInt(courseNum),Integer.parseInt(noCredit),depCodeFin);
        courseList.add(course);
        //creates a Json file
        Writer writer = new FileWriter("courses.json");
        Gson gson = new GsonBuilder().create();
        gson.toJson(courseList, writer);

        writer.close(); //close write



    }

    public static Course[] displayCourse() throws IOException {
        //loads a Json file
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("courses.json"));
        Course[] enums = gson.fromJson(reader, Course[].class);

        return  enums;
    }
   // verifies if department selected is equal to department code
   public static String getDepCode(String depCode, String depCodeFin){
       if (depCode=="Computer Science"){
           depCodeFin="CS";
       }else if(depCode=="Mathematics") {
           depCodeFin="MATH";
       }else if(depCode=="Chemistry") {
           depCodeFin="CHEM";
       }else if(depCode=="Physics") {
           depCodeFin="PHYS";
       }else if(depCode=="Biology"){
           depCodeFin="BIOL";
       }else{
           depCodeFin="EE";
       }

       return depCodeFin;

   }
}