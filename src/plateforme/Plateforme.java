package plateforme;

public class Plateforme {
	
	private Plateforme instance = null;

	private Plateforme() {
		super();
	}


	public Plateforme getInstance() {
		if(instance == null){
			instance = new Plateforme();
		}
		return instance;
	}
	
	
}
