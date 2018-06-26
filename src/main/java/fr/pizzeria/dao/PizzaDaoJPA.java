package fr.pizzeria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.pizzeria.console.Pizza;
import fr.pizzeria.exception.StockageException;

public class PizzaDaoJPA implements IPizzaDao
{
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria");
	EntityManager em = entityManagerFactory.createEntityManager();
	
	@Override
	public List<Pizza> findAllPizzas() throws StockageException 
	{
		// TODO Auto-generated method stub
		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p", Pizza.class);
		List<Pizza> p = query.getResultList();
		return p;
	}

	@Override
	public void saveNewPizza(Pizza pPizza) 
	{
		// TODO Auto-generated method stub
		if(!pizzaExists(pPizza.getCode()))
		{
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(pPizza);
			et.commit();
		}
		
	}

	@Override
	public void updatePizza(String pCode, Pizza pPizza) 
	{
		// TODO Auto-generated method stub
		if(pizzaExists(pCode))
		{
			Pizza p = findPizzaByCode(pCode);
			EntityTransaction et = em.getTransaction();
			et.begin();
		
			p.setCode(pCode);
			p.setLibelle(pPizza.getLibelle());
			p.setCategorie(pPizza.getCategorie());
			p.setPrix(pPizza.getPrix());
		
			et.commit();
		}
	}

	@Override
	public void deletePizza(String pCode) 
	{
		// TODO Auto-generated method stub
		if(pizzaExists(pCode))
		{
			Pizza p = findPizzaByCode(pCode);
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			em.remove(p);
			
			et.commit();
		}
		
	}

	@Override
	public Pizza findPizzaByCode(String pCode) 
	{
		// TODO Auto-generated method stub
		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p where code = ?", Pizza.class);
		query.setParameter(1, pCode);
		Pizza p = query.getSingleResult();
		return p;
	
	}

	@Override
	public boolean pizzaExists(String pCode) 
	{
		// TODO Auto-generated method stub
		return findPizzaByCode(pCode)==null;
	}
}
