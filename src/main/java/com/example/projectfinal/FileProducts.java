package com.example.projectfinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileProducts {
    public static ArrayList<Products> readProducts(String fildpath) {
        ArrayList<Products> products = new ArrayList<>();// استحدمت كلاس ال user  هان افضل من انه احط Sring[] لانه ممكن في المسقبل احط كمان تاربخ ميلاد او ... ف صعب اني اعدل عليه في ال مصفوفة
        File file = new File("src/main/resources/com/example/ProjectFinal/Products.txt");// ما حطيت ال path  لانه ال ملف وال مشروه في نفس ال folder
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
//                System.out.println(line);
                String[] parts = line.split(" ");
                if (parts.length >= 2) {// عشان ااضمن انه في اسم وكلمة مرور
                    String category = parts[0];
                    String productName = parts[1];
                    String imageName= parts[2];
                    double price= Double.parseDouble(parts[3]);
                    String madeIn= parts[4];
                    String imageFileName = parts[parts.length-3];
                    String path= parts[5];
//                    String path = "/com/example/projectfinal/"+category+"Images/"+imageName;
//                    String path = "src/main/resources/com/example/projectfinal/"+category+"Images/"+imageName;
                    System.out.println(path);

                    Products product=(new Products(category ,productName, imageName ,price,madeIn));
                    product.setImagesPathes(path);
products.add(product);
                }

            }


            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(" the file does not exist :" + e.getMessage());

        }
        return products;

    }

}

