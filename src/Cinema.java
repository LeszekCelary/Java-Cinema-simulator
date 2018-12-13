import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Cinema{

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		List<Future<Person>> personList = new ArrayList<>();
		
		ExecutorService firstExecutor = Executors.newFixedThreadPool(100);
		for(int i = 0; i < 100;i++)
		{
			Person person = new Person(i, false);
			personList.add(firstExecutor.submit(new GoingToCinemaProblem(person)));
		}
		firstExecutor.shutdown();
		
		int counter = 0;
		for(int i = 0; i < 100;i++)
			if(personList.get(i).get().getGoingToCinema())
				counter = counter + 1;
		
		if(counter < 5)
			System.out.println("Przepraszamy, film zostal anulowany");
		else
		{
			List<Future<Spectator>> spectatorList = new ArrayList<>();
			
			ExecutorService secondExecutor = Executors.newFixedThreadPool(counter);
			for(int i = 0; i < counter; i++)
			{
				Spectator spectator = new Spectator(i, false);
				spectatorList.add(secondExecutor.submit(new WatchingFilmProblem(spectator)));
			}
			secondExecutor.shutdown();
			
			int secondCounter=0;
			for(int i = 0; i < counter; i++)
				if(spectatorList.get(i).get().getWatchingFilm())
					secondCounter = secondCounter + 1;
			
			if(secondCounter < 5)
				System.out.println("Film zostal przerwany, poniewaz na sali nie bylo pieciu widzow");
			else
				System.out.println("Film zostal wyswietlony, poniewaz bylo co najmniej pieciu widzow");
		}
	}
}

class GoingToCinemaProblem implements Callable<Person>{
	private int counter;
	private double p = 0.05;
	
	GoingToCinemaProblem(Person p)
	{
		this.counter = p.getCounter() + 1;
	}

	@Override
	public Person call() throws Exception {
		Random rand = new Random();
		Thread.sleep((rand.nextInt(4) + 1) * 1000);
		Person person = null;
		int state = rand.nextInt(100);
		if(state <= (p*100))
			person = new Person(this.counter, true);
		else 
			person = new Person(this.counter, false);
		return person;
	}
}

class WatchingFilmProblem implements Callable<Spectator>{
	private int counter;
	private double p = 0.3;
	
	WatchingFilmProblem(Spectator s)
	{
		this.counter = s.getCounter() + 1;
	}

	@Override
	public Spectator call() throws Exception {
		Random rand = new Random();
		Thread.sleep(2000);
		Spectator spectator = null;
		int state = rand.nextInt(100);
		if(state <= (p * 100))
			spectator = new Spectator(this.counter, true);
		else 
			spectator = new Spectator(this.counter, false);
		Thread.sleep(2000);
		return spectator;
	}
}