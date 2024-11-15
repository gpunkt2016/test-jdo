package org.datanucleus.test;

import java.util.*;
import org.junit.*;
import javax.jdo.*;

import static org.junit.Assert.*;
import mydomain.model.*;
import org.datanucleus.util.NucleusLogger;

public class SimpleTest
{
    @Test
    public void testSimple()
    {
        NucleusLogger.GENERAL.info(">> test START");
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("MyTest");

        PersistenceManager pm = null;
        Transaction tx = null;
        try
        {
        	pm = pmf.getPersistenceManager();
            tx = pm.currentTransaction();
            tx.begin();

            // [INSERT code here to persist object required for testing]
           
            Inventory inv = new Inventory("My Inventory");
            HashMap<String, DC_Value> data = new HashMap<String, DC_Value>();
            data.put("s1", new DC_Value("Teststring"));
            data.put("s2", new DC_Value(new Date()));
            Book book = new Book("Lord of the Rings by Tolkien","The classic story",49.99,"JRR Tolkien", "12345678", "MyBooks Factory" , data);
            inv.getProducts().add(book);
            pm.makePersistent(inv);
            
            tx.commit();
        }
        catch (Throwable thr)
        {
            NucleusLogger.GENERAL.error(">> Exception in test", thr);
            fail("Failed test : " + thr.getMessage());
        }
        finally 
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
        
        //Retrieving Extent for Products
        pm = pmf.getPersistenceManager();
      try
      {
          System.out.println("Retrieving Extent for Products");
          
          Query<Product> query = pm.newQuery(Product.class ); 
          ArrayList<Product> res = new ArrayList((Collection) query.execute());
      	  query.closeAll();  
      	System.out.println("products found " + res.size());
      	
      	  if(res!=null) {
      		  if(res.size()==1) {
      			  Book b = (Book)res.get(0);
      			  String s1 = (String)b.getValueOfDCV("s1");
      			  Date d1 = (Date)b.getValueOfDCV("s2");
      			  System.out.println("Hashmap Objects : s1 " + s1 + " d1 " + d1);
      		  }
      	  }
      }
      catch (Exception e)
      {
          System.out.println("Exception thrown during retrieval of Extent : " + e.getMessage());
      }
      finally
      {
          pm.close();
      }

        pmf.close();
        
        this.readDataWithNewPMF();
        NucleusLogger.GENERAL.info(">> test END");
    }
    
    protected void readDataWithNewPMF() {
    	  NucleusLogger.GENERAL.info(">> readDataWithNewPMF START");
          PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("MyTest");

          PersistenceManager pm = null;
          Transaction tx = null;
          try
          {
          	pm = pmf.getPersistenceManager();
              tx = pm.currentTransaction();
              tx.begin();
              Query<Product> query = pm.newQuery(Product.class ); 
              ArrayList<Product> res = new ArrayList((Collection) query.execute());
          	  query.closeAll();  
          	  System.out.println("products found " + res.size());
          	
          	  if(res!=null) {
          		  if(res.size()==1) {
          			  Book b = (Book)res.get(0);
          			  String s1 = (String)b.getValueOfDCV("s1");
          			  Date d1 = (Date)b.getValueOfDCV("s2");
          			  System.out.println("Hashmap Objects : s1 " + s1 + " d1 " + d1);
          		  }
          	  }
              tx.commit();
          }
          catch (Throwable thr)
          {
              NucleusLogger.GENERAL.error(">> Exception in readDataWithNewPMF", thr);
              fail("Failed test : " + thr.getMessage());
          }
          finally 
          {
              if (tx.isActive())
              {
                  tx.rollback();
              }
              pm.close();
          }
          pmf.close();
          NucleusLogger.GENERAL.info(">> readDataWithNewPMF END");

    }
}
