package api;

public interface AuthenticationAPI {

	public boolean userLogin(String user,String pass);
	public boolean AdminLogin(String user,String pass);
	public boolean userRegister(String user,String pass);
	
}
