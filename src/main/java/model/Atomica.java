package model;

public class Atomica extends Formula implements Binario {
	
	private String nome;
	private boolean valor;
	private int bitPos, cont;

	public Atomica (String nome) {
		this.nome = nome;
		this.valor = false;
	}


	public Atomica (String nome, Boolean bool) {
		this.nome = nome;
		this.valor = bool;
	}

	public boolean getValor() {
		return this.valor;
	}
	
	public void setValor(boolean valor) {
		this.valor = valor;
	}

	@Override
	public void escolherBitPos(int pos) {
		this.bitPos = pos;
	}

	@Override
	public void incrementarContador() {
		this.cont++;
	}

	@Override
	public int getContador() {
		return this.cont;
	}

	@Override
	public void zerarContador() {
		this.cont = 0;
	}
	
	@Override
	public int getContadorMax() {
		return (int)Math.pow(2, bitPos);
	}

	@Override
	public void trocarValor() {
		valor = !valor;
	}

	@Override
	public String toString() {
		return this.nome;
	}
        
    public String getNome(){
        return this.nome;
    }
    
    @Override
    public Formula getEsquerdo() { return null ; } 

	@Override
	public Formula getDireito() { return null; }


        
}