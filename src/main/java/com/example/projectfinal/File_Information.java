package com.example.projectfinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class File_Information {
    public static ArrayList<USER> readFile(String fildpath) throws IOException {
// المسار رح يكون مثلاً: C:\Users\User\GulnarStore\USERS.txt
String appFolder = System.getProperty("user.home") + "/GulnarStore/";
new File(appFolder).mkdirs(); // إنشاء المجلد إذا مش موجود
File usersFile = new File(appFolder + "USERS.txt");
        ArrayList<USER> User = new ArrayList<>();// استحدمت كلاس ال user  هان افضل من انه احط Sring[] لانه ممكن في المسقبل احط كمان تاربخ ميلاد او ... ف صعب اني اعدل عليه في ال مصفوفة
        File file = new File("src/main/resources/com/example/ProjectFinal/USERS.txt");// ما حطيت ال path  لانه ال ملف وال مشروه في نفس ال folder

        try {
            Scanner sc= new Scanner (file);
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                //String[] parts = line.trim().split("\\s+");
                String[] parts = line.split(" ");
                if (parts.length>=2){// عشان ااضمن انه في اسم وكلمة مرور
                    String userName = parts[0].trim();
                    String password = parts[1].trim();
                    User.add(new USER(userName,password));

                }
            }

            sc.close();
        }
        catch (FileNotFoundException e){
            System.out.println(" the file does not exist :"+e.getMessage());

        }
        return User;
    }

}
