package com.namkit.namki.imagelistviewpicasso;

/**
 * 각 리스트뷰 아이템에 들어갈 새로운 클래스 만들자
 * */
public class Product {
    private String image;
    private String name;
    private String price;

    public Product(String image, String name, String price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
