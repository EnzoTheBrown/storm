package stormTP.core;

import java.io.StringReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

/**
 Classe regroupant les fonctionnalités nécessaires au traitement des flux des coureurs
*/
public class TortoiseManager {
	
	public static final String CONST = "Constant";
	public static final String PROG = "En progression";
	public static final String REGR = "En régression";
	
	String nomsBinome ="";
	int dossard = -1;
	
	public TortoiseManager(int dossard, String nomsBinome){
		this.nomsBinome = nomsBinome;
		this.dossard = dossard;
	}
	
	
	/**
	 * Permet de filtrer les informations concernant votre coureur
	 * @param input : objet JSON contenant les observations de la course courante 
	 * @return un coureur
	 */
    public Runner filter(String input){
        String firstAttribute = "tortoises";
        JsonReader parseurJ = Json.createReader(new StringReader(input));
        JsonObject json_tuple = parseurJ.readObject();
        String singleTuple = json_tuple.getJsonArray(firstAttribute).get(this.dossard).toString();
        JsonReader parseurT = Json.createReader(new StringReader(singleTuple));
        JsonObject json_player = parseurT.readObject();

        Runner tortoise = new Runner();
        tortoise.setId(json_player.getInt("id"));
        tortoise.setTop(json_player.getInt("top"));
        tortoise.setNom(this.nomsBinome);
        tortoise.setNbDerriere(json_player.getInt("nbDerriere"));
        tortoise.setNbDevant(json_player.getInt("nbDevant"));
        tortoise.setTotal(json_player.getInt("total"));
        tortoise.setPosition(json_player.getInt("position"));

        return tortoise;
    }

	private String findExAequo(int nbDevant, int nbDerriere, int total){
		if(nbDevant + nbDerriere + 1 < total)
			return "ex";
		return "";
	}
	/**
	 * Permet de calculer le rang de votre coureur
	 * @param id : identifiant du coureur
	 * @param top : numéro d'observation
	 * @param nom : nom du coureur
	 * @param nbDevant : nombre de coureur devant le coureur courant dans le classement
	 * @param nbDerriere : nombre de coureur derrière le coureur courant dans le classement
	 * @param total : nombre total de coureurs en lice
	 * @return un coureur
	 */
	public Runner computeRank(long id, long top, String nom, int nbDevant, int nbDerriere, int total){

		Runner tortoise = new Runner();
		tortoise.setId(id);
		tortoise.setTop(top);
		tortoise.setNom(nom);
		tortoise.setNbDerriere(nbDerriere);
		tortoise.setNbDevant(nbDevant);
		tortoise.setTotal(total);
		tortoise.setPosition(5);

		String rang = Integer.toString(nbDevant + 1) + findExAequo(nbDevant, nbDerriere, total);
		tortoise.setRang(rang);
		return tortoise;
	}

	/**
	 * Permet de calculer les points bonus d'un coureur
	 * @param rang : rang du coureur
	 * @param total : nombre total de coureurs en lice
	 * @return nombre de points gagnés par le coureur
	 */

    public static int computePoints(String rang,  int total){
        return total - Integer.valueOf(rang.replace("ex", "")) - 1;
    }
	

	/**
	 * Permet de calculer la vitesse d'un coureur
	 * @param topInit : numéro d'observation initial
	 * @param topFin : numéro d'observation final
	 * @param posInit : position initiale du coureur sur la piste à l'observation initiale
	 * @param posFin : position finiale du coureur sur la piste à l'observation finale
	 * @return la vitesse du coueur (en nombre moyen de cellules par top d'observation) arrondie à 2 chiffres après la virgule
	 */
	public static double computeSpeed(long topInit, long topFin, int posInit, int posFin){
		if (posInit - posFin == 0)
			return 0;
		return Math.abs(posInit - posFin)/(topFin - topInit);
	}

	
	/**
	 * Permet de calculer le rang moyen d'un coureur
	 * @param rangs : tableau des différentes valeurs de rang observées pour un coureur
	 * @return la moyenne des rangs
	 */
	public static int giveAverageRank(String[] rangs){
				
		
		int rang = 0;
		
		//@TODO
		
		return rang;
		
	}
	

	/**
	 * Permet de calculer l'évolution du rang moyen d'une tortue
	 * @param cavg : le rang moyen courant
	 * @param pavg : le rang moyen précédent
	 * @return la chaine de caractères correspondante à l'évolution du rang moyen 
	 */
	public static String giveRankEvolution(int cavg, int pavg){
				
		String evol = "";
		
		//@TODO
		
		
		return evol;
		
		
	}
	
	
	/**
	 * Permet de calculer le podium
	 * @param input : objet JSON correspondant aux observations de la course
	 * @return objet JSON correspondant au podium
	 */
	public static String getPodium(String input){
				
		JsonObjectBuilder r =  Json.createObjectBuilder();

		//@TODO
		
		
        
		return r.build().toString();
		
						
		
	}

	
	/**
	 * S'assure que le podium est complet et réaffecte le classement en fonction des ex aequo.
	 * @param* de coureurs classé par leur rang
	 * @return le podium sous forme de liste de liste de coureurs
	 */
	private static ArrayList<ArrayList<String>> computePodium(Runner[] runners){
		
		ArrayList<ArrayList<String>> realrank = new ArrayList<ArrayList<String>>();
		
		/* Initialisation des listes */
		for(int i = 0 ; i < runners.length; i++){
			realrank.add(i, new ArrayList<String>());
		}
		
		/* affectation des runner en fonction de leur position */
		for(int i = 0 ; i < runners.length; i++){
			realrank.get(runners[i].getNbDevant()).add(runners[i].getNom());
		}
		
		/* suppression des rangs vides dus aux ex aequo */
		int cpt = 0;
		int nbmaxiter = realrank.size();
		for(int i = 0 ; i < nbmaxiter; i++){
			if( realrank.get(cpt).size() == 0 ){
				realrank.remove(cpt);
			}else{
				cpt++;
			}
		}
		
		ArrayList<ArrayList<String>> marchesPodium = new ArrayList<ArrayList<String>>();
		
		/* transfert des données */
		for( int i = 0 ; i < Math.min(3,realrank.size())  ; i++){
			marchesPodium.add(i, realrank.get(i));
		}
		
		return marchesPodium;
		
	}
	
	
	
	

	
	
	

}
