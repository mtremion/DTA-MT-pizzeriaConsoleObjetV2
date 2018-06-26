package fr.pizzeria.service;


import java.util.Scanner;

import fr.pizzeria.console.Pizza;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.*;

public class SupprimerPizzaService extends MenuService
{
	public SupprimerPizzaService(IPizzaDao pIPizzaDao) {
		super(pIPizzaDao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeUC(Scanner scanner) throws DeletePizzaException
	{
		String codeASuppr;
		System.out.println("***** Suppression d'une pizza *****\n");
		scanner.nextLine();
		
		do
		{
			System.out.println("\nVeuillez choisir le code de la pizza à supprimer");
			codeASuppr = scanner.nextLine().toUpperCase();
			this.iPizzaDao.pizzaExists(codeASuppr);
			
		}while(!this.iPizzaDao.pizzaExists(codeASuppr));
		
		Pizza delPizza = this.iPizzaDao.findPizzaByCode(codeASuppr);
		
		this.iPizzaDao.deletePizza(codeASuppr);
	}
}
