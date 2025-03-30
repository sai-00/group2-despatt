package com.keebcove.model;

public class CartItem {
	private int productId;
    private String productName;
    private double price;
    private int quantity;

    public CartItem(int productId, String productName, double price, int quantity) {
    	this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
    

    public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
        return productName;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

