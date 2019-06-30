package controller;
import model.*;

// implementamos uma pilha de tam. fixo 3 por motivos de desempilharmos, formando fórmulas com o conectivo principal sendo unário ou binário
public class Pilha {
	Formula[] pilha = new Formula[3];
	int topo = -1;
	// tamanho
	int size() {
		if ( empty() ) return 0;
		return topo+1;
	}
	// pilha vazia
	boolean empty() {
		if ( topo == -1 ) return true;
		return false;
	}
	// pilha cheia
	boolean flow() {
		if ( topo == 2 ) return true;
		return false;
	}
	// empilha
	void push(Formula f) {
		if (flow()) {
			System.out.println("Error: overflow");

		} else {
			++topo;
			
			pilha[topo] = f;
		}
	}
	// desempilha
	Formula pop() {
		if (empty()){
			System.out.println("Error: underflow");
			return null;
		}

		Formula x = pilha[topo];

		pilha[topo] = null;

		--topo;

		return x;
	}
	// método para vermos o que há no topo da pilha (foi usado somente para debugar)
	void top() {
		if (empty()) System.out.println("Error: stack empty");

		System.out.println(pilha[topo]);
	}
	// as funções abaixo de nome ready são essenciais para as funções de reduceStack, pois são conduções fundamentais para formação das subformulas
	boolean readyUnary() {
		if ( pilha[topo] instanceof Negacao && size() == 2 ) return true;
		return false;
	}
	
	boolean readyBinary() {
		if (flow()) return true;
		return false;
	}
	// verifica se o topo da pilha é do tipo Atomica
	boolean isAtomica() {
		if (empty()) System.out.println("Error: stack empty");

		if ( pilha[topo] instanceof Atomica ) return true;

		return false;
	}
	// obs (métodos reduceStack): a cada posição da pilha verificamos as possibilidades do tipo do elemento, exeto na posição que estará o elemento de tipo Conectivo, que será a raiz da árvore, na perspectiva do composite.
	// reduzindo o tam. da pilha e criando uma fórmula com o conectivo principal (no raiz na arv. do composite) sendo unário
	void reduceStackUnary() {
		Conectivo conecUn = (Conectivo)pop();

		if (isAtomica())
			conecUn.add( (Atomica)pop() );
		else
			conecUn.add( (Conectivo)pop() );

		push(conecUn);
	}
	// reduzindo o tam. da pilha e criando uma fórmula com o conectivo principal (no raiz na arv. do composite) sendo binário
	void reduceStackBinary() {
		Atomica atTop = null, atLow = null;
		Conectivo conecTop = null, conecCenter = null, conecLow = null;

		if (isAtomica())
			atTop = (Atomica)pop();
		else
			conecTop = (Conectivo)pop();

		conecCenter = (Conectivo)pop();

		if (isAtomica())	
			atLow = (Atomica)pop();
		else
			conecLow = (Conectivo)pop();
		
		if (atLow != null) 
			conecCenter.add(atLow);
		else
			conecCenter.add(conecLow);
		
		if (atTop != null) 
			conecCenter.add(atTop);
		else
			conecCenter.add(conecTop);
		
		push(conecCenter);
	}
}
