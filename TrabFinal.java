import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author milton
 */
public class TrabFinal {

    /**
     * @param args the command line arguments
     */
	
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
    	
    	TelaVal d = new TelaVal();        
        d.setVisible(true);
    }
    
}
