package it.univpm.Dati_Europa.Services;



import java.text.DateFormat.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import it.univpm.Dati_Europa.Download_Parsing.Download;
import it.univpm.Dati_Europa.Download_Parsing.Parsing;
import it.univpm.Dati_Europa.Model.MainCat;

@Service
public class Services {
	private String urlDataset = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=fifth-european-working-conditions-survey-2010";
	private Download d1;
	private Parsing p1;
	private ArrayList<MainCat> lista;
	private Metadata MD;
	private Stats statistiche;
	
	public Services()
	{
		d1 = new Download(urlDataset);
		String urlCsv;
		urlCsv = d1.Getlink();
		p1 = new Parsing(urlCsv);
		lista=p1.getData();
		MD=new Metadata(lista);
	}
	
	//restituisce i metadata
	public ArrayList<Map> getMetadata() 
	{
		return MD.getMetadata();
	}
	//restituisce i dati del csv
	public ArrayList<MainCat> Dati() 
	{
 		return this.lista;
	}
	//Stats (si inserisce la categoria,sottocategoria e paese e viene restituito il dato)
	public ArrayList <Object> getStats(String MainCat, String SubCat,String paese) 
	{
		ArrayList <Object> infoError = new ArrayList <Object> ();
		infoError.add("Error"); //nel caso ci siano errori nella dichiarazione dei parametri
		ArrayList <Object> info = new ArrayList <Object> ();
		info.add(MainCat);
		info.add(SubCat);
		//devo trovare in lista l'elemnto il cui nome è MainCat, il parametro passato alla funzione.
		Map<String, Double> map = new HashMap<>();
		double dato = 0;
		for(MainCat c : this.lista)
		{
			String nome = c.getNameCat();
			if(nome.equals(MainCat))
			{
				dato=c.getSubcat(SubCat).getDatoPaese(paese);
			}
		}
		map.put(paese, dato);
		info.add(map);
		if(info.isEmpty())
		{
			return infoError;
		}
		else return info;	
				
	  } 
	



}
	

