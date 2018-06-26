package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.console.Pizza;
import fr.pizzeria.exception.StockageException;

public interface IPizzaDao
{	
	List<Pizza> findAllPizzas() throws StockageException;
	void saveNewPizza(Pizza pPizza);
	void updatePizza(String pCode, Pizza pPizza);
	void deletePizza(String pcode);
	Pizza findPizzaByCode(String pCode);
	boolean pizzaExists(String pCode);
}
