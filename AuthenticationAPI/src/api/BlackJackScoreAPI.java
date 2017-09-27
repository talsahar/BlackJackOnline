package api;

public interface BlackJackScoreAPI {
	public double load(String user);
	public boolean save(String user,double score);
}
