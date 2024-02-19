package factories;

import com.app.dao.impl.*;	

public class ProduitFactory extends AbstractFactory {
	
	public ProduitFactory() {}

	@Override
	public ProduitDaoImpl getProduitDao (Class<? extends ProduitDaoImpl> typeDao) {
		if ( typeDao == null ) {
			return null;
		}
		
		if (typeDao == ProduitDaoImpl.class) {
			return new ProduitDaoImpl ();
		} 
		
		return null;
	}
}
