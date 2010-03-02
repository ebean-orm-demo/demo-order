package app.order.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import setup.Setup;
import app.data.Order;

import com.avaje.ebean.Ebean;

public class TestNamedQueryFromOrmXml {

    // FIXME: maven not loading orm.xml ?
    @Test
    public void test() {
        
        Setup.resetData();
        
        List<Order> orders = Ebean.createNamedQuery(Order.class, "with.cust")
            .where().gt("id", 1)
            .findList();
        
        Assert.assertTrue(orders.size() > 0);
    }
}
