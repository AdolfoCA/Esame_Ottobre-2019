package it.univpm.Dati_Europa.Services;



import java.util.ArrayList;
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
	
	public Services()
	{
		d1 = new Download(urlDataset);
		String urlCsv;
		urlCsv = d1.Getlink();
		p1 = new Parsing(urlCsv);
		lista=p1.getData();
		//MD=new Metadata(lista);
	}
	
	//restituisce i metadata
	public List<Map> getMetadata() 
	{
		return MD.getMetadata();
	}
	//restituisce i dati del csv
	public ArrayList<MainCat> Dati() 
	{
 		return this.lista;
	}
	
}
