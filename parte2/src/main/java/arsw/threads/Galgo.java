package arsw.threads;

/**
 * Un galgo que puede correr en un carril
 * 
 * @author rlopez
 * 
 */
public class Galgo extends Thread {
	private int paso;
	private Carril carril;
	RegistroLlegada regl;
	Pausa pau;

	public Galgo(Carril carril, String name, RegistroLlegada reg, Pausa pau) {
		super(name);
		this.carril = carril;
		paso = 0;
		this.regl=reg;
		this.pau = pau;
	}

	public void corra() throws InterruptedException {
		while (paso < carril.size()) {
			pau.waitIsPaused();
			Thread.sleep(100);
			carril.setPasoOn(paso++);
			carril.displayPasos(paso);
			
			if (paso == carril.size()) {						
				carril.finish();
				int ubicacion = regl.asignarPosicion(this.getName());
				System.out.println("El galgo "+this.getName()+" llego en la posicion "+ubicacion);
				
			}
		}
	}


	@Override
	public void run() {
		
		try {
			corra();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
