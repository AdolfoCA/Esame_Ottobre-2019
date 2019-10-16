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

/**
 * Classe concepita per rispondere alle richieste effettuate tramite postman, sfruttando tutte le classi precedentemente create
*/

@Service
public class Services {
	private String urlDataset = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=fifth-european-working-conditions-survey-2010"; //URL del dataset
	private Download d1;
	private Parsing p1;
	private ArrayList<MainCat> lista;
	private Metadata MD;
	private Stats statistiche;
	private ArrayList<Double>Dati;
	private Filter filtri;
	
	/**
	 * Costruttore della classe:
	 * qui si esegue il parsing dei dati del csv (in quanto viene lanciato il costruttore della relativa classe) e vengono estratti i dati ormai modellati
	*/
	
	public Services()
	{
		d1 = new Download(urlDataset);
		String urlCsv;
		urlCsv = d1.Getlink();
		p1 = new Parsing(urlCsv);
		lista=p1.getData();
		MD=new Metadata(lista);
		statistiche = new Stats();
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
	
	/**
	 * Metodo che restiruisce i metadati
	 * @return la lista dei metadati
	*/
	
	public ArrayList<Map> getMetadata() 
	{
		return MD.getMetadata();
	}
	
	/**
	 * Metodo che restituisce tutti i dati
	 * @retrun la lista di tutti i dati
	*/
	
	public ArrayList<MainCat> Dati() 
	{
 		return this.lista;
	}
	
	/**
	 * Metodo che restituisce un dato specifico
	 * @param MainCat, SubCat e paese, le coordinate del dato da restituire
	 * @return lo specifico dato
	*/
	
	public ArrayList <Object> getDato_paese(String MainCat, String SubCat,String paese) 
	{
		ArrayList <Object> info = new ArrayList <Object> ();
		//devo trovare in lista l'elemnto il cui nome è MainCat, il parametro passato alla funzione.
		Map<String, Double> map = new HashMap<>();
		double dato = -1;
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
						if(paese.equals(""))
						{
							ArrayList <Object> errore = new ArrayList <Object>();
							errore.add("Errore, paese non inserito");
							return errore;
						}
						info.add(MainCat);
						info.add(SubCat);
						dato=s.getDatoPaese(paese);
						if(dato==-1)
						{
							ArrayList <Object> errore = new ArrayList <Object>();
							errore.add("Errore, paese errato");
							return errore;
						}
						map.put(paese, dato);
						info.add(map);
					}
				}
			}
			
		}
		if(MainCat.equals("") || SubCat.equals("")|| info.isEmpty())
		{
			ArrayList <Object> errore = new ArrayList <Object>();
			errore.add("Errore, Macrocategoria o sottocategoria non inserita");
			return errore;
		}
		return info;
	}
	//metodo utile per controllare l'esistenza di una macro e sub categoria
	private boolean check(String a , String elemento)
	{
		if(a.contains(elemento))
				return true;
			else 
				return false;

	}
	
	/**
	 * Metodo che calcola le statistiche no
	 * @param MainCat e SubCat
	 * @return le statistiche relative alla data macrocategoria e sottocategoria 
	*/
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
	
	/**
	 * Metodo che restituisce le statistiche quando non inseriamo la sottocategoria tramite postman
	 * @param MainCat
	 * @return le statistiche relative a tutte le sottocategorie appartenenti alla macrocategoria indicata
	*/
	
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
	
	/**
	 * Metodo che restituisce i dati filtrati
	 * @param i nomi della macrocategoria e della sottocategoria, il filtro da applicare e i parametri su cui applicare il filtro
	 * @return i dati filtrati
	*/
	
	public Object Filter (String MainCat,String SubCat,String Filtro,Double[] param)
	{
		/*String MainCat =filtri.getMainCat();
		String SubCat = filtri.getSubCat();
		String Filtro = filtri.getFiltro();
		Double[] param= filtri.getParam();*/
		boolean flag1;
		boolean flag2;
		ArrayList <Double> dati=new ArrayList <Double>();
		ArrayList <Double> Ndati=new ArrayList <Double>();
		HashMap <String, Object> Paesi_filtrati=new HashMap <String,Object>();
		HashMap <Double, String> esitoIn=new HashMap <Double,String>();
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
						dati=getDatiPaesi(s);
						switch(Filtro)
						{
							case "Gt" :
							Ndati=filtri.Gt(dati, param[0]);
							for(Double d:Ndati)
							{
								Paesi_filtrati.put(s.getPaese(d), d);
						    }
						    Filter.put(MainCat+":"+SubCat, Paesi_filtrati);
						    break;
							case "Lt":
							Ndati=filtri.Lt(dati, param[0]);
						    for(Double d:Ndati)
						    {
						    	Paesi_filtrati.put(s.getPaese(d), d);
						    }
						    Filter.put(MainCat+":"+SubCat, Paesi_filtrati);
						    break;
							case"In":
							boolean in_nin;
							for(double p:param)
							{
								in_nin=filtri.In(dati,p);
								if(in_nin==true)
								{
									esitoIn.put(p,"il dato relativo al paese "+s.getPaese(p));
								}
								else
								{
									esitoIn.put(p,"il dato non è relativo a nessun paese nell'elenco");
								}
							}
							Filter.put(MainCat+":"+SubCat,esitoIn );
							break;
							default:
							Paesi_filtrati.put("Errore", "Filtro inesistente");
							return Paesi_filtrati;
					    }
					}
			   }
			}
		}
		if(dati.isEmpty())
		{
			Paesi_filtrati.put("Errore", "Macrocategoria o Sottocategoria inesistente");
			return Paesi_filtrati;
		}
		return Filter;
	}
	
	/**
	 * Metodo che restituisce i dati filtrati quando non e' specificata la sottocategoria
	 * @param il nome della macrocategoria, il filtro da applicare e i parametri su cui applicare il filtro
	 * @return i dati di tutta la macrocategoria filtrati
	*/
	
	public Object Filter (String MainCat, String filtro, Double[] param)
	{
		boolean flag1=false;
		ArrayList <Object> info=new ArrayList <Object>();
		for(MainCat c:lista)
		{
			flag1 = check(c.getNameCat(),MainCat);
			if(flag1==true)
			{
				for(SubCat s: c.getSottocategorie())
				{
					info.add(Filter (MainCat,s.getNameSub(),filtro,param));
				}
			}
		}
		return info;
	}
	
	/**
	 * Metodo che restituisce i dati filtrati quando non sono specificate la macrocategoria e la sottocategoria 
	 * @param il filtro da applicare e i parametri su cui applicare il filtro
	 * @return i dati di tutto il csv filtrati
	*/
	
	public Object Filter (String filtro, Double[] param)
	{
		ArrayList <Object> info=new ArrayList <Object>();
		for(MainCat c:lista)
		{
			info.add(Filter(c.getNameCat(),filtro,param));
		}
		return info;
	}
}