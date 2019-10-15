package it.univpm.Dati_Europa.Services;



import java.text.DateFormat.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	private ArrayList<Double>Dati;
	private Filter filtri;
	
	public Services()
	{
		d1 = new Download(urlDataset);
		String urlCsv;
		urlCsv = d1.Getlink();
		p1 = new Parsing(urlCsv);
		lista=p1.getData();
		MD=new Metadata(lista);
		statistiche = new Stats();
		filtri=new Filter();
		Dati=new ArrayList <Double>();
	}
	//resituisce l'array con tutti i dati dei vari paesi rispetto ad una sottocategoria
	private ArrayList <Double> getDatiPaesi(SubCat s)
	{
		ArrayList<Double> DatiPaesi= new ArrayList<Double>();
		Set<String> paesi;
		paesi=s.getDati().keySet();//un set con i nomi dei paesi
		for(String p : paesi)
		{
			DatiPaesi.add(s.getDati().get(p));
		}
		return DatiPaesi;
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
	//Si inserisce la categoria,sottocategoria e paese e viene restituito il dato
	public ArrayList <Object> getDato_paese(String MainCat, String SubCat,String paese) 
	{
		ArrayList <Object> infoError = new ArrayList <Object> ();
		infoError.add("Error"); //nel caso ci siano errori nella dichiarazione dei parametri
		ArrayList <Object> info = new ArrayList <Object> ();
		//devo trovare in lista l'elemnto il cui nome è MainCat, il parametro passato alla funzione.
		Map<String, Double> map = new HashMap<>();
		double dato = 0;
		for(MainCat c:lista)
		{
			boolean flag1 = check(c.getNameCat(),MainCat);
			if(flag1==true)
			{
				for(SubCat s: c.getSottocategorie())
				{
					boolean flag2 = check(s.getNameSub(),SubCat);
					if(flag2==true)
					{
						info.add(MainCat);
						info.add(SubCat);
						dato=s.getDatoPaese(paese);
						if(dato==0)
						{
							return infoError;
						}
						map.put(paese, dato);
						info.add(map);
					}
				}
			}
		}
		return info;
					
		
	}
	
		//metodo utile per controllare l'esistenza di una macro e sub categoria
		public boolean check(String a , String elemento)
		{
			if(a.contains(elemento))
					return true;
				else 
					return false;
			
		}
		//calcola le stastistiche rispetto ad una sottocategoria di una macrocategoria
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
							dati=getDatiPaesi(s);
							stats.put("Media",statistiche.avg(dati));
							stats.put("Somma",statistiche.sum(dati));
							double m=statistiche.max(dati);
							stats.put("max:"+s.getPaese(m),m);
							m=statistiche.min(dati);
							stats.put("min:"+s.getPaese(m),m);
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
		//calcola le statistiche rispetto ad una macrocategoria
		public HashMap<String,HashMap<String,Double>>  Statistiche (String MainCat)
		{
			HashMap <String,HashMap<String,Double>> dati = new HashMap <String,HashMap<String,Double>>();
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
		//Filtri
		public Object Filter (String MainCat,String SubCat, String filtro, double [] param)
		{
			String[] nomi_filtri= {"Gt", "Lt", "In"};
			boolean flag1;
			boolean flag2;
			boolean flag3;
			ArrayList <Double> dati=new ArrayList <Double>();
			HashMap <String, Object> Paesi_filtrati=new HashMap <String,Object>();
			HashMap <String, HashMap> Filter=new HashMap <String,HashMap>();
			for(MainCat c:lista)
			{
				flag1=check(c.getNameCat(),MainCat);
				if(flag1==true)
				{
					for(SubCat s: c.getSottocategorie())
					{
						flag2=check(s.getNameSub(),SubCat);
						if(flag2==true)
						{
							for(String f: nomi_filtri)
							{
								
									dati=getDatiPaesi(s);
									switch(f)
									{
									case "Gt" :
									{
										dati=filtri.Gt(dati, param[0]);
									    for(Double d:dati)
									    {
									    	Paesi_filtrati.put(s.getPaese(d), d);
									    }
									    Filter.put(MainCat+":"+SubCat, Paesi_filtrati);
									    break;
									}
									case "Lt":
									{
										dati=filtri.Lt(dati, param[0]);
									    for(Double d:dati)
									    {
									    	Paesi_filtrati.put(s.getPaese(d), d);
									    }
									    Filter.put(MainCat+":"+SubCat, Paesi_filtrati);
									    break;
									}
									case"In":
									{
										boolean in_nin;
										for(double p:param)
										{
											in_nin=filtri.In(dati,p);
											if(in_nin==true)
											{
												Paesi_filtrati.put(s.getPaese(p), in_nin +" parametro: "+p);
											}
											else
											{
												Paesi_filtrati.put("Paese inesistente",in_nin +" parametro: "+p);
											}
										}
										Filter.put(MainCat+":"+SubCat, Paesi_filtrati);
										break;
									}
										default:
										{
											Paesi_filtrati.put("Errore", "Filtro inesistente");
											return Paesi_filtrati;
										}
									
								    }
									
							}
						}
					}
				}
			}
			return Filter;
		}
		
}
	


	

