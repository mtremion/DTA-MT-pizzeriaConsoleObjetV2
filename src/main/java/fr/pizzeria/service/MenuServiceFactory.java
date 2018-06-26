package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.*;

public class MenuServiceFactory 
{	
	public static MenuService getMenuService(IPizzaDao pIPizzaDao, int pChoix) throws StockageException
	{		
		switch(pChoix)
		{
			case(1) : 			
				return new ListerPizzasService(pIPizzaDao);
			case(2) : 
				return new AjouterPizzaService(pIPizzaDao);
			case(3) : 
				return new ModifierPizzaService(pIPizzaDao);
			case(4) : 
				return new SupprimerPizzaService(pIPizzaDao);
			case(5) : 
				return new InitialiserBaseService(pIPizzaDao);
			case(99) : 
				return null;
			default :
				throw new StockageException("Choix Inexistant !");
		}
	}
	
}
