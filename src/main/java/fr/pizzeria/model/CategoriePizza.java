package fr.pizzeria.model;

public enum CategoriePizza 
{
	VIANDE("Viande"),
	POISSON("Poisson"),
	VEGETARIENNE("Vegetarienne");
	
	/*************************************************************************************/
	
	private String libelle;

	/*************************************************************************************/
	
	CategoriePizza(String pLibelle)
	{
		this.libelle = pLibelle;
	}
	
	/*************************************************************************************/
	
	
	public static CategoriePizza getCategorieByLibelle(String pLibelle)
	{
		CategoriePizza[] tabCategorie = CategoriePizza.values();
		
		for (CategoriePizza c : tabCategorie)
		{
			if(c.getLibelle().equals(pLibelle))
			{
				return c;
			}
		}
		return null;
	}
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
