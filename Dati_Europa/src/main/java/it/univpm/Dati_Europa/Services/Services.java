package it.univpm.Dati_Europa.Services;

import org.springframework.stereotype.Service;

import it.univpm.Dati_Europa.Download_Parsing.Download;
import it.univpm.Dati_Europa.Download_Parsing.Parsing;

@Service
public class Services {
	private String urlDataset = "http://data.europa.eu/euodp/data/api/3/action/package_show?id=fifth-european-working-conditions-survey-2010";
	private String urlCsv;
	private Download d1;
	private Parsing p1;
	public Services()
	{
		d1 = new Download(urlDataset);
		urlCsv = d1.Getlink();
		p1 = new Parsing(urlCsv);
	}
}
