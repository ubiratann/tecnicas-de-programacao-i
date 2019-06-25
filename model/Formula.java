package model;
import java.util.*;

public abstract class Formula {
	//protected List<Observador> observadores = new ArrayList<>();

	public abstract boolean obterValor();

	public void add(Formula a) {};

	public void remove(Formula a) {};
        
	
	Formula obterFilho(int index) {return null;} // 0 para o filho esquerdo e 1 para o filho direito

	/*public void anexarObservador(Observador observador) {
		observadores.add(observador);
	}
	public void desanexarObservador(Observador observador) {
		observadores.remove(observador);
	}
	public void notificarObservadores() {
		for (Observador i : observadores) i.atualizar();
	}
	
	public void chamarObservadores() {
		for (Observador i : observadores) i.msg();
	}*/
}