package com.example.projectfinal;

public class Products {
        private String category;
        private String productName;
        private String imageName;
        private double price;
        private String madeIn;
        private String imagesPathes;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }



    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }


    public String getImagesPathes() {
        return imagesPathes;
    }

    public void setImagesPathes(String imagesPathes) {
        this.imagesPathes = imagesPathes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Products(String category, String productName, String imageName, double price, String madeIn){
        this.category=category;
        this.productName=productName;
        this.price=price;
        this.imageName=imageName;
        this.madeIn=madeIn;
    }

    }



