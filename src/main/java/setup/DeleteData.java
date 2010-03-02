package setup;

import app.data.Address;
import app.data.Customer;
import app.data.Order;
import app.data.OrderDetail;

import com.avaje.ebean.Ebean;

public class DeleteData {

	public static void main(String[] args) {
		DeleteData me = new DeleteData();
		me.deleteAll();
	}
	
	
	public void deleteAll() {
		Ebean.beginTransaction();
		try {
			// orm update use bean name and bean properties
			Ebean.createUpdate(OrderDetail.class, "delete from orderDetail")
				.execute();
			
			Ebean.createUpdate(Order.class, "delete from order")
				.execute();

			Ebean.createUpdate(Customer.class, "delete from Customer")
				.execute();

			Ebean.createUpdate(Address.class, "delete from address")
			.execute();

			// sql update uses table and column names
			Ebean.createSqlUpdate("delete from o_country")
				.execute();

			Ebean.createSqlUpdate("delete from o_product")
				.execute();

			
			Ebean.commitTransaction();
			
		} finally {
			Ebean.endTransaction();
		}
	}
}
