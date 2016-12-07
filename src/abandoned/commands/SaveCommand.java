package abandoned.commands;

/**
 * Class to model a save command.
 * @author hils124
 */
public class SaveCommand implements Command {
  private CommandProcessor cmdProcess;

  public SaveCommand(CommandProcessor cmdProcess) {
    this.cmdProcess = cmdProcess;
  }

  @Override
  public void execute() {
    cmdProcess.save();
  }
}