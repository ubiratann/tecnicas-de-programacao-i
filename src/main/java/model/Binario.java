package model;

public interface Binario {

	void escolherBitPos(int i);

	void incrementarContador();

	void zerarContador();
	
	int getContador();

	int getContadorMax();

	void trocarValor();
}