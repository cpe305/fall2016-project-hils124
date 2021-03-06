package abandoned.game;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class to save the game to file data.
 * @author hils124
 */
public class GameSaver {
  
  private GameSaver() {
    throw new IllegalAccessError("Builder class");
  }
  
  /**
   * Saves the house to file data.
   * @return initial house state
   */
  public static boolean saveGame() {
    ObjectMapper mapper = new ObjectMapper();
    Logger logger = Logger.getLogger("HouseSaver");
    try {
      // Convert object to JSON string and save into a file directly
      mapper.writeValue(new File("resources/saveHouse.json"), GlobalHouse.get());
      mapper.writeValue(new File("resources/savePlayer.json"), GlobalPlayer.get());
      return true;
      
    } catch (JsonGenerationException | JsonMappingException ex) {
      logger.log(Level.SEVERE, "Uncaught exception", ex); 
      return false;
    } catch (IOException ex) {
      logger.log(Level.SEVERE, "IO Exception", ex); 
      return false;
    } catch (Exception ex) {
      logger.log(Level.SEVERE, "Game didn't save", ex); 
      return false;
    }
  }
}
