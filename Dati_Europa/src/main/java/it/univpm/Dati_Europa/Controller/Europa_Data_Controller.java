package it.univpm.Dati_Europa.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public List getAllData()
	{
		return servizio.getData();
	}
	//Metodo che gestisce la richiesta GET alla rotta "/Metadati", restituisce i metadati
	//@return lista dei metadata
	@GetMapping("/Metadati")
	public List<Map> getMetadata()
	{
		return servizio.getMetadata();
	}
}
