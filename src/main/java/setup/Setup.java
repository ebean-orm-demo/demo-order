package setup;

public class Setup {

	public static void resetData() {
		
		DeleteData delete = new DeleteData();
		delete.deleteAll();
		
		InsertSeedData seed = new InsertSeedData();
		seed.insert();
		
		LoadOrderData loadOrders = new LoadOrderData();
		loadOrders.insert();
		
	}
	
}
