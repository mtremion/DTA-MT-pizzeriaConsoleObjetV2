package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.pizzeria.console.Pizza;
import fr.pizzeria.exception.StockageException;

public class PizzaMemDao implements IPizzaDao
{
	private List<Pizza> listPizza = new ArrayList<Pizza>();

	@Override
	public List<Pizza> findAllPizzas() throws StockageException
	{
		return listPizza;
	}

	@Override
	public void saveNewPizza(Pizza pPizza) 
	{
		this.listPizza.add(pPizza);
	}
	

	@Override
	public void deletePizza(String pCode) 
	{
		Pizza pizzaDel = findPizzaByCode(pCode);
		this.listPizza.remove(pizzaDel);
	}
	

	@Override
	public boolean pizzaExists(String pCode) 
	{				
		for(Object o : this.listPizza)
		{
			Pizza pizzaScan = (Pizza) o;
			if((pizzaScan.getCode()).equals(pCode))
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Pizza findPizzaByCode(String pCode) 
	{	
		for(Object o : this.listPizza)
		{
			Pizza pizzaScan = (Pizza) o;
			if((pizzaScan.getCode()).equals(pCode))
			{
				return pizzaScan;
			}
		}
		
		return null;
	}

	@Override
	public void updatePizza(String pCode, Pizza pPizza) 
	{
		if(pizzaExists(pCode))
		{
			deletePizza(pCode);
			saveNewPizza(pPizza);
		}
	}


	
	

}
