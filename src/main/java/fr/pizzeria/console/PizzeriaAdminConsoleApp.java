package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoBase;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.*;
import fr.pizzeria.service.MenuServiceFactory;

public class PizzeriaAdminConsoleApp 
{

	public static void main(String[] args) throws StockageException
	{
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		//IPizzaDao menu = new PizzaMemDao();
		IPizzaDao menu = new PizzaDaoBase();
		
		do
		{
			System.out.println("***** Pizzeria Administration *****\n");
			System.out.println("1. Lister les pizzas");
			System.out.println("2. Ajouter une nouvelle pizza");
			System.out.println("3. Mettre à jour une pizza");
			System.out.println("4. Supprimer une pizza");
			System.out.println("5. Initialiser Base de Données");
			System.out.println("99. Sortir");
				
			choice = sc.nextInt();
			
			if(choice == 1)
			{
				MenuServiceFactory.getMenuService(menu,choice).executeUC(sc);
			}
			else if(choice == 2)
			{
				MenuServiceFactory.getMenuService(menu,choice).executeUC(sc);
	
			}
			else if(choice == 3)
			{
				MenuServiceFactory.getMenuService(menu,choice).executeUC(sc);
	
			}
			else if(choice == 4)
			{
				MenuServiceFactory.getMenuService(menu,choice).executeUC(sc);
	
			}
			else if(choice == 5)
			{
				MenuServiceFactory.getMenuService(menu,choice).executeUC(sc);
			}
					
			/*catch(SavePizzaException e)
			{
				System.out.println("Il y a eu une erreur lors de l'ajout de votre pizza. Veuillez r�essayer.");
			}
			catch(DeletePizzaException e)
			{
				System.out.println("Il y a eu une erreur lors de la suppresion de votre pizza. Veuillez r�essayer.");
			}
			catch(UpdatePizzaException e)
			{
				System.out.println("Il y a eu une erreur lors de la mise � jour de votre pizza. Veuillez r�essayer.");
			}*/
		
		
		}while(choice != 99);
			
		System.out.println("Au revoir !");
			
		sc.close();

	}

}
