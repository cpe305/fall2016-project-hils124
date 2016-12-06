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
 * Method to get single instance of a player
 * @return Player - instance of a player
 * @throws Exception - Player is not instantiated.
 */

public class GlobalHouse {
  private static House house;
  
  private GlobalHouse() {
    
  }
  
  /**
   * Method to get single instance of a house.
   * @return House - instance of a house
   */
  public static House get() {
    if (house == null) {
      house = newHouse();
    }
    return house;  
  }
  
  /**
   * Method to initialize single instance of a player.
   * @param isNew - true if the player needs to be new
   */
  public static void initializeHouse(boolean isNew) {
    if (house != null) {
      return;
    }
    if (isNew) {
      house = newHouse();
    } else {
      house = GameLoader.loadHouse();
    }
  }
  
  /**
   * Builds the house from new file data.
   * @return initial house state
   */
  private static House newHouse() { 
    Logger logger = Logger.getLogger("GlobalHouse");
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(new File("resources/HouseData.json"), House.class);
      
    } catch (JsonGenerationException | JsonMappingException ex) {
      logger.log(Level.SEVERE, "Uncaught exception", ex); 
    } catch (IOException ex) {
      logger.log(Level.SEVERE, "IO Exception", ex); 
    }
    return null;
  }
}
