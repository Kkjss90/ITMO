public class App {
    public static void main(String[] args) {

        if (args.length > 0) {
            if (!args[0].equals("")) {
                Application application = new Application();
                application.start(args[0]);
            }
        } else {
            Application application = new Application();
            application.start("/Users/a11/IdeaProjects/ITMO/prog_labs/lab_5/try.json");
        }
    }
}