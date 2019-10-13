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
import it.univpm.Dati_Europa.Model.SubCat;

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
		statistiche = new Stats();
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
	/*public ArrayList <Object> getDato_paese(String MainCat, String SubCat,String paese) 
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
		if(map.get(paese)==0) //se quindi non esiste il paese o la categoria o la sottocategoria
		{
			return infoError;
		}
		else return info;	
	}
	//metodo che restituisce l'array di dati relativi al paese cercato
	public HashMap ....... getDati_paese (String paese)
	{
		ArrayList <Object> infoError = new ArrayList <Object> ();
		infoError.add("Error");//nel caso ci siano errori nella dichiarazione dei parametri
		
		ArrayList <String> nomiMainCat =new ArrayList <String>();
		ArrayList <Double> dati = new ArrayList <Double> ();
		
		
		HashMap <String, ArrayList<String>> mapNomi = new HashMap <String, ArrayList<String>>();//contiene il nome della macrocategoria e le relative sottocategorie
		HashMap <HashMap,Double>  mapInfo = new HashMap <HashMap,Double>();//contiene i dati
		
		for(int i=0;i<lista.size();i++)
		{
			nomiMainCat.add(lista.get(i).getNameCat());
			ArrayList <String> nomiSubCat;
			for(int j=0;j<lista.get(i).getSottocategorie().size();j++)
			{
				nomiSubCat.add(lista.get(i).getSottocategorie().get(j).getNameSub());
			    //si viene a formare un array con i nomi delle sottocategoria rispetto a ciascuna categoria
				//ora devo salvare i dati dei paesi relativi a sottocategorie
			}
			mapNomi.put(nomiMainCat.get(i),nomiSubCat);
		}*/
		
		public boolean check(String a , String elemento)
		{
			if(a.equals(elemento))
					return true;
				else 
					return false;
			
		}
		public HashMap <String, Double>  Statistiche (String MainCat,String Subcat)
		{
			boolean flag1;
			boolean flag2;
			ArrayList <Double> dati=new ArrayList <Double>();
			HashMap <String, Double> stats=new HashMap <String,Double>();
			for(MainCat c:lista)
			{
				flag1=check(c.getNameCat(),MainCat);
				if(flag1==true)
				{
					for(SubCat s: c.getSottocategorie())
					{
						flag2=check(s.getNameSub(),Subcat);
						if(flag2==true)
						{
							dati=s.getDatiPaesi();
							stats.put("Media",statistiche.avg(dati));
							stats.put("Somma",statistiche.sum(dati));
							double m=statistiche.max(dati);
							int i=(s.getDatiPaesi()).indexOf(m);
							stats.put((s.getnPaesi()).get(i),m);
							m=statistiche.min(dati);
							i=(s.getDatiPaesi()).indexOf(m);
							stats.put((s.getnPaesi()).get(i),m);
							stats.put("Deviazione Standard",statistiche.DevStd(dati));
						}
					}
				}
			}
			if(dati.isEmpty())
			{
				stats.put("Error",(double) 0);
			}
			return stats;
		}
		public HashMap<String,HashMap>  Statistiche (String MainCat)
		{
			HashMap <String,HashMap> dati = null;
			HashMap <String,Double> stats = new HashMap<String,Double>();
			boolean flag1;
			for(MainCat c:lista)
			{
				flag1=check(c.getNameCat(),MainCat);
				if(flag1==true)
				{
					for(SubCat s: c.getSottocategorie())
					{
						
						stats=Statistiche(MainCat,s.getNameSub());
						dati.put(s.getNameSub(), stats);
					}
				}
			}
			if(dati.isEmpty())
			{
				stats.put("Error",(double) 0);
				dati.put (MainCat,stats );
			}
			return dati;
		
	    }
		
			
		
		
		
}
	


	

