package com.imcs.maven.hib.dao;

import java.util.List;

import com.imcs.maven.hib.pojo.Products;

public interface IProductsDao {
	void addProducts(Products products);
	public void updateProducts(Products products);
	List<Products> getProducts();
	Products getProduct(Integer next);
}
