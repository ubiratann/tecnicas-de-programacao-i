package controller;

import model.*;
import java.util.*;

public class Leitor {
	String stringPsi; // formula entrada em String
	String[] psi; // aray de String
	Atomica[] atomicas; // nos folhas do composite

	public Leitor(String form) {
		stringPsi = form.toUpperCase(); // por padrao, temos toda a expressao em maiuscula
		psi = form.toUpperCase().split(""); // quebramos a String entrada em array de String
		atomicas = new Atomica[qtdAtomicas()]; // iniciamos o array das Atomicas que contera os nos folhos do composite
	}
	
	public Formula criarForm(String phi) {
		Formula aux;
	    
	    if (phi.compareTo("A") == 0 || phi.compareTo("B") == 0 || phi.compareTo("C") == 0 || phi.compareTo("D") == 0 || phi.compareTo("E") == 0) 
		{
			aux = new Atomica(phi);
			// adicionamos de trás para frente pois os primeiros lidos (processamos da esquerda a direita) são mais significativos
			for (int i = atomicas.length-1; i >= 0; --i)
			{
				if (atomicas[i] == null)
				{
					atomicas[i] = (Atomica)aux; // adiciona a posição seja que e null
					return atomicas[i];
				} else {
					// verificamos se a String não tem o mesmo nome que uma Atomica antes criada
					if (atomicas[i].getNome().compareTo(phi) == 0) {
						return atomicas[i]; // se sim, não precisamos adiciona-la,pois caso contrario seria tratato como Atomicas distintas
					}
				}
			}
		}
		else { // se nao for atomica, e conectivo ou parentese fechado ")"
			switch(phi) {
				case ".":
		    		aux = new Conjuncao(null, phi, null);
		    		break;
		    	
		    	case "+":
		    		aux = new Disjuncao(null, phi, null);
		    		break;
		    	
		    	case "'":
		    		aux = new Negacao(null,phi);
		    		break;
		    	
		    	default:
		    		aux = null; // obs: a entrada dos dados e tratada no inicio do programa, aux nao sera null
		    		break;
		    }
		}
		
	    return aux;
	}
	// retorna o indice di fim da subformula da qual queremos computar, isto é, indice de um ' (negação) ou de um ) (parenteses)
	int fimSubForm(String[] phi, int ini) {
		int i, qtdParentesis = 0;

		for (i = ini; i < phi.length; ++i)
		{
			if ( phi[i].compareTo("(") == 0 ) ++qtdParentesis;
			
			if ( phi[i].compareTo(")") == 0 )
			{
				--qtdParentesis;
				
				if ( qtdParentesis == 0 ) // qtdParenteses igual a 0 significa que os parenteses foram abertos e fechados corretamente
				{
					if ( i < phi.length-1 && phi[i+1].compareTo("'") == 0 ) return i+1; // obs: para negar a proposicao é necessario por entre parenteses. nesse caso, retornamos o indice de '
					return i; // indice de )
				}
			}
		}

		return 0; //obs: a entrada dos dados e tratada no inicio do programa
	}
	
	public Formula lerFormula() {
		int i, f;
		Formula aux;
		Pilha p = new Pilha();

		for (i = 0; i < psi.length; i++) 
		{
			if ( psi[i].compareTo("(") == 0 ) 
			{
				f = fimSubForm(psi, i); // retorna o indice que termina a subformula ou a formula da negação
				
				p.push(lerFormula(++i, f)); // lemos o que está dentro dos parênteses, e ao fim, empilhamos

				i = f; // indice pula para o fim da formula lida no comando acima
			} 
			else 
			{
				// obs: nosso programa ignora quando encontramos ). Os parênteses são apenas marcações.
				if (psi[i].compareTo(")") != 0) {
					aux = criarForm( psi[i] ); // cria uma formula atômica ou um conectivo
					p.push(aux); // empilha o objetivo criado
				}
			}
	        	
			// desempilhamos, e "concatenamos" os elementos da pilha,  caso necessario
			if ( p.readyUnary() ) p.reduceStackUnary(); 
			
			if ( p.readyBinary() ) p.reduceStackBinary(); 
		}
		
		return p.pop(); // desempilha. obs: sera do tipo Conectivo e apos isso a pilha ficara vazia
	}
	
	// fazemos sobrecarga do método para lermos pedaços da expressão entrada
	public Formula lerFormula(int ini, int fim) {
		int i, f;
		Formula aux;
		Pilha p = new Pilha();

		for (i = ini; i <= fim; i++)
		{
			if ( psi[i].compareTo("(") == 0 ) 
			{
				f = fimSubForm(psi, i);
				
				p.push(lerFormula(++i, f));

				i = f;
			} 
			else 
			{
				if (psi[i].compareTo(")") != 0) {
					aux = criarForm( psi[i] );
					p.push(aux);
				}
			}
	        
			if ( p.readyUnary() ) p.reduceStackUnary(); 
			
			if ( p.readyBinary() ) p.reduceStackBinary(); 
		}

		return p.pop();
	}
	// retorna a String processada pela classe
	public String stringFormula() {
		return stringPsi;
	}
	// retorna a quantidade exata de atomicas contida na nossa expressão
	public int qtdAtomicas() {
		if (psi != null) {
			Set set = new HashSet(); // usamos Set para que não haja duplicação

			for (String c : psi) 
				if (c.compareTo("A") == 0 || c.compareTo("B") == 0 || c.compareTo("C") == 0 || c.compareTo("D") == 0 || c.compareTo("E") == 0) 
					set.add(c); // add as atomicas a set, caso ela não exista na coleção

			if (set.size() != 0) return set.size(); // o número de atômicas é igual ao tamanho da coleção
		}

		return 0; // caso a String entrada seja nula
	}

	public Atomica[] obterAtomicas() {
		return atomicas; // retorna o as atomicas que foram criadas após o método lerForm()
	}
}
