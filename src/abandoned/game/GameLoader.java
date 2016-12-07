package abandoned.game;

import abandoned.commands.Player;
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
public class GameLoader {
  
  private GameLoader() {
    throw new IllegalAccessError("Loader class");
  }
  
  /**
   * Builds the house from new file data or saved file data.
   * @return initial house state
   */
  public static House loadHouse() {
    Logger logger = Logger.getLogger("HouseLoader");
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(new File("resources/saveHouse.json"), House.class);
      
    } catch (JsonGenerationException | JsonMappingException ex) {
      logger.log(Level.SEVERE, "Uncaught exception", ex); 
    } catch (IOException ex) {
      logger.log(Level.SEVERE, "IO Exception", ex); 
    }
    return null;
  }
  
  /**
   * Builds the house from new file data or saved file data.
   * @return initial house state
   */
  public static Player loadPlayer() {
    Logger logger = Logger.getLogger("PlayerLoader");
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(new File("resources/savePlayer.json"), Player.class);  
      
    } catch (JsonGenerationException | JsonMappingException ex) {
      logger.log(Level.SEVERE, "Uncaught exception", ex); 
    } catch (IOException ex) {
      logger.log(Level.SEVERE, "IO Exception", ex); 
    }
    return null;
  }
}
