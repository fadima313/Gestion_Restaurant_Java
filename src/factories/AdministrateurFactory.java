package factories;

import com.app.dao.impl.*;	

public class AdministrateurFactory extends AbstractFactory {
	
	public AdministrateurFactory() {}
	
	@Override
	public AdministrateurDaoImpl getAdministrateurDao (Class<? extends AdministrateurDaoImpl> typeDao) {
		if ( typeDao == null ) {
			return null;
		}
		
		if (typeDao == AdministrateurDaoImpl.class) {
			return new AdministrateurDaoImpl ();
		} 
		
		return null;
	}
}
