import java.util.*;

public class Leitor {
	String stringPsi;
	String[] psi;
	Atomica[] atomicas;

	Leitor(String form) {
		stringPsi = form.toUpperCase();
		psi = form.toUpperCase().split("");
		atomicas = new Atomica[qtdAtomicas()];
	}
	
	Formula criarForm(String phi) {
		Formula aux;
	    
	    if (phi.compareTo("A") == 0 || phi.compareTo("B") == 0 || phi.compareTo("C") == 0 || phi.compareTo("D") == 0 || phi.compareTo("E") == 0) 
		{
			aux = new Atomica(phi);

			for (int i = atomicas.length-1; i >= 0; --i)
			{
				if (atomicas[i] == null)
				{
					atomicas[i] = (Atomica)aux;
					//System.out.println("string: "+atomicas[i]);
					return atomicas[i];
				} else {
					if (atomicas[i].getNome().compareTo(phi) == 0) {
					//	System.out.println("já existe string: "+atomicas[i]); 
						return atomicas[i];
					}
				}
			}
		}
		else {
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
		    		aux = null;
		    		break;
		    }
		    
		 //   System.out.println("conectivo: "+phi);
		}
	//	System.out.println("retorno string: "+aux);
	    return aux;
	}

	int fimSubForm(String[] phi, int ini) {
		int i, qtdParentesis = 0;

		for (i = ini; i < phi.length; ++i)
		{
			if ( phi[i].compareTo("(") == 0 ) ++qtdParentesis;
			
			if ( phi[i].compareTo(")") == 0 )
			{
				--qtdParentesis;
				
				if ( qtdParentesis == 0 )
				{
					if ( i < phi.length-1 && phi[i+1].compareTo("'") == 0 ) return i+1; // CONSIDERANDO QUE PODE HAVER O SIMBOLO DA NEGAÇÃO APÓS O PARENTESE
					return i;
				}
			}
		}

		return 0;
	}
	
	Formula lerFormula() {
		int i, f;
		Formula aux;
		Pilha p = new Pilha();

	//	System.out.println("Expressao: ");
		for (i = 0; i < psi.length; i++) System.out.print(i+" ");
	//	System.out.println();
		for (String c : psi) System.out.print(c+" ");

		for (i = 0; i < psi.length; i++) 
		{
	//		System.out.println("\nRODADA: "+(i+1)+"\n");

			if ( psi[i].compareTo("(") == 0 ) 
			{
				f = fimSubForm(psi, i);
		//		System.out.println("abre: "+psi[i]+" "+i+"\tfecha: "+psi[f]+" "+f);
				
				p.push(lerFormula(++i, f));

				i = f;
			} 
			else 
			{
				if (psi[i].compareTo(")") != 0) {
					aux = criarForm( psi[i] );
					p.push(aux);
				}
				else {
		//			System.out.println("paretense: "+psi[i]);
				}
			}
	        
	        if ( p.readyUnary() ) {
	        	p.reduceStackUnary();
	    //   	System.out.print("pilha: ");
	        	p.top();
	        }
			
			if ( p.readyBinary() ) {
				p.reduceStackBinary();
		//		System.out.print("pilha: ");
	        	p.top();
			}
		}
		
		return p.pop();
	}

	Formula lerFormula(int ini, int fim) {
		int i, f, cont = 1;
		Formula aux;
		Pilha p = new Pilha();

	//	System.out.println("\nSubexpressao: ");
		for (i = ini; i <= fim; ++i) System.out.print(i+" ");
	//	System.out.println();
		for (i = ini; i <= fim; ++i) System.out.print(psi[i]+" ");

		for (i = ini; i <= fim; i++)
		{
	//		System.out.println("\nRODADA: "+cont+"\n");

			if ( psi[i].compareTo("(") == 0 ) 
			{
				f = fimSubForm(psi, i);
	//			System.out.println("abre: "+psi[i]+" "+i+"\tfecha: "+psi[f]+" "+f);
				
				p.push(lerFormula(++i, f));

				i = f;
			} 
			else 
			{
				if (psi[i].compareTo(")") != 0) {
					aux = criarForm( psi[i] );
					p.push(aux);
				}
				else {
	//				System.out.println("paretense: "+psi[i]);
				}
			}
	        
	        if ( p.readyUnary() ) {
	        	p.reduceStackUnary();
	//        	System.out.print("pilha: ");
	        	p.top();
	        }
			
			if ( p.readyBinary() ) {
				p.reduceStackBinary();
	//			System.out.print("pilha: ");
	        	p.top();
			}
			
			cont++;
		}

	//	System.out.print("Fim da subexpressao: ");
		p.top();

		return p.pop();
	}

	String stringFormula() {
		return stringPsi;
	}

	int qtdAtomicas() {
		if (psi != null) {
			Set set = new HashSet();

			for (String c : psi) 
				if (c.compareTo("A") == 0 || c.compareTo("B") == 0 || c.compareTo("C") == 0 || c.compareTo("D") == 0 || c.compareTo("E") == 0) 
					set.add(c);

			if (set.size() != 0) return set.size();
		}

		return 0;
	}

	Atomica[] obterAtomicas() {
		return atomicas;
	}

}

