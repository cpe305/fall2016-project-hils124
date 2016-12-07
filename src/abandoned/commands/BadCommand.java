package abandoned.commands;

/**
 * Class to model a bad command.
 * @author hils124
 */
public class BadCommand implements Command {
  private CommandProcessor cmdProcess;

  public BadCommand(CommandProcessor cmdProcess) {
    this.cmdProcess = cmdProcess;
  }

  @Override
  public void execute() {
    cmdProcess.bad();
  }
}