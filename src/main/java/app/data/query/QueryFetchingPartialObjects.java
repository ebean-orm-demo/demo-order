package app.data.query;

import java.util.List;

import setup.Setup;
import app.data.Order;

import com.avaje.ebean.Ebean;

public class QueryFetchingPartialObjects {

	public static void main(String[] args) {
		
		
		// load some test data...
		Setup.resetData();
		
		QueryFetchingPartialObjects me = new QueryFetchingPartialObjects();
		me.partialObjectQuery();

	}

	public void partialObjectQuery() {

		List<Order> list = Ebean.find(Order.class)
			.select("id, status, orderDate")
			.join("customer", "name")
			.join("details", "id, orderQty")
			.join("details.product", "name, sku")
			.where().eq("status",Order.Status.NEW)
			.findList();

		if (list.size() > 0){
			Order firstOrder = list.get(0);
			// access a field not initially loaded by the
			// query causes a lazy-loading query
			firstOrder.getShipDate();
		}
		
		System.out.println(list);

	}

	/**

	summary='[app.data.Order, customer] +many[details, details.product]
	
	select o.id, o.status, o.order_date
	        , c.id, c.name
	        , d.id, d.order_qty
	        , dp.id, dp.name, dp.sku 
	from o_order o
	join o_customer c on o.customer_id = c.ID 
	left outer join o_order_detail d on o.ID = d.order_id 
	left outer join o_product dp on d.PRODUCT_ID = dp.ID  
	where o.status = ?  
	order by o.id	

	 */		
}
