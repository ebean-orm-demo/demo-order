package app.data.query;

import java.util.List;

import setup.Setup;
import app.data.Customer;
import app.data.Order;
import app.data.OrderDetail;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.QueryListener;

/**
 * For large queries / batch processing - you can
 * use QueryListener.
 * <p>
 * Normally all the objects returned from a query are
 * held in the returning list/set/map and persistence 
 * context. Using a QueryListener means that instead 
 * the returned objects are passed to the listener and
 * a new persistence context is used per object graph.
 * </p>
 * <p>
 * This means that if we query 1,000,000 object graphs
 * then we don't need to hold them all in memory.
 * </p>
 */
public class LargeQueryProcessing {

	public static void main(String[] args) {
		
		// load some test data...
		Setup.resetData();
		
		LargeQueryProcessing me = new LargeQueryProcessing();
		me.run();

	}

	/**
	 * Using a QueryListener to process a query.
	 * - Good for processing query's with large results
	 * - Means all the returning objects are not held
	 *   in memory (but instead passed to the listener)
	 */
	private void run() {

		// use a listener means...
		// - the orders are not added to the list
		// - instead the orders are passed to the listener
		// - each order instance has it's own persistence context
		// - we don't hold all the orders in memory 
		
		MyQueryListener listener = new MyQueryListener();
		
		// fetch orders... and include the customer
		// and order details in our object graphs
		List<Order> list = Ebean.find(Order.class)
			.setAutofetch(false)
			.join("details")
			.join("customer")
			.setListener(listener)
			.findList();

		// the list is always empty ... as each order is
		// given to the listener and *NOT* added to the list
		// This means that if we process 1,000,000 orders we
		// don't have them all in memory.
		assert(list.size() == 0);
		
		System.out.println(list);
	}
	
	private static class MyQueryListener implements QueryListener<Order> {

		/**
		 * Process the order with its customer and details.
		 * <p>
		 * This order is then NOT added to the list so that
		 * it does not consume memory. A new 'persistence context'
		 * is used for each Order.
		 * </p>
		 * <p>
		 * I sometimes describe this as ...
		 *    "per object graph persistence context".
		 * </p>
		 */
		public void process(Order order) {
			
			Integer orderId = order.getId();
			Customer customer = order.getCustomer();
			List<OrderDetail> details = order.getDetails();
			
			System.out.println("Process order:"+orderId+" withCust:"+customer.getName()+" details:"+details.size());
			
		}
		
		
	}
}
