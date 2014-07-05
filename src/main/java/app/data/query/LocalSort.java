package app.data.query;

import java.util.List;

import setup.Setup;

import app.data.Order;

import com.avaje.ebean.Ebean;

/**
 * Using the local (non-database) list sorting.
 */
public class LocalSort {

	public static void main(String[] args) {
		
		Setup.resetData();
		
		List<Order> list = Ebean.find(Order.class)
			.fetch("customer")
			.order("id")
			.findList();

		list.get(0).getCustomer().setName(null);
		printList(list);
		
		Ebean.sort(list, "customer.name asc");

		System.out.println("... after sort asc");
		printList(list);
		
		Ebean.sort(list, "customer.name desc");

		System.out.println("... after sort desc");
		printList(list);

		Ebean.sort(list, "customer.name asc nullsLow");

		System.out.println("... after sort asc nullsLow");
		printList(list);

		Ebean.sort(list, "customer.name asc nullsHigh");

		System.out.println("... after sort asc nullsHigh");
		printList(list);

		Ebean.sort(list, "customer.name");

		System.out.println("... after sort");
		printList(list);

		
		System.out.println("----------- ");
		
		Ebean.sort(list, "customer.name desc nullsHigh");

		System.out.println("... after sort desc nullsHigh");		
		printList(list);

		Ebean.sort(list, "customer.name desc nullsLow, shipDate, status");

		System.out.println("... after sort desc nullsLow");
		printList(list);
	}
	
	private static void printList(List<Order> list) {
		
		for (Order order : list) {
			System.out.println(order.getCustomer().getName()+"  "+order.getId());
		}
	}
}
