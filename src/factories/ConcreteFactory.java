package factories;



public class ConcreteFactory {

	public ConcreteFactory() { }
	
	public static AbstractFactory getFactory (Class<? extends AbstractFactory> factory) {
		if (factory == null) {
			return null;
		}
		
		if (factory == CommandeFactory.class) {
			return new CommandeFactory ();
		} else if (factory == ProduitFactory.class) {
			return new ProduitFactory ();
		} else if (factory == PaiementFactory.class) {
			return new PaiementFactory ();
		} else if (factory == RecetteFactory.class) {
			return new RecetteFactory ();
		} else if (factory == ChefFactory.class) {
			return new ChefFactory ();
		} else if (factory == RestaurateurFactory.class) {
			return new RestaurateurFactory ();
		} else if (factory == AdministrateurFactory.class) {
			return new AdministrateurFactory ();
		
		}
		
		return null;
	}

	
}
