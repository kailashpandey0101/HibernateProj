package com.imcs.maven.hib.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import com.imcs.maven.hib.dao.CustomerDao;
import com.imcs.maven.hib.dao.ICustomerDao;
import com.imcs.maven.hib.dao.IOrderDao;
import com.imcs.maven.hib.dao.IProductsDao;
import com.imcs.maven.hib.dao.OrderDao;
import com.imcs.maven.hib.dao.ProductsDao;
import com.imcs.maven.hib.pojo.Address;
import com.imcs.maven.hib.pojo.Customer;
import com.imcs.maven.hib.pojo.Orders;
import com.imcs.maven.hib.pojo.OrdersProducts;
import com.imcs.maven.hib.pojo.Products;
import com.imcs.maven.hib.services.IOrderServices;
import com.imcs.maven.hib.services.OrderServices;
import com.imcs.maven.hib.services.ProductServices;

public class CustomerApp {

	public static void main(String[] args) throws ParseException {
		try (Scanner sc = new Scanner(System.in);) {
			while (true) {
				System.out.println("-----------Menu------------");
				System.out.println("1.  Add a customer");
				System.out.println("2.  Add a Product");
				System.out.println("3.  Add an Order");
				System.out.println("4. Select Order Operations");
				System.out.println("5.  Exit");

				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					addCustomer(sc);
					break;
				case 2:
					addProducts(sc);
					break;
				case 3:
					addOrders(sc);
					break;
				case 4:
					selectOrderOperations(sc);
					break;
				case 5:
					System.exit(0);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void selectOrderOperations(Scanner sc) throws ParseException {
		System.out.println("------------------------------------------");
		System.out.println("1.\tGet orders by Invoice date range");
		System.out.println("2.\tGet orders by Delivery date range");
		System.out.println("3.\tGet orders by Price");
		System.out.println("4.\tGet orders by status");
		System.out.println("5.\tGet products of particular order");
		System.out.println("6.\tExit module");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			getOrdersByInvoiceRange(sc);
			break;
		case 2:
			getOrdersByDeliveryRange(sc);
			break;
		case 3:
			getOrdersByPrice(sc);
			break;
		case 4:
			getOrdersByStatus(sc);
			break;
		case 5:
			getProductsByOrderId(sc);
			break;
		case 6:
			System.out.println("Exiting this module........");
			break;
		}

	}

	private static void getProductsByOrderId(Scanner sc) {
		System.out.println("Enter the order id");
		int orderId = sc.nextInt();
		IOrderServices orderServices = new OrderServices();
		ProductServices productServices = new ProductServices();
		List<Integer> productsId = orderServices.getProductsByOrderId(orderId);
		for (Products product : productServices.getProducts(productsId)) {
			System.out.println(product);
		}
	}

	private static void getOrdersByStatus(Scanner sc) {
		System.out.println("Enter the Status");
		String status = sc.next();
		IOrderServices orderServices = new OrderServices();
		for (Orders o : orderServices.getOrdersByStatus(status)) {
			System.out.println(o);
		}

	}

	private static void getOrdersByPrice(Scanner sc) {
		IOrderServices orderServices = new OrderServices();
		System.out.println("Please Enter the price");
		double price = (double) sc.nextInt();
		for (Orders o : orderServices.getOrdersByTotalPrice(price)) {
			System.out.println(o);
		}

	}

	private static void getOrdersByDeliveryRange(Scanner sc) throws ParseException {
		IOrderServices orderServices = new OrderServices();
		System.out.println("Input the date range: Enter first date, hit enter and enter the second date. (MM/DD/YYYY)");
		String startDate = sc.next();
		String endDate = sc.next();
		SimpleDateFormat format = new SimpleDateFormat("MM/DD/yyyy");
		List<Orders> ordersByInvoiceRange = orderServices.getOrdersByInvoiceRange(format.parse(startDate),
				format.parse(endDate));
		for (Orders o : ordersByInvoiceRange) {
			System.out.println(o);
		}

	}

	private static void getOrdersByInvoiceRange(Scanner sc) throws ParseException {
		IOrderServices orderServices = new OrderServices();
		System.out.println("Input the date range: Enter first date, hit enter and enter the second date. (MM/DD/YYYY)");
		String startDate = sc.next();
		String endDate = sc.next();
		SimpleDateFormat format = new SimpleDateFormat("MM/DD/yyyy");
		List<Orders> ordersByDeliveryRange = orderServices.getOrdersByDeliveryRange(format.parse(startDate),
				format.parse(endDate));
		for (Orders o : ordersByDeliveryRange) {
			System.out.println(o);
		}

	}

	private static void addOrders(Scanner sc) throws ParseException {
		IProductsDao productsDao = new ProductsDao();
		ICustomerDao customerDao = new CustomerDao();
		IOrderDao orderDao = new OrderDao();
		Orders order = new Orders();
		double totalPrice = 0.0;
		SimpleDateFormat format = new SimpleDateFormat("MM/DD/yyyy");

		order.setDeliverDate(format.parse("02/14/2018"));
		order.setInvoiceDate(format.parse("02/12/2018"));
		order.setPaymentDate(format.parse("02/20/2018"));
		order.setStatus("pending");
		System.out.println("Enter your ID : (Customer id)");
		long custId = (long) sc.nextInt();
		Customer customer = customerDao.loadCustomer(custId);
		order.setCustomer(customer);
		boolean flag = true;
		while (flag) {
			System.out.println("------------Prodcuts Menu-----------------");
			System.out.printf("%-10s %-10s %-10s %-10s\n", "Product Id", "Name", "Description", "Price");
			for (Products p : productsDao.getProducts()) {
				System.out.printf("%-10s %-10s %-10s %-10s\n", p.getProductId(), p.getName(), p.getDescription(),
						p.getPrice());
			}
			System.out.println("Enter the product id");
			int choice = sc.nextInt();
			Products products = productsDao.getProducts().get(choice - 1);

			OrdersProducts ordersProducts = new OrdersProducts();
			System.out.println("Enter the Quantity");
			int quantity = sc.nextInt();
			totalPrice = totalPrice + products.getPrice() * quantity;
			ordersProducts.setQuantity(quantity);
			ordersProducts.setOrders(order);
			ordersProducts.setProducts(products);
			order.getOrdersProducts().add(ordersProducts);
			products.getOrdersProducts().add(ordersProducts);
			// opDao.addOrdersProducts(ordersProducts);
			// orderDao.addOrder(order);
			System.out.println("Want to add more products: 1 for yes and 2 for No");
			choice = sc.nextInt();
			if (choice == 1) {
				flag = true;
			} else if (choice == 2) {
				order.setTotalPrice(totalPrice);
				orderDao.addOrder(order);
				flag = false;
			} else {
				System.out.println("Wrong Choice");
				order.setTotalPrice(totalPrice);
				orderDao.addOrder(order);
				flag = false;
			}
		}

	}

	private static void addProducts(Scanner sc) {
		Products products = new Products();
		System.out.println("Enter the Name");
		String name = sc.next();
		products.setName(name);
		System.out.println("Enter the price");
		double price = (double) sc.nextInt();
		products.setPrice(price);
		System.out.println("Enter the Description");
		String desc = sc.next();
		products.setDescription(desc);

		IProductsDao productsDao = new ProductsDao();
		productsDao.addProducts(products);

	}

	private static void addCustomer(Scanner sc) {
		Customer customer = new Customer();
		System.out.println("Enter the First Name");
		String fName = sc.next();
		customer.setFirstName(fName);
		System.out.println("Enter the Last Name");
		String lName = sc.next();
		customer.setLastName(lName);
		System.out.println("Enter the Email");
		String email = sc.next();
		customer.setEmail(email);

		// address
		Address address = new Address();
		System.out.println("Enter the street");
		String street = sc.next();
		address.setStreet(street);
		System.out.println("Enter the city");
		String city = sc.next();
		address.setCity(city);
		System.out.println("Enter the state");
		String state = sc.next();
		address.setState(state);
		System.out.println("Enter the Zip");
		String zip = sc.next();
		address.setZipCode(zip);
		customer.setAddress(address);
		ICustomerDao customerDao = new CustomerDao();
		customerDao.addCustomer(customer);

	}

}
