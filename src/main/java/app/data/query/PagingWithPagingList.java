package app.data.query;

import java.util.List;

import setup.Setup;
import app.data.Order;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.JoinConfig;
import com.avaje.ebean.Page;
import com.avaje.ebean.PagingList;


public class PagingWithPagingList {

	public static void main(String[] args) throws Exception {
		
		// load some test data...
		Setup.resetData();
		
		PagingWithPagingList me = new PagingWithPagingList();
		me.run();

	}

	private void run() throws Exception {

		int pageSize = 10;
		
		PagingList<Order> pagingList = Ebean.find(Order.class)
			
			// NOTE That "fetch joins" to OneToMany can't be 
			// used with SQL firstRow/maxRows. JoinConfig 
			// provides a good solution to this
			.join("details", new JoinConfig().lazy(pageSize))
			.where()
				.gt("id", 0)
				.eq("status", Order.Status.NEW)
						
			// You *SHOULD* have an order by 
			.order().desc("id")
			.findPagingList(pageSize);

		// The benefits of a PagingList over just using
		// firstRow/maxRows is that Ebean will automatically
		// propagate the 'persistence context', support
		// fetch in advance, and built in support for 
		// finding the total row count
	
		//Integer rowCount = pagingList.getFutureRowCount().get();
		int totalCount = pagingList.getTotalRowCount();

		Page<Order> page0 = pagingList.getPage(0);
		List<Order> list = page0.getList();
		String xyz = page0.getDisplayXtoYofZ(" to ", " of ");
		
		System.out.println("Page totalCount: "+totalCount);
		System.out.println("Page    content: "+xyz);
		System.out.println(list);
		
		System.out.println("... read page data");
		
		for (Order order : list) {
			int detailsCount = order.getDetails().size();
			System.out.println("got order:"+order.getId()+" details:"+detailsCount);
		}
	}
	
	
}
