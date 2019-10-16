package it.univpm.Dati_Europa.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.Dati_Europa.Model.MainCat;
import it.univpm.Dati_Europa.Model.SubCat;
import it.univpm.Dati_Europa.Services.Filter;
import it.univpm.Dati_Europa.Services.Services;

/**
 * Classe concepita per gestire le richieste effettuate tramite postman
*/

@RestController
public class Europa_Data_Controller
{
	@Autowired
	private Services servizio;
	
	/**
	 * Costruttore della classe
	 * grazie all'annotazione @Autowired viene lanciato automaticamente all'avvio di Spring e esegue il collegamento a Services
	*/
	
	public Europa_Data_Controller(Services servizio)
	{
		this.servizio = servizio;
	}
	
	/**
	 * Metodi per la comunicazione con il client che gestiscono le richieste get e post
	*/
	/**
	 * Metodo che gestisce la richiesta get alla rotta /Dati con lo scopo di restituire tutto il csv
	 * @return la lista di tutti i dati del csv
	*/
	
	@GetMapping("/Dati")
	public ArrayList <MainCat> getAllData()
	{
		return servizio.Dati();
	}
	
	/**
	 * Metodo che gestisce la richiesta get alla rotta /Metadati con lo scopo di restituire i metadati
	 * @retrun la lista dei metadati
	*/
	
	@GetMapping("/Metadati")
	public ArrayList<Map> getMetadata()
	{
		return servizio.getMetadata();
	}
	
	/**
	 * Metodo che gestisce la richiesta get alla rotta /Statistiche con lo scopo di restituire le statistiche di una porzione dei dati o di tutti
	 * @param MainCat e SubCat, tramite RequestParam vengono inseriti in postman i nomi della sottocategoria di valori di cui effettuare le statistiche e della relativa macrocategoria
	 * @return Stats, la lista che contiene per ciascuna sottocategoria tutte le statistiche 
	*/
	
	@GetMapping("/Statistiche")
	public List<Map> statistiche(@RequestParam(value = "MainCat", defaultValue="") String MainCat, @RequestParam("SubCat") String SubCat)
	{
		List<Map> Stats= new ArrayList<>();
		if(!(MainCat.equals("")))
		{
			if(!(SubCat.equals("")))
			{
				Stats.add(servizio.Statistiche(MainCat, SubCat));
			}
			 else
			{
				Stats.add(servizio.Statistiche(MainCat));
			}
		}
		else
		{
			HashMap<String,Double> dati=new HashMap<String,Double>();
			HashMap<String,HashMap> dati_sottocategorie=new HashMap<String,HashMap>();
			for(MainCat M : servizio.Dati())
			{
				for(SubCat S : M.getSottocategorie())
				{
					dati=servizio.Statistiche(M.getNameCat(), S.getNameSub());
					dati_sottocategorie.put(M.getNameCat()+":"+S.getNameSub(), dati);
				}
				Stats.add(dati_sottocategorie);
			}
		}
		return Stats;
	}
	
	/**
	 * Metodo che gestisce la richiesta get alla rotta /DatoPaese con lo scopo di restituire uno specifico valore del csv
	 * @param MainCat, SubCat e Paese , il nome del paese tramite RequestParam
	 * @return il valore corrispondente alle coordinate fornite
	*/
	
	@GetMapping("/DatoPaese")
	public ArrayList <Object> DatoPaese(@RequestParam(value = "MainCat", defaultValue="") String MainCat, @RequestParam("SubCat") String SubCat, @RequestParam("Paese") String Paese)
	{
		return servizio.getDato_paese(MainCat, SubCat, Paese);
	}
	
	/**
	 * Metodo che gestisce la richiesta post alla rotta /Filtri con lo scopo di restituire una porzione dei dati del csv
	 * @param filtro, un oggetto della classe Filter i cui attributi verranno automaticamente settati sulla base di cio che verra' inserito nel body tramite RequestBody
	 * gli attributi in questione sono MainCat , SubCat , Filtro (l'operatore logico da applicare ai dati), Param (un array di valori sulla base dei quali applicare i filtri)
	 * @return la lista di valori del csv (o solo di una parte di esso) filtrata con uno degli operatori consentiti 
	*/
	
	@PostMapping("/Filtri")
	public Object Filtri(@RequestBody Filter filtro)
	{
		String MainCat= filtro.getMainCat();
		String SubCat= filtro.getSubCat();
		String Filtro= filtro.getFiltro();
		Double[] Param= filtro.getParam();
		if(MainCat.equals(""))
		{
			return servizio.Filter(Filtro, Param);
		}
		if(SubCat.equals(""))
		{
			return servizio.Filter(MainCat, Filtro, Param);
		}
		return servizio.Filter(MainCat, SubCat, Filtro, Param);
	}
}