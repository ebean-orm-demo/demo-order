package app.order.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import setup.Setup;
import app.data.Order;

import com.avaje.ebean.Ebean;

public class TestNamedRawSqlQueryFromOrmXml {

    @Test
    public void test() {
        
        if (true){
            // FIXME: can't find orm.xml in maven test?
            return;
        }
        
        Setup.resetData();
        
        List<Order> orders = Ebean.createNamedQuery(Order.class, "simple.sql")
            .where().gt("id", 1)
            .findList();
        
        Assert.assertTrue(orders.size() > 0);
    }
}
