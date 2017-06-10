package nl.hu.ipass.gameHistory.Service;

public class ServiceProvider {
	
	private static RondeService rondeservice = new RondeService();
	private static SpelerService spelerservice = new SpelerService();
	private static SpelService spelservice = new SpelService();
	
	public static RondeService getRondeService(){
		return rondeservice;
	}
	
	public static SpelerService getSpelerService(){
		return spelerservice;
	}
	
	public static SpelService getSpelService(){
		return spelservice;
	}
	
}
