package app.data;

import java.util.Set;

import com.avaje.ebean.event.BeanPersistAdapter;
import com.avaje.ebean.event.BeanPersistRequest;

/**
 * Can be used to extend or override the insert/update/delete
 * processing.
 * <p>
 * Extend: typically setting property values (e.g. audit columns) and 
 * returning true so that Ebean continues the insert OR
 * </p>
 * <p>
 * Override: handle the insert processing yourself (call a stored
 * procedure etc and return false so that Ebean doesn't continue 
 * processing the insert.
 * </p>
 */
public class OrderController extends BeanPersistAdapter {

	@Override
	public boolean isRegisterFor(Class<?> cls) {
		if (cls.equals(Order.class)){
			return true;
		}
		if (cls.equals(OrderDetail.class)){
			return true;
		}
		return false;
	}

	@Override
	public void postLoad(Object bean, Set<String> includedProperties) {

	}

	@Override
	public boolean preInsert(BeanPersistRequest<?> request) {
		
		// do something interesting like
		// 1) set audit properties and return true (continue normal processing)
		// 2) use a store procedure to do the insert and return false
	
//		// **UNCOMMENT** The next 5 lines
//		// we are just going to send stuff to system out
//		Object bean = request.getBean();
//		Transaction txn = request.getTransaction();
//		
//		System.out.println("... OrderController > inserting txn["+txn+"] "+bean);
		
		// return true means Ebean will continue normal processing
		// of the insert
		
		// If instead we return false then that means we will have 
		// handled the actual insert (maybe by using a stored procedure 
		// to perform the insert) and Ebean should not continue with
		// the insert processing.
		return true;
		
	}

	
	
}
