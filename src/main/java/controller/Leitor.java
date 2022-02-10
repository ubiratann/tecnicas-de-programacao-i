package controller;

import model.Atomica;
import model.Conjuncao;
import model.Disjuncao;
import model.Formula;
import model.Negacao;

public class Leitor {

	private String stringPsi; // formula entrada em String
	private String[] arrayPsi; // array de String
	private Atomica[] arrayAtomicas; // nos folhas do composite

	
	/*
	 * Criamos um leitor que contém a String da formula que é recebida na interface gráfica da calculadora
	 * e apos isso geramos 2 arrays, um com todos os caracteres da fórmula e outro com
	 * de Atomicas com base na quantidade de caracterese de formulas atomicas que existem no arrayPsi
	 */
	

	public Leitor(String form) {
		stringPsi = form.toUpperCase();
		arrayPsi = form.toUpperCase().split("");
		arrayAtomicas = new Atomica[getQuantidadeAtomicas(arrayPsi)]; 
	}

	
	/* 
	 * O método criarFormulas usa a estratégia de notação polonesa para gerar as fórmulas de uma string
	 * 
	 * */
	public Formula criarFormula(String phi) {
		Formula aux;
		if (phi.matches("[A-E]")) {
			aux = new Atomica(phi);
			for (int i = arrayAtomicas.length - 1; i >= 0; --i) {
				if (arrayAtomicas[i] == null) {
					arrayAtomicas[i] = (Atomica) aux; 
					return arrayAtomicas[i];
				} else {
					if (arrayAtomicas[i].getNome().equals(phi)) {
						return arrayAtomicas[i];
					}
				}
			}
		} else { 
			switch (phi) {
			case ".":
				aux = new Conjuncao(null, phi, null);
				break;

			case "+":
				aux = new Disjuncao(null, phi, null);
				break;

			case "'":
				aux = new Negacao(null, phi);
				break;

			default:
				aux = null;
				break;
			}
		}

		return aux;
	}

	// retorna o indice di fim da subformula da qual queremos computar, isto é,
	// indice de um ' (negação) ou de um ) (parenteses)
	public int fimSubFormula(String[] phi, int ini) {
		int i, qtdParentesis = 0;

		for (i = ini; i < phi.length; ++i) {
			if (phi[i].equals("("))
				++qtdParentesis;

			if (phi[i].equals(")")) {
				--qtdParentesis;

				if (qtdParentesis == 0) // qtdParenteses igual a 0 significa que os parenteses foram abertos e fechados
										// corretamente
				{
					if (i < phi.length - 1 && phi[i + 1].equals("'"))
						return i + 1; // obs: para negar a proposicao é necessario por entre parenteses. nesse caso,
										// retornamos o indice de '
					return i; // indice de )
				}
			}
		}

		return 0; // obs: a entrada dos dados e tratada no inicio do programa
	}

	public Formula lerFormula() {
		int i, f;
		Formula aux;
		Pilha p = new Pilha();

		for (i = 0; i < arrayPsi.length; i++) {
			if (arrayPsi[i].equals("(")) {
				f = fimSubFormula(arrayPsi, i); // retorna o indice que termina a subformula ou a formula da negação

				p.push(lerSubFormula(++i, f)); // lemos o que está dentro dos parênteses, e ao fim, empilhamos

				i = f; // indice pula para o fim da formula lida no comando acima
			} else {
				// obs: nosso programa ignora quando encontramos ). Os parênteses são apenas
				// marcações.
				if (!arrayPsi[i].equals(")")) {
					aux = criarFormula(arrayPsi[i]); // cria uma formula atômica ou um conectivo
					p.push(aux); // empilha o objetivo criado
				}
			}

			// desempilhamos, e "concatenamos" os elementos da pilha, caso necessario
			if (p.readyUnary())
				p.reduceStackUnary();

			if (p.readyBinary())
				p.reduceStackBinary();
		}

		return p.pop(); // desempilha. obs: sera do tipo Conectivo e apos isso a pilha ficara vazia
	}


	private Formula lerSubFormula(int ini, int fim) {
		int i, f;
		Formula aux;
		Pilha p = new Pilha();

		for (i = ini; i <= fim; i++) {
			if (arrayPsi[i].equals("(")) {
				f = fimSubFormula(arrayPsi, i);

				p.push(lerSubFormula(++i, f));

				i = f;
			} else {
				if (!arrayPsi[i].equals(")")) {
					aux = criarFormula(arrayPsi[i]);
					p.push(aux);
				}
			}

			if (p.readyUnary())
				p.reduceStackUnary();

			if (p.readyBinary())
				p.reduceStackBinary();
		}

		return p.pop();
	}
	
	private int getQuantidadeAtomicas(String[] array) {
		int contador = 0;
		
		if(array != null && array.length > 0) {
			for(String atomica : array) {
				if(atomica.matches("[A-E]")) contador++;
			}			
		}
		
		return contador;
	}

	public String getStringPsi() {
		return stringPsi;
	}

	public void setStringPsi(String stringPsi) {
		this.stringPsi = stringPsi;
	}

	public String[] getArrayPsi() {
		return arrayPsi;
	}

	public void setArrayPsi(String[] arrayPsi) {
		this.arrayPsi = arrayPsi;
	}

	public Atomica[] getArrayAtomicas() {
		return arrayAtomicas;
	}

	public void setArrayAtomicas(Atomica[] arrayAtomicas) {
		this.arrayAtomicas = arrayAtomicas;
	}

}
