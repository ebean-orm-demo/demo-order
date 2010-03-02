package setup;

import java.util.ArrayList;
import java.util.List;

import app.data.Address;
import app.data.Country;
import app.data.Customer;
import app.data.Order;
import app.data.OrderDetail;
import app.data.Product;

import com.avaje.ebean.Ebean;

public class LoadOrderData {

	public static void main(String[] args) {

		LoadOrderData me = new LoadOrderData();
		me.insert();
	}
	
	
	public void insert() {

		Customer cust1 = insertCustomerNoAddress();
		Customer cust2 = insertCustomer();
	
		createOrder1(cust1);
		createOrder2(cust2);
		createOrder3(cust1);
	}

	private Customer insertCustomerNoAddress() {
		
		Customer c = new Customer();
		c.setName("Cust NoAddress");
		c.setStatus(Customer.Status.NEW);

		Ebean.save(c);
		return c;
	}
	
	private Customer insertCustomer() {
		
		Customer c = new Customer();
		c.setName("Rob");
		c.setStatus(Customer.Status.NEW);
		
		Address shippingAddr = new Address();
		shippingAddr.setLine1("1 Banana St");
		shippingAddr.setLine2("Sandringham");
		shippingAddr.setCity("Auckland");
		shippingAddr.setCountry(Ebean.getReference(Country.class, "NZ"));
		
		c.setShippingAddress(shippingAddr);
		
		Address billingAddr = new Address();
		billingAddr.setLine1("P.O.Box 1234");
		billingAddr.setLine2("Sandringham");
		billingAddr.setCity("Auckland");
		billingAddr.setCountry(Ebean.getReference(Country.class, "NZ"));
		
		c.setBillingAddress(billingAddr);
		
		Ebean.save(c);
		
		return c;
	}
	
	private void createOrder1(Customer customer) {
		
		Product product1 = Ebean.getReference(Product.class, 1);
		Product product2 = Ebean.getReference(Product.class, 2);
		Product product3 = Ebean.getReference(Product.class, 3);
			
		
		Order order = new Order();
		order.setCustomer(customer);
		
		List<OrderDetail> details = new ArrayList<OrderDetail>();
		details.add(new OrderDetail(product1, 5));
		details.add(new OrderDetail(product2, 3));
		details.add(new OrderDetail(product3, 1));
		order.setDetails(details);
		
		Ebean.save(order);
	}

	private void createOrder2(Customer customer) {
		
		Product product1 = Ebean.getReference(Product.class, 1);
					
		Order order = new Order();
		order.setCustomer(customer);
		
		List<OrderDetail> details = new ArrayList<OrderDetail>();
		details.add(new OrderDetail(product1, 4));
		order.setDetails(details);
		
		Ebean.save(order);
	}

	private void createOrder3(Customer customer) {
		
		Product product1 = Ebean.getReference(Product.class, 1);
		Product product3 = Ebean.getReference(Product.class, 3);
					
		Order order = new Order();
		order.setCustomer(customer);
		
		List<OrderDetail> details = new ArrayList<OrderDetail>();
		details.add(new OrderDetail(product1, 3));
		details.add(new OrderDetail(product3, 40));
		order.setDetails(details);
		
		Ebean.save(order);
	}
}
