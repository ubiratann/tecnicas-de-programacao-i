import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.*;
import model.*;


public class TrabFinal {


	
	public static void InterfaceSystem() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {

		} catch (ClassNotFoundException e) {

		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		}
	}
	
	
    public static void main(String[] args) {
    	
    	InterfaceSystem(); //Muda a Interface
    	
	//instancia da view    
    	TelaVal d = new TelaVal();        
        d.setVisible(true);
    }
    
}
