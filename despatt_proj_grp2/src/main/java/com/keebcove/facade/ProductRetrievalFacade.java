package com.keebcove.facade;


import com.keebcove.model.Product;
import com.keebcove.utility.Facade;
import com.keebcove.utility.ProductCache;

public class ProductRetrievalFacade implements Facade {
	private int productId;
    private Product product;
    
	public ProductRetrievalFacade() {}
	
	public ProductRetrievalFacade(int productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

	@Override
	public void process() {
		this.product = ProductCache.getProduct(this.productId);
	}

}
