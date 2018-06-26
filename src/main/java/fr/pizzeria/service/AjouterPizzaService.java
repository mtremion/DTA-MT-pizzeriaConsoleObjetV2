
package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.console.Pizza;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;

public class AjouterPizzaService extends MenuService
{

	public AjouterPizzaService(IPizzaDao pIPizzaDao) 
	{
		super(pIPizzaDao);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeUC(Scanner scanner) throws SavePizzaException
	{

		String tempCode;
		String tempLibelle;
		double tempPrix;
		
		String tempCategorie;

	
		System.out.println("*** Ajout d'une nouvelle pizza ***\n");
			
		System.out.println("Veuillez saisir le code :");
		tempCode = scanner.nextLine();
			
		tempCode = scanner.nextLine().toUpperCase();
		
		if(this.iPizzaDao.pizzaExists(tempCode) == true)
		{
			throw new SavePizzaException("Une pizza est déjà enregistré avec ce code. Veuillez en saisir un nouveau");
		}
		
		
		System.out.println("Veuillez saisir le nom (sans espace):");
		tempLibelle = scanner.nextLine();
		
		if(tempLibelle.isEmpty())
		{
			throw new IllegalArgumentException("Vous avez entré une mauvaise valeur. Veuillez réessayer.");
		}
		
		System.out.println("Veuillez saisir la catégorie : Viande/Poisson/Vegetarienne");
		tempCategorie= scanner.nextLine();
		
		System.out.println("Veuillez saisir le prix :");
		tempPrix = Double.parseDouble(scanner.nextLine());
		
		if(tempPrix < 0)
		{
			throw new SavePizzaException("Vous avez saisis un prix négatif !");
		}
			
		Pizza newPizza = new Pizza(tempCode,tempLibelle,CategoriePizza.getCategorieByLibelle(tempCategorie),tempPrix);
		
		
		this.iPizzaDao.saveNewPizza(newPizza);
		
		
		
	}

}
