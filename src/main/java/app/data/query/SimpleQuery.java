package app.data.query;

import java.util.List;

import setup.Setup;
import app.data.Order;

import com.avaje.ebean.Ebean;


public class SimpleQuery {

	public static void main(String[] args) {
		
		// load some test data...
		Setup.resetData();
		
		SimpleQuery me = new SimpleQuery();
		me.run();

	}

	private void run() {

		
		List<Order> list = Ebean.find(Order.class)
			.fetch("customer")
			.where()
				.gt("id", 0)
				.eq("status", Order.Status.NEW)
				.ilike("customer.name", "Ro%")
			.findList();

		System.out.println(list);
	}
	
	
}
