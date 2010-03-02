package app.data.load;

import app.data.Product;

import com.avaje.ebean.Ebean;

public class TestInsert {

	public static void main(String[] args) {
		
		
		Product p = new Product();
		//p.setId(94);
		p.setName("Chair");
		p.setSku("C001");

		Ebean.save(p);
		
		
		p.setName("Banana");
		Ebean.save(p);

	}
}
