package app.data.query;

import java.util.List;

import setup.Setup;
import app.data.Order;

import com.avaje.ebean.Ebean;


public class QueryWithLotsOfJoins {

	public static void main(String[] args) {
		
		// load some test data...
		Setup.resetData();
		
		QueryWithLotsOfJoins me = new QueryWithLotsOfJoins();

		me.joinsWithFullObjects();
		me.joinsWithPartialObjects();
	}

	
	/**
	 * Fetch a full object graph.
	 */
	private void joinsWithFullObjects() {

		List<Order> list = Ebean.find(Order.class)
			.fetch("details")
			.fetch("details.product")
			.fetch("customer")
			.fetch("customer.billingAddress")
			.fetch("customer.shippingAddress")
			.where().eq("status",Order.Status.NEW)
			.findList();

		System.out.println(list);

	}

	private void joinsWithPartialObjects() {
		
		List<Order> list = Ebean.find(Order.class)
			.select("id, status, orderDate")
			.fetch("customer", "name")
			.fetch("details", "id, orderQty")
			.fetch("details.product", "name, sku")
			.where().eq("status",Order.Status.NEW)
			.findList();

		System.out.println(list);

	}
	
}
