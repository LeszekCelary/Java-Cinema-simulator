
public class Person {

	private int counter;
	private boolean goingToCinema;
	
	Person(){};
	
	Person(int c, boolean b){
		this.counter = c;
		this.goingToCinema = b;
	}
	
	int getCounter(){
		return this.counter;
	}
	
	boolean getGoingToCinema() {
		return this.goingToCinema;
	}
	
	void setCounter(int c) {
		this.counter = c;
	}
	
	void setGoingToCinema(boolean b) {
		this.goingToCinema = b;
	}
}
