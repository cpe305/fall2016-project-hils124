import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class GameBuilder {
	public static void initGame() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			House house = mapper.readValue(new File("HouseData.json"), House.class);
			house.printHouse();
		}
		catch (JsonGenerationException e) {
			e.printStackTrace();
		} 
		catch (JsonMappingException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	