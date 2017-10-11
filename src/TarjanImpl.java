import java.util.HashMap;
import java.util.Stack;


public class TarjanImpl {
	
	Stack<ParaphraseVertex> stack;
	HashMap<ParaphraseVertex, Boolean> onStack;
	int index;
	int cycleNumber;
	ParaphraseDigraph digraph;

	public TarjanImpl(ParaphraseDigraph digraph) {
		
		// Initialize HashMap
		onStack = new HashMap<ParaphraseVertex, Boolean>();
		
		// Initialize Digraph
		this.digraph = digraph;
		
		// Initialize onStack
		for(ParaphraseVertex v: digraph.vertices){
			onStack.put(v, false);
		}
		
		// Initialize cycle number
		cycleNumber = 1;
		
		// Initialize stack
		stack = new Stack<ParaphraseVertex>();
		// Begin loop
		index = 0;
		for(ParaphraseVertex v: digraph.vertices){
			if(v.index == Integer.MAX_VALUE){
				strongConnect(v);
			}
		}
	}
	
	public void strongConnect(ParaphraseVertex v){
		
		// Set depth index for v to the smallest unused index
		v.index = this.index;
		v.lowlink = this.index;
		index++;
		stack.push(v);
		onStack.put(v, true);
		
		// Consider successors of v
		if(digraph.neighbours(v) != null){
			for(ParaphraseVertex w: digraph.neighbours(v)){
				// Successor not yet visited; recurse on it
				if(w.index == Integer.MAX_VALUE){
					strongConnect(w);
					v.lowlink = Math.min(v.lowlink, w.lowlink);
				} 
				
				// Successor w is in stack and hence in current SCC
				else if(onStack.get(w)){
					v.lowlink = Math.min(v.lowlink, w.index);
				}
				
			}
		}
		
		// If v is a root node, pop the stack and generate an SCC
		if(v.lowlink == v.index) {
			Stack<ParaphraseVertex> SCC = new Stack<ParaphraseVertex>();
			ParaphraseVertex w = stack.pop();
			while(w != v){
				onStack.put(w, false);
				SCC.push(w);
				w = stack.pop();
			}
			SCC.push(v);
			if(SCC.size() > 2){
				System.out.println("I found a cycle! Cycle number: " + cycleNumber);
				while(!SCC.empty()){
					System.out.println(SCC.pop().ID);
				}
				cycleNumber++;
				System.out.println();
			}
		}
		
	}

}
