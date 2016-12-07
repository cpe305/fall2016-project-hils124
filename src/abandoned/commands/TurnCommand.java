package abandoned.commands;

/**
 * Class to model a turn command.
 * @author hils124
 */
public class TurnCommand implements Command {
  private CommandProcessor cmdProcess;

  public TurnCommand(CommandProcessor cmdProcess) {
    this.cmdProcess = cmdProcess;
  }

  @Override
  public void execute() {
    cmdProcess.turn();
  }
}