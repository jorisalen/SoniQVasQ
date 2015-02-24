package api;


public class ApiFacade {

	
	private Api apiCaller;
	
	
	public void callApi(String query,String type) throws ApiException{
		apiCaller = ApiFactory.createApi(type);
		apiCaller.doCall(query);
	}
		
}
