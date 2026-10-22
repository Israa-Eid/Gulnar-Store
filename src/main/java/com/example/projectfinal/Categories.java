package com.example.projectfinal;


public class Categories {
    private String image_categoriesName;
    private  String imagePath;
    String buttonCategoried;

    public Categories(String image_categoriesName, String imagePath,String buttonCategoried) {
        this.image_categoriesName = image_categoriesName;
        this.imagePath = imagePath;
        this.buttonCategoried=buttonCategoried;
    }

    public String getImageName() {
        return image_categoriesName;
    }

    public void setImageName(String imageName) {
        this.image_categoriesName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getImage_categoriesName() {
        return image_categoriesName;
    }

    public void setImage_categoriesName(String image_categoriesName) {
        this.image_categoriesName = image_categoriesName;
    }

    public String getButtonCategoried() {
        return buttonCategoried;
    }

    public void setButtonCategoried(String buttonCategoried) {
        this.buttonCategoried = buttonCategoried;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

//    @Override
//    public String toString() {
//        return "File_Categories{" +
//                "imageName='" + image_categoriesName + '\'' +
//                ", imagePath='" + imagePath + '\'' +
//                '}';
//    }
}



