package app.data.query;

import java.util.List;

import setup.Setup;
import app.data.Order;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;


public class QueryFetchingRowCount {

	public static void main(String[] args) {
		

		
		// load some test data...
		Setup.resetData();
		
		QueryFetchingRowCount me = new QueryFetchingRowCount();
		me.run();

	}

	private void run() {

		Query<Order> query = Ebean.find(Order.class)
			.fetch("customer")
			.where()
				.gt("id", 0)
				.eq("status", Order.Status.NEW)
				.ilike("customer.name", "Ro%")
			.query();
		
		// execute a row count query for this
		int rowCount = query.findRowCount();
		
		// execute the original query
		List<Order> list = query.findList();

		System.out.println("RowCount was: "+rowCount);
		System.out.println(list);
	}
	
	
}
