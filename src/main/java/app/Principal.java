package app;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.TelaCalculadora;


public class Principal {
	
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
    	TelaCalculadora calculadora = new TelaCalculadora();        
    	calculadora.setVisible(true);
    }
    
}
