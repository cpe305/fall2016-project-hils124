package abandoned.game;
import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import abandoned.house.House;

public class GameBuilder {
  public static House initGame() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      House house = mapper.readValue(new File("HouseData.json"), House.class);
      return house;
      
    } catch (JsonGenerationException ex) {
      ex.printStackTrace(); 
    } catch (JsonMappingException ex) {
      ex.printStackTrace();  
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }
}
