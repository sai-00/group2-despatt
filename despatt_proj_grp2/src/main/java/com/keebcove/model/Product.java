package com.keebcove.model;

public class Product implements Cloneable{
	private int id;
    private String category;
    private String name;
    private double price;
    private String description;
    private int quantity;

    public Product(int id, String category, String name, double price, String description, int quantity) {
    	this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }
    
    public int getId() { return id; }
    public String getCategory() { return category; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public int getQuantity() { return quantity; }
    
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported for Product", e);
        }
    }
}
