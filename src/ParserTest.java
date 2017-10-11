import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;


public class ParserTest {
	
	@Test
	public void testParse() throws FileNotFoundException, IOException {
		@SuppressWarnings("unused")
		ParaphraseParser test = new ParaphraseParser("entdata.txt");
	}

}
