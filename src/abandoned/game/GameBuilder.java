package abandoned.game;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import abandoned.house.House;

public class GameBuilder {
  public static House initGame() {
    Logger logger = Logger.getLogger("HouseBuilder");
    try {
      ObjectMapper mapper = new ObjectMapper();
      House house = mapper.readValue(new File("resources/HouseData.json"), House.class);
      return house;
      
    } catch (JsonGenerationException ex) {
      logger.log(Level.SEVERE, "Uncaught exception", ex); 
    } catch (JsonMappingException ex) {
      logger.log(Level.SEVERE, "Uncaught exception", ex);  
    } catch (IOException ex) {
      logger.log(Level.SEVERE, "Uncaught exception", ex); 
    }
    return null;
  }
}
