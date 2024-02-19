package factories;

import com.app.dao.impl.*;	

public class RestaurateurFactory extends AbstractFactory {
	
	public RestaurateurFactory() {}
	
	@Override
	public RestaurateurDaoImpl getRestaurateurDao (Class<? extends RestaurateurDaoImpl> typeDao) {
		if ( typeDao == null ) {
			return null;
		}
		
		if (typeDao == RestaurateurDaoImpl.class) {
			return new RestaurateurDaoImpl ();
		} 
		
		return null;
	}
}
