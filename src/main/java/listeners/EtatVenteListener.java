package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class EtatVenteListener
 *
 */
@WebListener
public class EtatVenteListener implements ServletContextListener {
	
	private final static long TEMPS_ACTUALISATION_MS = 30000; //30 secondes
	private Thread asyncTask = null;
    /**
     * Default constructor. 
     */
    public EtatVenteListener() {
        // TODO Auto-generated constructor stub
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("L'application LiveCoding s'arrete !");
    	if (asyncTask != null && asyncTask.isAlive()) {
    		asyncTask.interrupt();
    		System.out.println("Le traitement asynchrone s'arrete !");
    	}
    }

    public void contextInitialized(ServletContextEvent sce)  { 
      //definition d'un traitement asynchrone s'executant toutes les 5 secondes
      asyncTask = new Thread(new Runnable() {
			@Override
			public void run() {
				//décrire le traitement asybchrone à réaliser
				try {
					//System.out.println("Un traitement asynchrone et récurrent est en cours d'executions !");
					while(!asyncTask.isInterrupted()) {
						//Ici procedure stockée
						System.out.println("Actualisation des etats de vente");
						Thread.sleep(TEMPS_ACTUALISATION_MS);
					}
				} catch (InterruptedException e) {
					System.out.println("Interruption des actualisations !");
				}
				
			}
      });
      //demarrer le traitement asynchrone
      asyncTask.start();
    }
	
}
