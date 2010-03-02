package app.data.query;

import java.util.List;

import setup.Setup;
import app.data.Order;

import com.avaje.ebean.Ebean;


public class QueryWithImplicitJoin {

	public static void main(String[] args) {
		
		// load some test data...
		Setup.resetData();
		
		QueryWithImplicitJoin me = new QueryWithImplicitJoin();
		me.addJoinForPredicate();
		me.addJoinForOrderBy();

	}

	private void addJoinForPredicate() {

		List<Order> list = Ebean.find(Order.class)
			.where()
				// A JOIN is automatically added to support
				// predicates (and order by) as required
				.ilike("customer.name", "Ro%")
			.findList();

		System.out.println(list);
	}
	
	
	private void addJoinForOrderBy() {

		List<Order> list = Ebean.find(Order.class)
			.where()
				// A JOIN is automatically added to support
				// predicates (and order by) as required
				.order().asc("customer.name")
			.findList();

		System.out.println(list);
	}
	
}
