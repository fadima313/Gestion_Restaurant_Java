package factories;

import com.app.dao.impl.*;	

public class CommandeFactory extends AbstractFactory {
	
	public CommandeFactory() {}
	
	@Override
	public CommandeDaoImpl getCommandeDao (Class<? extends CommandeDaoImpl> typeDao) {
		if ( typeDao == null ) {
			return null;
		}
		
		if (typeDao == CommandeDaoImpl.class) {
			return new CommandeDaoImpl ();
		} 
		
		return null;
	}
}
