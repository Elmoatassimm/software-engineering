import controller.LibraryController;
import view.LibraryView;

public class App {
    public static void main(String[] args) throws Exception {
        LibraryController controller = new LibraryController();
        LibraryView view = new LibraryView(controller);
        view.start();
    }
}
