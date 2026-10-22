package com.example.projectfinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class File_Categories {
    public static ArrayList<Categories> readCategories(String filePath) {
        ArrayList<Categories> categories = new ArrayList<>();// استحدمت كلاس ال user  هان افضل من انه احط Sring[] لانه ممكن في المسقبل احط كمان تاربخ ميلاد او ... ف صعب اني اعدل عليه في ال مصفوفة
        File file = new File("src/main/resources/com/example/ProjectFinal/Categories.txt");// ما حطيت ال path  لانه ال ملف وال مشروه في نفس ال folder
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" ");// تقسيم ع مسافات متعددة
                if (parts.length >= 2) {// عشان ااضمن انه في اسم وكلمة مرور
                    String imageName = parts[1].trim();
                    String imagePath = parts[2].trim();
                    String buttonCategoried =imageName.toLowerCase()+"Button";
                    Categories category=(new Categories(imageName,imagePath , buttonCategoried ));
                    categories.add(category);
                }
            }

            sc.close();
        } catch (
                FileNotFoundException e) {
            System.out.println(" the file does not exist :" + e.getMessage());

        }
        return categories;

    }
}
