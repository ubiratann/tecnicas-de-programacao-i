package model;

public abstract class Formula {

	public abstract boolean getValor();

	public void add(Formula a) {};

	public void remove(Formula a) {};
	
	public abstract Formula getEsquerdo();
	
	public abstract Formula getDireito();

}