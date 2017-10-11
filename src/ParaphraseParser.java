import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ParaphraseParser {
	
	public ParaphraseDigraph digraph;

	public ParaphraseParser(String fileName) throws FileNotFoundException, IOException {
		
		// Create the Digraph
		digraph = new ParaphraseDigraph();
		
		// Get input file
		File file = new File(fileName);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    int i = 1;
		    // Parsing each line
		    while ((line = br.readLine()) != null) {
		    	String[] splitted = line.split("\\|");
		    	// Pattern matching entailment
		    	switch (splitted[2].trim()){
		    		case "ForwardEntailment":
//		    			System.out.println("This is a Forward Entailment!");
		    			digraph.addLink(splitted[0].trim(), splitted[1].trim());
		    			break;
		    		case "ReverseEntailment":
//		    			System.out.println("This is a Reverse Entailment!");
		    			digraph.addLink(splitted[1].trim(), splitted[0].trim());
		    			break;
		    		case "Equivalence":
//		    			System.out.println("This is an Equivalence!");
		    			digraph.addLink(splitted[1].trim(), splitted[0].trim());
		    			digraph.addLink(splitted[0].trim(), splitted[1].trim());
		    			break;
		    	}
		    	System.out.println("Processing entailment number: " + i);
		    	i++;
		    }
		}

		@SuppressWarnings("unused")
		TarjanImpl tarjan = new TarjanImpl(digraph);
	}

}
