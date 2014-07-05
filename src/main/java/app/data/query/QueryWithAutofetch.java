package app.data.query;

import java.util.List;

import setup.Setup;
import app.data.Order;

import com.avaje.ebean.Ebean;

public class QueryWithAutofetch {

	public static void main(String[] args) throws Exception {
	
		QueryWithAutofetch me = new QueryWithAutofetch();
		me.test();

		System.out.println("done");
	}
	
	private void test() throws Exception {
		
		Setup.resetData();

		List<Order> list = Ebean.find(Order.class)
			.setAutofetch(true)
			.fetch("customer")
			.where()
				.eq("status", Order.Status.NEW)
			.order().desc("shipDate")
			.order().asc("id")
			.findList();
		
		for (Order order : list) {
			order.getOrderDate();
			order.getCustomer().getName();
			System.out.println(order);
		}
			
	}
}
