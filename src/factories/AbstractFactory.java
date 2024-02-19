package factories;

import com.app.dao.impl.*;

public class AbstractFactory {
	public AbstractFactory() { }
	
	public ChefDaoImpl getChefDao (Class<? extends ChefDaoImpl> typeDao) {
		return null ;
	}
	
	public RestaurateurDaoImpl getRestaurateurDao (Class<? extends RestaurateurDaoImpl> typeDao) {
		return null ;
	}
	
	public AdministrateurDaoImpl getAdministrateurDao (Class<? extends AdministrateurDaoImpl> typeDao) {
		return null ;
	}
	
	public ProduitDaoImpl getProduitDao (Class<? extends ProduitDaoImpl> typeDao) {
		return null ;
	}
	
	public CommandeDaoImpl getCommandeDao (Class<? extends CommandeDaoImpl> typeDao) {
		return null ;
	}
	
	public PaiementDaoImpl getPaiementDao (Class<? extends PaiementDaoImpl> typeDao) {
		return null ;
	}
	
	public RecetteDaoImpl getRecetteDao (Class<? extends RecetteDaoImpl> typeDao) {
		return null ;
	}
}
