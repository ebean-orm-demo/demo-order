package app.data.query;

import java.util.List;

import setup.Setup;
import app.data.Order;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;


public class ProgrammaticallyConfigureBootup {

	public static void main(String[] args) {
		
		
		// programmatically configure including
		// DDL generation and execution
		ServerConfig c = new ServerConfig();
		c.setName("h2");
		
		// load settings from ebean.properties
		// as a starting point
		//c.loadFromProperties();
		
		c.setDdlGenerate(false);
		c.setDdlRun(false);
		
		// set as the 'default' EbeanServer
		c.setDefaultServer(true);
		
		DataSourceConfig dsConfig = new DataSourceConfig();
		dsConfig.setUrl("jdbc:h2:database/test");
		dsConfig.setUsername("sa");
		dsConfig.setPassword("");
		dsConfig.setDriver("org.h2.Driver");
		c.setDataSourceConfig(dsConfig);

		EbeanServerFactory.create(c);
		
		
		// load some test data...
		Setup.resetData();
		
		ProgrammaticallyConfigureBootup me = new ProgrammaticallyConfigureBootup();
		me.runSimpleQuery();
	}

	
	
	private void runSimpleQuery() {

		List<Order> l0 = Ebean.find(Order.class).findList();
		System.out.println(l0);
		
	}
	
}
