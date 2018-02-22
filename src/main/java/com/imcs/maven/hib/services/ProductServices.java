package com.imcs.maven.hib.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.imcs.maven.hib.dao.IProductsDao;
import com.imcs.maven.hib.dao.ProductsDao;
import com.imcs.maven.hib.pojo.Products;

public class ProductServices {
IProductsDao productsDao=new ProductsDao();
public List<Products> getProducts(List<Integer> ids){
	List<Products> productsList=new ArrayList<>();
	for(Iterator<Integer> iter=ids.iterator();iter.hasNext();) {
		productsList.add(productsDao.getProduct(iter.next()));
	}
	
	return productsList;
	
}
}
