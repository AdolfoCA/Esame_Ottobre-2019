package it.univpm.Dati_Europa.Services;
import it.univpm.Dati_Europa.Model.MainCat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Classe utile per la gestione dei matadati quando vengono richiesti
 */

public class Metadata 
{
private ArrayList<Map> metadata = new ArrayList<>();
	
	public Metadata(List<MainCat> lista) 
	{

        for ( MainCat c : lista) 
        {
        	Map<String, String> map = new HashMap<>();
            //andiamo ad inserire le coppie chiave/valore
            map.put("alias", c.getNameCat());
            map.put("sourceField", c.getNameCat());//nome del campo in csv
            map.put("type", "double");
            metadata.add(map);
        }
	}
	/**
	 * Metodo che ritorna la lista di mappe contenente i metadati
	 * 
	 * @return lista dei metadati
	 */
	
	public List<Map> getMetadata() 
	 {
		return metadata;
	 }
}
