package abandoned.commands;

/**
 * Class to model a view command.
 * @author hils124
 */
public class ViewCommand implements Command {
  private CommandProcessor cmdProcess;

  public ViewCommand(CommandProcessor cmdProcess) {
    this.cmdProcess = cmdProcess;
  }

  @Override
  public void execute() {
    cmdProcess.view();
  }
}