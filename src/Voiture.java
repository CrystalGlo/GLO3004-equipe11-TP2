
public class Voiture implements Runnable {
	private String depart;
	private String direction;
	public Thread voitureEstController;
	public Thread voitureOuestController;
	public Thread voitureSudController;
	public Boolean threadLance = false;
	
	Voiture(String directionDepart, String directionDestination) {
		this.depart = directionDepart;
		this.direction = directionDestination;
	}
	
	synchronized void controllerVoitureEst() {
		threadLance = false;
		if(direction.equals("tournerDroite")) {
			voitureEstController = new Thread();
			voitureEstController.start();
			threadLance = true;
			System.out.println("voitureEstController a lancé un Thread : ");
		}
	}
	
	synchronized void controllerVoitureOuest() {
		threadLance = false;
		if(direction.equals("tournerGauche")) {
			voitureOuestController = new Thread(this);
			voitureOuestController.start();
			threadLance = true;
			System.out.println("voitureOuestController a lancé un Thread : ");
		}
	}
	
	synchronized void controllerVoitureSud() {
		threadLance = false;
		if(direction.equals("continuer")) {
			voitureSudController = new Thread(this);
			voitureSudController.start();
			threadLance = true;
			System.out.println("voitureSudController a lancé un Thread : ");
		}
	}
	public Boolean threadExecute() {
		return threadLance;
	}
	
	public void run() {
		try {
			Thread.sleep(1000);
		} 
		catch(InterruptedException e) {
			System.out.println("Thread voiture est interrompu à cause d'une exception");

			voitureEstController = null;
			voitureOuestController = null;
			voitureSudController = null;
		}
	}	
}