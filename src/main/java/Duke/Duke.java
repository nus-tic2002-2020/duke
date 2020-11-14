package Duke;

/**
 * Represents Duke
 */
public class Duke {
    //public Storage storage;
    public TaskList tasks;
    public Ui ui;
    public Storage storage;


    /**
     * Constructs a Duke object.
     *
     * @param filePath File path to the file that stores the Tasks data
     * @param ui Ui for display
     * @param storage storage to load and save to text file
     * @param tasks TaskList to store existing task from text file and also store new tasks added
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.askInput();
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("test.txt").run();
    }
}
