package it.univpm.Dati_Europa.Services;

import it.univpm.Dati_Europa.Model.MainCat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe concepita per la restituzione dei metadati ovvero le macrocategorie
*/

public class Metadata 
{
	private ArrayList<Map> metadata = new ArrayList<Map>();
	
	/**
	 * Costruttore della classe:
	 * genera un'array e lo riempie con i nomi delle macrocategorie ed il rispettivo tipo
	*/
	
	public Metadata(ArrayList<MainCat> lista) 
	{
        for ( MainCat c : lista) 
        {
        	Map<String, String> map = new HashMap<String,String>();
            //andiamo ad inserire le coppie chiave/valore
            map.put("alias", c.getNameCat());
            map.put("sourceField", c.getNameCat());//nome del campo in csv
            map.put("type", "double");
            metadata.add(map);
        }
	}
	
	/**
	 * Metodi per la restituzione dei metadati
	 * @return la lista dei metadati
	*/
	
	public ArrayList<Map> getMetadata() 
	{
		return metadata;
	}
}
