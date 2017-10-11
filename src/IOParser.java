
public class IOParser {
	
	public ParaphraseDigraph digraph;
	int i = 1;

	public IOParser() {
		digraph = new ParaphraseDigraph();
	}

	public void add(String line) {
		if(line != null){
	    	String[] splitted = line.split("\\|");
	    	// Pattern matching entailment
	    	switch (splitted[2].trim()){
	    		case "ForwardEntailment":
//	    			System.out.println("This is a Forward Entailment!");
	    			digraph.addLink(splitted[0].trim(), splitted[1].trim());
	    			break;
	    		case "ReverseEntailment":
//	    			System.out.println("This is a Reverse Entailment!");
	    			digraph.addLink(splitted[1].trim(), splitted[0].trim());
	    			break;
	    		case "Equivalence":
//	    			System.out.println("This is an Equivalence!");
	    			digraph.addLink(splitted[1].trim(), splitted[0].trim());
	    			digraph.addLink(splitted[0].trim(), splitted[1].trim());
	    			break;
	    	}
	    	System.out.println("Processing entailment number: " + i);
	    	i++;
		}
	}

	public void runTarjan() {
		@SuppressWarnings("unused")
		TarjanImpl tarjan = new TarjanImpl(digraph);
	}

}
