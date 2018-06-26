package fr.pizzeria.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.pizzeria.console.Pizza;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;

public class PizzaDaoBase implements IPizzaDao
{
	String nameDriver = "org.mariadb.jdbc.Driver";
	String nameBDD = "jdbc:mariadb://localhost:3306/pizzeria";
			
	ResourceBundle monFichier = ResourceBundle.getBundle("jdbc");
	String url = monFichier.getString("URL");
	String user = monFichier.getString("USER");
	String pwd = monFichier.getString("PASSWORD");
	
	Connection myConnection = null;
	PreparedStatement statement = null;
	ResultSet resultatQuery = null;
	int resultatUpdate = -1;
	boolean resultat = false;
	
	
	public void connecterBase()
	{
		try
		{
			Class.forName(nameDriver);
			myConnection = DriverManager.getConnection(nameBDD, user, pwd);
		}
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			deconnecterBase();
			e.printStackTrace();
			
		}
		catch(SQLException e)
		{
			deconnecterBase();
			e.printStackTrace();
		}
	}
	
	public void deconnecterBase()
	{
		if(resultatQuery != null)
		{
			try 
			{
				resultatQuery.close();
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		if(statement != null)
		{
			try 
			{
				statement.close();
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(myConnection != null)
		{
			try 
			{
				myConnection.close();
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Pizza> findAllPizzas() throws StockageException 
	{
		// TODO Auto-generated method stub
		connecterBase();
		
		List<Pizza> tempList = new ArrayList<>();
		
		try 
		{
			statement = myConnection.prepareStatement("SELECT * FROM Pizza");
			resultatQuery = statement.executeQuery();
			
			while(resultatQuery.next()) 
			{
				Pizza tempPizza = null;
				
				int id = resultatQuery.getInt("ID");
				String code = resultatQuery.getString("code");
				String libelle = resultatQuery.getString("libelle");
				double prix = resultatQuery.getDouble("prix");
				CategoriePizza categorie = CategoriePizza.getCategorieByLibelle(resultatQuery.getString("categorie"));
				
				tempPizza = new Pizza(id,code,libelle,categorie,prix);
				tempList.add(tempPizza);
			}
			
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		finally
		{
			deconnecterBase();
		}
		
		return tempList;
	}

	@Override
	public void saveNewPizza(Pizza pPizza) 
	{
		// TODO Auto-generated method stub
		if(!pizzaExists(pPizza.getCode()))
		{
			connecterBase();
			
			
			try 
			{
				statement = myConnection.prepareStatement("INSERT INTO Pizza(code,libelle,categorie,prix)"
															+ "VALUES (?,?,?,?);");
				
				statement.setString(1, pPizza.getCode());
				statement.setString(2, pPizza.getLibelle());
				statement.setString(3, pPizza.getCategorie());
				statement.setDouble(4, pPizza.getPrix());
				
				
				
				resultatUpdate = statement.executeUpdate();
				
				
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				try 
				{
					myConnection.rollback();
				} catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			finally
			{
				try 
				{
					myConnection.commit();
				} catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				deconnecterBase();
			}
		}
		
	}

	@Override
	public void updatePizza(String pCode, Pizza pPizza) 
	{
		// TODO Auto-generated method stub
		connecterBase();
		
		try 
		{
			statement = myConnection.prepareStatement("UPDATE Pizza SET code = ?, libelle = ?, categorie = ?, prix = ?");

			statement.setString(1, pPizza.getCode());
			statement.setString(2, pPizza.getLibelle());
			statement.setString(3, pPizza.getCategorie());
			statement.setDouble(4, pPizza.getPrix());
			
			resultatUpdate = statement.executeUpdate();
			
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			try 
			{
				myConnection.rollback();
			} catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				myConnection.commit();
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			deconnecterBase();
		}
		
		
	}

	@Override
	public void deletePizza(String pCode) 
	{
		// TODO Auto-generated method stub
		connecterBase();
		
		try 
		{
			statement = myConnection.prepareStatement("DELETE FROM Pizza WHERE code = ?");
			statement.setString(1, pCode);
			
			resultatUpdate = statement.executeUpdate();
			
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			try 
			{
				myConnection.rollback();
			} catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				myConnection.commit();
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			deconnecterBase();
		}
	}

	@Override
	public Pizza findPizzaByCode(String pCode) 
	{
		// TODO Auto-generated method stub
		connecterBase();
		
		Pizza tempPizza = null;
		
		try 
		{
			statement = myConnection.prepareStatement("SELECT * FROM Pizza WHERE code = ?");
			statement.setString(1, pCode);
			
			resultatQuery = statement.executeQuery();
			
			
			while(resultatQuery.next()) 
			{
				int id = resultatQuery.getInt("ID");
				String code = resultatQuery.getString("code");
				String libelle = resultatQuery.getString("libelle");
				double prix = resultatQuery.getDouble("prix");
				String categorie = resultatQuery.getString("categorie").toUpperCase();
				
				tempPizza = new Pizza(id,code,libelle,CategoriePizza.getCategorieByLibelle(categorie),prix);
			}
			
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		finally
		{
			deconnecterBase();
		}
		
		
		return tempPizza;
	}

	@Override
	public boolean pizzaExists(String pCode) 
	{
		// TODO Auto-generated method stub
			return findPizzaByCode(pCode) != null;
	}
	
	
}
