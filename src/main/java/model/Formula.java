package model;

public abstract class Formula {

	public abstract boolean obterValor();

	public void add(Formula a) {};

	public void remove(Formula a) {};
	
	public abstract Formula getFilhoEsquerdo();
	
	public abstract Formula getFilhoDireito();

}