package abandoned.commands;

/**
 * Class to model a help command.
 * @author hils124
 */
public class HelpCommand implements Command {
  private CommandProcessor cmdProcess;

  /**
   * Public constructor.
   * @param cmdProcess - specifies cmdProcessor
   */
  public HelpCommand(CommandProcessor cmdProcess) {
    this.cmdProcess = cmdProcess;
  }

  @Override
  public void execute() {
    cmdProcess.help();
  }
}