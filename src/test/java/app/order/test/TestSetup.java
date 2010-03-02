package app.order.test;

import junit.framework.Assert;

import org.junit.Test;

import setup.Setup;
import app.data.Order;

import com.avaje.ebean.Ebean;

public class TestSetup {

    @Test
    public void test() {
        
        Setup.resetData();
        
        int rc = Ebean.find(Order.class)
            .findRowCount();
        
        Assert.assertTrue("got orders",rc > 0);
        
    }
}
