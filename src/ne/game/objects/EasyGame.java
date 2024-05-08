package ne.game.objects;
import org.newdawn.slick.*;

public class EasyGame extends BasicGame {

    private Player2 player2;
    private Image background;
    private Player2 Player2;
    private Player1 player1;
    private Sound sound;
    private Music music;
    private int lautstärke = 0;
    private int hitPlayer1 = 0;
    private int hitPlayer2 = 0;
    private AngelCodeFont font;
    private Animation animation;

    public EasyGame() {
        super("EasyGame");
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer container = new AppGameContainer(new EasyGame());
        container.setDisplayMode(1920,1080 , true);
        //container.setClearEachFrame(false);
        container.setMinimumLogicUpdateInterval(25);
        container.setTargetFrameRate(144);
        container.setShowFPS(true);
        container.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        //animation = new Animation();
        //PackedSpriteSheet pss = new PackedSpriteSheet("res/animation/player1.def");
        //for (int i=1;i<=11;i++) {
        //    animation.addFrame(pss.getSprite("flame_" + i + ".png"), 100);
        //}
            font = new AngelCodeFont("testdata/demo2.fnt","testdata/demo2_00.tga");
            background = new Image("assets/pics/background.png");
            Player2 = new Player2(1440, 540, new Image("assets/pics/Player1.png"),container.getInput());
            player1 = new Player1(480,540,new Image("assets/pics/Player2.png"),container.getInput());
            music = new Music("testdata/testloop.ogg");
            sound = new Sound("testdata/burp.aif");
            music.loop();

        }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            container.exit();
        }
        if (input.isKeyPressed(Input.KEY_UP)){
            lautstärke = lautstärke +1;
            if (lautstärke >=10) lautstärke = 10;
            music.setVolume(lautstärke/10f);
        }
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            lautstärke = lautstärke - 1;
            if (lautstärke < 1) lautstärke = 0;
            music.setVolume(lautstärke / 10f);
        }
        Player2.update(delta);
        player1.update(delta);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        background.draw();
        Player2.draw(g);
        player1.draw(g);
        font.drawString(8, 25, "Player 1: "+ hitPlayer1, Color.black);
        font.drawString(8, 50, "Player 2: "+ hitPlayer2, Color.black);
    }
}



