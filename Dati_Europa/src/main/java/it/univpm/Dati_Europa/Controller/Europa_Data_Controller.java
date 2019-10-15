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

@RestController
public class Europa_Data_Controller
{
	@Autowired  //il controllore dipende da servizio, stiamo dunque iniettando una dipendenza
	private Services servizio;
	//Costruttore che con l'annotazione @Autowired viene lanciato automaticamente all'avvio da Spring e esegue il collegamento al Services
	public Europa_Data_Controller(Services servizio)
	{
		this.servizio = servizio;
	}
	//Metodi per la comunicazione con il client che gestiscono le richieste GET e POST
	//Metodo che gestisce la richiesta GET alla rotta "/Dati", restituisce l'intero dataset
	//@GetMapping, con una chiamata di tipo GET otteniamo l'accesso ad una risorsa specifica
	//@RequestParam, permette di passare un parametro in ingresso
	@GetMapping("/Dati")
	public ArrayList <MainCat> getAllData()
	{
		return servizio.Dati();
	}
	//Metodo che gestisce la richiesta GET alla rotta "/Metadati", restituisce i metadati
	//@return lista dei metadata
	@GetMapping("/Metadati")
	public ArrayList<Map> getMetadata()
	{
		return servizio.getMetadata();
	}
	/**

     * Metodo che gestisce la richiesta GET alla rotta "/stats" e che restituisce le statistiche

     * 

     * @param fieldName nome del campo del quale si vogliono calcolare le statistiche

     * @return lista delle statistiche

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
	
	@GetMapping("/DatoPaese")
	public ArrayList <Object> DatoPaese(@RequestParam(value = "MainCat", defaultValue="") String MainCat, @RequestParam("SubCat") String SubCat, @RequestParam("Paese") String Paese)
	{
		return servizio.getDato_paese(MainCat, SubCat, Paese);
	}
	
	@PostMapping("/Filtri")
	public ArrayList<Map> Filtri(@RequestBody Filter filtro)
	{
		return servizio.Filter(filtro.getMainCat(), filtro.getSubCat(), filtro.getFiltro(), filtro.getParam());
	}
   /* public List<Map> getStats(@RequestParam(value = "field", defaultValue = "") String fieldName) {

    	Field[] fields = Erasmus.class.getDeclaredFields();

    	List<Map> list = new ArrayList<>();

    	if(fieldName.equals("")) {  // se non viene specificato il campo calcola le statistiche di ogni attributo

    		for(int i=0; i < fields.length; i++) {

    			list.add(service.getStats(fields[i].getName()));		

    		}

    		return list;

    	}

    	else {  // altrimenti calcola le statistiche del solo campo specificato

    		list.add(service.getStats(fieldName));

    		return list;

    	}

	}*/

    

    /**

     * Metodo che gestisce la richiesta POST alla rotta "/filter" e che restituisce i dati filtrati 

     * 

     * @param req oggetto di tipo Filter al quale vengono passati i valori del body tramite una chiamata POST

     * @return lista dei dati opportunamente filtrati

     */

   /* @PostMapping("/data")

    public List getFilterData(@RequestBody Filter req) {

    	return service.getFilterData(req.getFieldName(), req.getOp(), req.getRif());

    }*/

    

    /**

     * Metodo che restituisce la richiesta POST alla rotta "/stats" e che restituisce le statistiche dei dati filtrati se non si specifica

     * il nome del campo, oppure, se lo si specifica, restituisce le statistiche dei dati filtrati solo del campo desiderato

     * 

     * @param fieldName nome del campo del quale si vogliono calcolare le statistiche

     * @param req oggetto di tipo Filter al quale vengono passati i valori del body tramite una chiamata POST

     * @return lista delle statistiche dei dati filtrati

     */

    /*@PostMapping("/stats")

    public List<Map> getFilterStats(@RequestParam(value = "field", defaultValue = "") String fieldName, @RequestBody Filter req) {

    	List<Map> listaStats = new ArrayList<>();

    	List listaFiltrata = service.getFilterData(req.getFieldName(), req.getOp(), req.getRif());

    	Field[] fields = Erasmus.class.getDeclaredFields();

    	// se non specifico il nome del campo, mi restituisce le statistiche di tutti gli attributi Erasmus dei dati filtrati secondo i parametri passati nel body

    	if(fieldName.equals("")) {

    		for(int i=0; i < fields.length; i++) {

    			listaStats.add(service.getStats(fields[i].getName(), listaFiltrata));		

    		}

    		return listaStats;

    	}

    	else {  // altrimenti restituisce solo quelli del parametro specificato

    		listaStats.add(service.getStats(fieldName, listaFiltrata));

    	}

		return listaStats;

    }*/
}
