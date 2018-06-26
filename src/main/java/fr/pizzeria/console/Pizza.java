package fr.pizzeria.console;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.utils.toString;

/**
 * Entry point for this project.
 *
 * @author MT
 */

public class Pizza 
{
	 // **************************************************
    // Fields
    // **************************************************
	
	static int currentId = 0;

	int id;
	
	@toString(separateur="->", upperCase=true)
	private String code;
	
	@toString(upperCase=false)
	private String libelle;
	
	private double prix;
	
	CategoriePizza categoriePizza;
	
	 // **************************************************
    // Constructors
    // **************************************************
	
	public Pizza()
	{
		this.id = currentId;
		this.code = "Inconnu";
		this.libelle = "Inconnu";
		this.prix = 0.0d;
		
		this.categoriePizza = null;
		
		currentId++;
	}
	
	public Pizza(String pCode, String pLibelle, CategoriePizza pCategorie, double pPrix)
	{
		this();
		this.code = pCode;
		this.libelle = pLibelle;
		this.prix = pPrix;
		
		this.categoriePizza = pCategorie;

	}
	
	public Pizza(int pId, String pCode, String pLibelle, CategoriePizza pCategorie, double pPrix)
	{
		this.id = pId;
		this.code = pCode;
		this.libelle = pLibelle;
		this.prix = pPrix;
		
		this.categoriePizza = pCategorie;

		
		currentId = pId + 1;
	}
	
	 // **************************************************
    // Setters
    // **************************************************
	
	public void setId(int pId)
	{
		this.id = pId;
	}
	
	public void setCode(String pCode)
	{
		this.code = pCode;
	}
	
	public void setLibelle(String pLibelle)
	{
		this.libelle = pLibelle;
	}
	
	public void setPrix(double pPrix)
	{
		this.prix = pPrix;
	}
	
	public void setCategorie(String pNom)
	{
		this.categoriePizza = CategoriePizza.valueOf(pNom);
	}
	
	 // **************************************************
    // Getters
    // **************************************************

	public static int getLastId()
	{
		return currentId;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public String getCode()
	{
		return this.code;
	}
	
	public String getLibelle()
	{
		return this.libelle;
	}
	
	public double getPrix()
	{
		return this.prix;
	}
	
	public String getCategorie()
	{
		return this.categoriePizza.getLibelle();
	}
	
	 // **************************************************
    // Public methods
    // **************************************************
	
	public String afficherPizza()
	{
		String str = new String(this.code+" -> "+this.libelle
								+" ("+this.prix+" €)"
								+" ("+this.categoriePizza+")");
		return str;
	}
	
	@Override
	public String toString()
	{
		String str = new String("La pizza "+this.libelle
								+" a pour code "+this.code
								+" , c'est une pizza de catégorie "+this.categoriePizza
								+", son prix est de "+this.prix
								+"€ et son ID est "+this.id+".");
		
		return "";
	}

	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Pizza))
		{
			return false;
		}
		
		Pizza other = (Pizza) obj;
		return this.code.equals(other.getCode());
	}

}
