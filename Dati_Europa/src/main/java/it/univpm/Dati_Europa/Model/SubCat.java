package it.univpm.Dati_Europa.Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.univpm.Dati_Europa.Model.MainCat;

public class SubCat 
{
	private String nameSub;
	private Map <String, Double> paesi = new HashMap <String, Double> (35); //creo una HashMap di paesi
	
	public SubCat(String nameSub)
	{
		this.nameSub=nameSub;
		paesi.put("EU27", null);
		paesi.put("Belgium", null);
		paesi.put("Bulgaria", null);
		paesi.put("Czech Republic", null);
		paesi.put("Denmark", null);
		paesi.put("Germany", null);
		paesi.put("Estonia", null);
		paesi.put("Greece", null);
		paesi.put("Spain", null);
		paesi.put("France", null);
		paesi.put("Ireland", null);
		paesi.put("Italy", null);
		paesi.put("Cyprus", null);
		paesi.put("Latvia", null);
		paesi.put("Lithuania", null);
		paesi.put("Luxembourg", null);  //ho scritto solo 16 paesi.
	}

	public String getNameSub() {
		return nameSub;
	}

	public void setNameSub(String nameSub) {
		this.nameSub = nameSub;
	}
	
	public void setData(String paese, double dato)
	{
		paesi.replace(paese, dato);
	}

}
