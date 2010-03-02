package setup;

import app.data.Country;
import app.data.Product;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.TxRunnable;
import com.avaje.ebean.annotation.Transactional;

/**
 * Insert some seed data.
 */
public class InsertSeedData {

	public static void main(String[] args) {
		InsertSeedData me = new InsertSeedData();
		me.insert();
	}
	
	@Transactional
	public void insert() {
		insertCountries();
		insertProducts();
	}
	
	public void insertCountries() {
		
		Ebean.execute(new TxRunnable() {
			public void run() {
				Country c = new Country();
				c.setCode("NZ");
				c.setName("New Zealand");
				Ebean.save(c);
				
				Country au = new Country();
				au.setCode("AU");
				au.setName("Australia");
				Ebean.save(au);				
			}
		});
	}
	
	@Transactional
	public void insertProducts() {
		Product p = new Product();
		p.setId(1);
		p.setName("Chair");
		p.setSku("C001");
		Ebean.save(p);

		p = new Product();
		p.setId(2);
		p.setName("Desk");
		p.setSku("DSK1");
		Ebean.save(p);

		p = new Product();
		p.setId(3);
		p.setName("Computer");
		p.setSku("C002");
		Ebean.save(p);

		p = new Product();
		p.setId(4);
		p.setName("Printer");
		p.setSku("C003");
		Ebean.save(p);
	}
}
