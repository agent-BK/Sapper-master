import game.InitGame;

public class StartGame {

    private static int TIME = 5000;

    public static void main(String[] args) {
        SplashScreen splash = new SplashScreen(TIME);
        splash.showSplashAndExit();
        new InitGame();
    }

}
