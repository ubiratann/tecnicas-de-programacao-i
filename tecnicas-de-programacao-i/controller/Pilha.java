public class Pilha {
	Formula[] pilha = new Formula[3];
	int topo = -1;

	int size() {
		if ( empty() ) return 0;
		return topo+1;
	}

	boolean empty() {
		if ( topo == -1 ) return true;
		return false;
	}

	boolean flow() {
		if ( topo == 2 ) return true;
		return false;
	}
	
	void push(Formula f) {
		if (flow()) {
			System.out.println("Error: overflow");

		} else {
			++topo;
			
			pilha[topo] = f;
		}
	}

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

	void top() {
		if (empty()) System.out.println("Error: stack empty");

		System.out.println(pilha[topo]);
	}

	void reduceStackUnary() {
		Conectivo conecUn = (Conectivo)pop();

		if (isAtomica())
			conecUn.add( (Atomica)pop() );
		else
			conecUn.add( (Conectivo)pop() );

		push(conecUn);
	}
	
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

	boolean readyUnary() {
		if ( pilha[topo] instanceof Negacao && size() == 2 ) return true;
		return false;
	}
	
	boolean readyBinary() {
		if (flow()) return true;
		return false;
	}

	boolean isAtomica() {
		if (empty()) System.out.println("Error: stack empty");

		if ( pilha[topo] instanceof Atomica ) return true;

		return false;
	}
}

/* PILHA ANTIGA

public class Pilha {
	Formula[] pilha = new Formula[3];
	int topo = -1;

	boolean empty() {
		if ( topo == -1 ) return true;
		return false;
	}

	boolean flow() {
		if ( topo == 2 ) return true;
		return false;
	}
	
	void push(Formula f) {
		if (flow()) {
			System.out.println("Error: overflow");

		} else {
			++topo;
			
			pilha[topo] = f;
		}
	}

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

	void reduceStackUnary() {
		Conectivo conecUn = (Conectivo)pop();
		
		conecUn.add( (Atomica)pop() );
		
		push(conecUn);
	}
	
	void reduceStackBinary() {
		Atomica atDir = (Atomica)pop();

		Conectivo conecBin = (Conectivo)pop();
		
		conecBin.add( pop(), atDir );

		push(conecBin);
	}

	boolean readyUnary() {
		if ( pilha[topo] instanceof Negacao ) return true;
		return false;
	}
	
	boolean readyBinary() {
		if ( pilha[topo] instanceof Atomica && flow() ) return true;
		return false;
	}
}*/