package abandoned.commands;

/**
 * Class to model an inspect command.
 * @author hils124
 */
public class InspectCommand implements Command {
  private CommandProcessor cmdProcess;

  public InspectCommand(CommandProcessor cmdProcess) {
    this.cmdProcess = cmdProcess;
  }

  @Override
  public void execute() {
    cmdProcess.inspect();
  }
}