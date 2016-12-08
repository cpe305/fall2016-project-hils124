package abandoned.commands;

/**
 * Class to initialize all command constants.
 * @author hils124
 */
public class Initialize {
  
  public static final CommandProcessor cmdProcess = new CommandProcessor();
  public static final BadCommand badCmd = new BadCommand(cmdProcess);
  public static final DescribeCommand describeCmd = new DescribeCommand(cmdProcess);
  public static final EnterCommand enterCmd = new EnterCommand(cmdProcess);
  public static final HelpCommand helpCmd = new HelpCommand(cmdProcess);
  public static final InspectCommand inspectCmd = new InspectCommand(cmdProcess);
  public static final QuitCommand quitCmd = new QuitCommand(cmdProcess);
  public static final SaveCommand saveCmd = new SaveCommand(cmdProcess);
  public static final TakeCommand takeCmd = new TakeCommand(cmdProcess);
  public static final TurnCommand turnCmd = new TurnCommand(cmdProcess);
  public static final ViewCommand viewCmd = new ViewCommand(cmdProcess);
  public static final UseCommand useCmd = new UseCommand(cmdProcess);
  public static final Invoker invoker = new Invoker();
  
  private Initialize() {
    throw new IllegalAccessError("Initializer class");
  }
}