/*public class Leitor {
	String stringPsi;
	String[] psi;
	Atomica[] atomicas;

	Leitor(String form) {
		stringPsi = form.toUpperCase();
		psi = form.toUpperCase().split("");
		atomicas = new Atomica[ this.qtdAtomicas() ];
	}
	
	String stringFormula() {
		return stringPsi;
	}

	int qtdAtomicas() {
		int i = 0;

		for (String c : psi)
			if (c.compareTo("A") == 0 || c.compareTo("B") == 0 || c.compareTo("C") == 0 || c.compareTo("D") == 0 || c.compareTo("E") == 0)
				++i;

		return i;
	}

	Atomica[] obterAtomicas() {
		return atomicas;
	}

	int fimSubForm(String[] phi, int ini) {
		int i, qtdParentesis = 0;

		for (i = ini; i < phi.length; ++i)
		{
			if ( phi[i].compareTo("(") == 0 ) ++qtdParentesis;
			
			if ( phi[i].compareTo(")") == 0 )
			{
				--qtdParentesis;
				
				if ( qtdParentesis == 0 )
				{
					if ( i < phi.length-1 && phi[i+1].compareTo("'") == 0 ) return i+1; // CONSIDERANDO QUE PODE HAVER O SIMBOLO DA NEGAÇÃO APÓS O PARENTESE
					return i;
				}

			}
		}

		return 0;
	}
	
	Formula lerFormula() {
		int i, f;
		Formula aux;
		Pilha p = new Pilha();

		System.out.println("Expressao: ");
		for (i = 0; i < psi.length; i++) System.out.print(i+" ");
		System.out.println();
		for (String c : psi) System.out.print(c+" ");

		for (i = 0; i < psi.length; i++) 
		{
			System.out.println("\nRODADA: "+(i+1)+"\n");

			if ( psi[i].compareTo("(") == 0 ) 
			{
				f = fimSubForm(psi, i);
				System.out.println("abre: "+psi[i]+" "+i+"\tfecha: "+psi[f]+" "+f);
				
				p.push(lerFormula(++i, f));

				i = f;
			} 
			else 
			{
				if (psi[i].compareTo(")") != 0) {
					aux = criarForm( psi[i] );
					p.push(aux);
				}
				else {
					System.out.println("paretense: "+psi[i]);
				}
			}
	        
	        if ( p.readyUnary() ) {
	        	p.reduceStackUnary();
	        	System.out.print("pilha: ");
	        	p.top();
	        }
			
			if ( p.readyBinary() ) {
				p.reduceStackBinary();
				System.out.print("pilha: ");
	        	p.top();
			}
		}
		
		return p.pop();
	}

	Formula lerFormula(int ini, int fim) {
		int i, f, cont = 1;
		Formula aux;
		Pilha p = new Pilha();

		System.out.println("\nSubexpressao: ");
		for (i = ini; i <= fim; ++i) System.out.print(i+" ");
		System.out.println();
		for (i = ini; i <= fim; ++i) System.out.print(psi[i]+" ");

		for (i = ini; i <= fim; i++)
		{
			System.out.println("\nRODADA: "+cont+"\n");

			if ( psi[i].compareTo("(") == 0 ) 
			{
				f = fimSubForm(psi, i);
				System.out.println("abre: "+psi[i]+" "+i+"\tfecha: "+psi[f]+" "+f);
				
				p.push(lerFormula(++i, f));

				i = f;
			} 
			else 
			{
				if (psi[i].compareTo(")") != 0) {
					aux = criarForm( psi[i] );
					p.push(aux);
				}
				else {
					System.out.println("paretense: "+psi[i]);
				}
			}
	        
	        if ( p.readyUnary() ) {
	        	p.reduceStackUnary();
	        	System.out.print("pilha: ");
	        	p.top();
	        }
			
			if ( p.readyBinary() ) {
				p.reduceStackBinary();
				System.out.print("pilha: ");
	        	p.top();
			}
			
			cont++;
		}

		System.out.print("Fim da subexpressao: ");
		p.top();

		return p.pop();
	}

	Formula criarForm(String phi) {
		Formula aux;
	    
	    if (phi.compareTo("A") == 0 || phi.compareTo("B") == 0 || phi.compareTo("C") == 0 || phi.compareTo("D") == 0 || phi.compareTo("E") == 0) 
		{
			System.out.println("string: "+phi);
			aux = new Atomica(phi);

			//for (int i = atomicas.length-1; i >= 0; --i)
			for (int i = 0; i < atomicas.length; ++i)
			{
				if (atomicas[i] == null)
				{
					atomicas[i] = (Atomica)aux;
					break;
				}
			}
		}
		else {
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
		    		aux = null;
		    		break;
		    }
		    
		    System.out.println("conectivo: "+phi);
		}

	    return aux;
	}

}/* 
             LEITOR ANTIGO

public class Leitor {


	
	Leitor(String form) {
		formula = form.split("");

	}

	Formula criarForm(String phi) {
		Formula aux;
	    
	    if (phi.toUpperCase().compareTo("A") == 0 || phi.toUpperCase().compareTo("B") == 0 || phi.toUpperCase().compareTo("C") == 0 || phi.toUpperCase().compareTo("D") == 0 || phi.toUpperCase().compareTo("E") == 0) 
		{
			aux = new Atomica(phi.toUpperCase());
		}
		else {
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
		    		aux = null;
		    		break;
		    }
		}

	    return aux;
	}
	
	int fimSubForm(String[] phi, int ini) {
		int i, qtdParentesis = 0;

		for (i = ini; i < phi.length; ++i)
		{
			if ( phi[i].compareTo("(") == 0 )
			{
				++qtdParentesis;
			}
			
			if ( phi[i].compareTo(")") == 0 )
			{
				if ( qtdParentesis == 0 )
				{
					if ( i < phi.length && phi[i+1].compareTo("'") == 0 ) return i+1; // CONSIDERANDO QUE PODE HAVER O SIMBOLO DA NEGAÇÃO APÓS O PARENTESE
					return i;
				}

				--qtdParentesis;
			}
		}

		return -1; // FAZER O TRY CATCH
	}

	Formula lerFormula(String[] phi, int ini, int fim) {
		int i, f;
		Formula aux;
		Pilha p = new Pilha();
		
		for (i = ini; i < fim; ++i) 
		{
			if ( phi[i].compareTo("(") == 0 ) 
			{
				f = fimSubForm(phi, ++i);
				
				p.push(lerFormula(phi, ++i, f));

				i = f;
			} 
			else 
			{
				aux = criarForm( phi[i] );

				p.push(aux);
			}

	        if ( p.readyUnary() ) p.reduceStackUnary();
			
			if ( p.readyBinary() ) p.reduceStackBinary();
		}
		
		return p.pop();
	}
}
*/