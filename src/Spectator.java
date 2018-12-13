
public class Spectator 
{
	private int counter;
	private boolean watchingFilm;
	
	Spectator(){};
	
	Spectator(int c, boolean b){
		this.counter = c;
		this.watchingFilm = b;
	}
	
	int getCounter(){
		return this.counter;
	}
	
	boolean getWatchingFilm() {
		return this.watchingFilm;
	}
	
	void setCounter(int c) {
		this.counter = c;
	}
	
	void setWatchingFilm(boolean b) {
		this.watchingFilm = b;
	}
}
