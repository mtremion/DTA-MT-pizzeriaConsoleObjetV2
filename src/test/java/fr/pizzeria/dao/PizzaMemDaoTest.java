package fr.pizzeria.dao;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import fr.pizzeria.console.Pizza;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;

public class PizzaMemDaoTest 
{
	List<Pizza> tempListPizza;
	List<Pizza> tempListPizzaTest;
	boolean tempBool;

/*	@Test
	public void test() 
	{
		fail("Not yet implemented");
	}*/
	
	@Test
	public void testFindAllPizzas() 
	{
		PizzaMemDao tempMemDao = new PizzaMemDao();
		try
		{						
			tempMemDao.findAllPizzas().add(new Pizza("CHE", "Chevre", CategoriePizza.VEGETARIENNE, 12.00));
			Assert.assertTrue((tempMemDao.findAllPizzas()).size() == 1);
			
			tempMemDao.findAllPizzas().add(new Pizza("KEB", "Kebab", CategoriePizza.VIANDE, 14.00));
			tempMemDao.findAllPizzas().add(new Pizza("SAU", "Saumon", CategoriePizza.POISSON, 13.50));
			Assert.assertTrue((tempMemDao.findAllPizzas()).size() == 3);
			
			System.out.println("Test OK.");
											
		}
		catch(StockageException e)
		{
			Assert.fail("Erreur lors du test.");
		}
	}
	
	@Test
	public void testSaveNewPizza()
	{
		PizzaMemDao tempMemDao = new PizzaMemDao();
		Pizza tempPizza = new Pizza("CHE", "Chevre", CategoriePizza.VEGETARIENNE, 12.00);
		
		try
		{						
			tempMemDao.saveNewPizza(tempPizza);
			Assert.assertTrue((tempMemDao.findAllPizzas().contains(tempPizza)));
			
			System.out.println("Test OK.");
											
		}
		catch(StockageException e)
		{
			Assert.fail("Erreur lors du test.");
		}	
	}
	
	@Test
	public void testUpdatePizza() throws StockageException
	{
		PizzaMemDao tempMemDao = new PizzaMemDao();
		Pizza tempPizza = new Pizza("CHE", "Chevre", CategoriePizza.VEGETARIENNE, 12.00);
		tempMemDao.findAllPizzas().add(tempPizza);
		Pizza tempPizzaTest = new Pizza("CHEV", "Chèvre", CategoriePizza.VEGETARIENNE, 15.00);
		
		try
		{
			tempMemDao.updatePizza(tempPizza.getCode(),tempPizzaTest);
			Assert.assertTrue(tempMemDao.findAllPizzas().contains(tempPizzaTest));
			Assert.assertFalse(tempMemDao.findAllPizzas().contains(tempPizza));
		
			System.out.println("Test OK.");
		}
		catch(UpdatePizzaException e)
		{
			
			
			Assert.fail("Erreur lors du test.");
		}
		
	}
	
	@Test
	public void testDeletePizza()
	{
		
	}
	
	@Test
	public void testFindPizzaByCode()
	{
		
	}

	@Test
	public void testPizzaExists()
	{
		
	}

}
