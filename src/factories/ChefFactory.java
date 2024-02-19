package factories;

import com.app.dao.impl.*;	

public class ChefFactory extends AbstractFactory {
	
	public ChefFactory() {}
	
	@Override
	public ChefDaoImpl getChefDao (Class<? extends ChefDaoImpl> typeDao) {
		if ( typeDao == null ) {
			return null;
		}
		
		if (typeDao == ChefDaoImpl.class) {
			return new ChefDaoImpl ();
		} 
		
		return null;
	}
}
