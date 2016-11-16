package abandoned.game;

import abandoned.house.House;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class to build the game from file data.
 * @author hils124
 */
public class GameBuilder {
  
  /**
   * Builds the house from file data.
   * @return initial house state
   */
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
