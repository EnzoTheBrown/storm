package stormTP.core;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

/** Classe représentant un coureur 'Tortue' ou 'Lièvre'
*/
public class Runner{
	
	long id = -1;    // identifiant du coureur
	long top = -1;   // numéro d'observation
	int position = -1;  // numéro de cellule sur la piste
	String nom = "";  // nom du coureur
	String rang = "";   // rang du coueur
	int nbDevant = -1;   // nombre de coureurs se trouvant devant le coueur courant dans le classement
	int nbDerriere = -1;  //nombre de coureurs se trouvant derrière le coureur courant dans le classement 
	int total = -1;  // nombre de coureurs en lice pour la course courante
	int points = 0;  // nombre de points cumulés par le coureur
	double mean = 0;

	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	double speed = 0;
	
	public Runner(){
		
	}
	
	public Runner(long id, String name, int before, int after, int total, int position, long top){
		this.id = id;
		this.nom = name;
		this.nbDevant = before;
		this.nbDerriere = after;
		this.total = total;
		this.position = position;
		this.top = top;
	}

/* Getters and setters */
	public long getId() {
	    return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getTop() {
		return top;
	}


	public void setTop(long top) {
		this.top = top;
	}


	public int getPosition() {
		return position;
	}


	public void setPosition(int position) {
		this.position = position;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getRang() {
		return rang;
	}


	public void setRang(String rang) {
		this.rang = rang;
	}


	public int getNbDevant() {
		return nbDevant;
	}


	public void setNbDevant(int nbDevant) {
		this.nbDevant = nbDevant;
	}


	public int getNbDerriere() {
		return nbDerriere;
	}


	public void setNbDerriere(int nbDerriere) {
		this.nbDerriere = nbDerriere;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}
	

	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}



	public String getJSONSpeed(){
		JsonObjectBuilder r = null;
		r = Json.createObjectBuilder();
		/* construction de l'objet JSON résultat */
		r.add("id", this.id);
		r.add("top", this.top);
		r.add("nom", this.nom);
		r.add("speed", this.speed);

		return r.build().toString();
	}



	public String getJSONRank(){
		JsonObjectBuilder r = null;
		r = Json.createObjectBuilder();
		/* construction de l'objet JSON résultat */
		r.add("id", this.id);
		r.add("top", this.top);
		r.add("nom", this.nom);
		r.add("rang", this.rang);
		r.add("nbTotal", this.total);

		return r.build().toString();
	}

	public String getJSONMean(){
		JsonObjectBuilder r = null;
		r = Json.createObjectBuilder();
		/* construction de l'objet JSON résultat */
		r.add("id", this.id);
		r.add("top", this.top);
		r.add("nom", this.nom);
		r.add("rang", this.rang);
		r.add("nbTotal", this.total);
		r.add("mean", this.mean);

		return r.build().toString();
	}

	public String getJSONPoints(){
		JsonObjectBuilder r = null;
		r = Json.createObjectBuilder();
		/* construction de l'objet JSON résultat */
		r.add("id", this.id);
		r.add("top", this.top);
		r.add("nom", this.nom);
		r.add("score", this.points);

		return r.build().toString();
	}


	public String getJSON_V1(){
		JsonObjectBuilder r = null;
		r = Json.createObjectBuilder();
		/* construction de l'objet JSON résultat */
		r.add("id", this.id);
		r.add("top", this.top);
		r.add("nom", this.nom);
		r.add("position", this.position);
        r.add("nbDevant", this.nbDevant);
        r.add("nbDerriere", this.nbDerriere);
        r.add("total", this.total);
       
        return r.build().toString();
	}
	
	public String getJSON_V2(){
		JsonObjectBuilder r = null;
		r = Json.createObjectBuilder();
		/* construction de l'objet JSON résultat */
		r.add("id", this.id);
		r.add("top", this.top);
		r.add("nom", this.nom);
		r.add("rang", this.rang);
        r.add("nbTotal", this.total);
       
        return r.build().toString();
	}


	
}
