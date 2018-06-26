package fr.pizzeria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.console.Pizza;

public class InitialiserBaseService extends MenuService
{
	public InitialiserBaseService(IPizzaDao pIPizzaDao) 
	{
		super(pIPizzaDao);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void executeUC(Scanner scanner) throws SavePizzaException, DeletePizzaException, UpdatePizzaException, StockageException 
	{
		// TODO Auto-generated method stub
		List<Pizza> initList = new ArrayList<>();
		initList.add(new Pizza("PEP", "Pépéroni",CategoriePizza.VIANDE,12.50));
		initList.add(new Pizza("REIN", "La Reine",CategoriePizza.VIANDE,11.50));
		initList.add(new Pizza("FRO", "La 4 fromages", CategoriePizza.VEGETARIENNE,12.00));
		initList.add(new Pizza("CAN", "La cannibale",CategoriePizza.VIANDE, 12.50));
		initList.add(new Pizza("SAV", "La savoyarde", CategoriePizza.VEGETARIENNE,13.00));
		initList.add(new Pizza("ORI", "L'orientale", CategoriePizza.VEGETARIENNE,13.50));
		initList.add(new Pizza("IND", "L'indienne", CategoriePizza.VEGETARIENNE,14.00));
		
		for(Pizza p : initList)
		{
			if(!this.iPizzaDao.pizzaExists(p.getCode()))
			{
				this.iPizzaDao.saveNewPizza(p);
			}
			
		}
		
	}

}
