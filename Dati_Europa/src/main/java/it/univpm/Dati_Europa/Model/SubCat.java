//una sottocategoria contiene una mappa con i dati dei vari paesi;
package it.univpm.Dati_Europa.Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SubCat 
{
	private String nameSub="";
	private ArrayList<String> nPaesi;
	private ArrayList<Double> datiPaesi;
	
	//private Map <String, Double> paesi = new HashMap <String, Double> (35); //creo una HashMap di paesi
	
	public SubCat()
	{
		nPaesi.add("EU27");
		nPaesi.add("Belgium");
		nPaesi.add("Bulgaria");
		/*nPaesi.add("EU27");
		nPaesi.add("EU27");
		nPaesi.add("EU27");
		nPaesi.add("EU27");
		nPaesi.add("EU27");
		nPaesi.add("EU27");
		nPaesi.add("EU27");
		nPaesi.add("EU27");
		nPaesi.add("EU27");
		nPaesi.add("EU27");*/
		
		/*paesi.put("EU27", null);
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
		paesi.put("Luxembourg", null);  //ho scritto solo 16 paesi.*/
	}

	public String getNameSub() {
		return nameSub;
	}

	public void setnameSub(String nameSub) {
		this.nameSub = nameSub;
	}
	
	public void setData(ArrayList <Double> data)
	{
		
		
		
		
		
		
	}
	
	public Map<String, Double> getPaesi() //restituisce la mappa con chiave e valore
	{
		return paesi;
	}
}
