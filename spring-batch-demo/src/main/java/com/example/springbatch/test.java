package com.example.springbatch;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class test {
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now).toString());
        File file=new File("D:/batch/internship_2022_bachkhoa_java_1/spring-batch-demo/src/main/resources/FacebookCSV_3000.csv");
        File fileRename=new File("D:/batch/internship_2022_bachkhoa_java_1/spring-batch-demo/src/main/resources/odlfile/Facebook_"+dtf.format(now).toString()+".csv" );
        // Path copied = ;
        // File.copy(copied, file, StandardCopyOption.REPLACE_EXISTING);
        file.renameTo(fileRename);
    if (file.exists()){
        System.out.println("true");
    }
    else{
        System.out.println("false");
    }
    }
    
}
