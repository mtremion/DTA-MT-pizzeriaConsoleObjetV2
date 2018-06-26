package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.*;

public abstract class MenuService 
{
	protected IPizzaDao iPizzaDao;
	

	public MenuService(IPizzaDao pIPizzaDao)
	{
		this.iPizzaDao = pIPizzaDao;
	}
	
	public abstract void executeUC(Scanner scanner) throws SavePizzaException, DeletePizzaException, UpdatePizzaException, StockageException ;
}
