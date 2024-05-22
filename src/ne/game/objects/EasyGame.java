package ne.game.objects;
import org.newdawn.slick.*;

public class EasyGame extends BasicGame {

    private Image background;
    private Player2 player2;
    private Player1 player1;
    private Projektil1 projektil1;
    private Projektil2 projektil2;
    private Sound sound;
    private Music music;
    private int lautstärke = 0;
    private int hitPlayer1 = 0;
    private int hitPlayer2 = 0;
    private AngelCodeFont font;
    private Animation animation;
    private boolean planecolission = false;
    private boolean plane1hit = false;
    private boolean plane2hit = false;

    private int timer =0;

    public EasyGame() {
        super("EasyGame");
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer container = new AppGameContainer(new EasyGame());
        container.setDisplayMode(1920,1080 , true);
        //container.setClearEachFrame(false);
        container.setMinimumLogicUpdateInterval(25);
        container.setTargetFrameRate(60);
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
            background = new Image("assets/pics/background.jpg");
            player1 = new Player1(480,540,new Image("assets/pics/Player1.png"),container.getInput());
            player2 = new Player2(1440, 540,new Image("assets/pics/Player2.png"),container.getInput());
            projektil1 = new Projektil1(480,540,new Image("assets/pics/Projektil1.png"),container.getInput());
            projektil2 =  new Projektil2 (1440,540,new Image("assets/pics/Projektil2.png"),container.getInput());
            music = new Music("testdata/testloop.ogg");
            sound = new Sound("testdata/burp.aif");
            music.loop();

        }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();

        if (planecolission){
            //animation + pause hier einfügen
            timer += delta;
            if (timer>=2000) {
                planecolission = false;
                timer = 0;
            }
        } if (plane2hit){
            timer += delta;
            if(timer>=1000) {
                plane2hit=false;
                timer=0;
            }
        } if (plane1hit) {
            timer += delta;
            if (timer >= 1000) {
                plane1hit = false;
                timer = 0;
                }
            } else {
                if (input.isKeyPressed(Input.KEY_ESCAPE)) {
                    container.exit();
                }
                if (input.isKeyPressed(Input.KEY_1)) {
                    lautstärke = lautstärke + 1;
                    if (lautstärke >= 10) lautstärke = 10;
                    music.setVolume(lautstärke / 10f);
                }
                if (input.isKeyPressed(Input.KEY_2)) {
                    lautstärke = lautstärke - 1;
                    if (lautstärke < 1) lautstärke = 0;
                    music.setVolume(lautstärke / 10f);
                }
                player2.update(delta);
                player1.update(delta);
                projektil1.update(delta);
                projektil2.update(delta);

                if (player1.intersects(player2.getShape())) {
                    planecolission = true;
                    hitPlayer1 = 0;
                    hitPlayer2 = 0;
                    player1.setX(480);
                    player1.setY(540);
                    player2.setX(1440);
                    player2.setY(540);
                    projektil1.setX(480);
                    projektil1.setY(540);
                    projektil2.setX(1440);
                    projektil2.setY(540);
                }
                if (projektil1.intersects(player2.getShape())) {
                    plane2hit = true;
                    hitPlayer1++;
                    player1.setX(480);
                    player1.setY(540);
                    player2.setX(1440);
                    player2.setY(540);
                    projektil1.setX(480);
                    projektil1.setY(540);
                    projektil2.setX(1440);
                    projektil2.setY(540);
                }
                if (projektil2.intersects(player1.getShape())) {
                    plane1hit = true;
                    hitPlayer2++;
                    player1.setX(480);
                    player1.setY(540);
                    player2.setX(1440);
                    player2.setY(540);
                    projektil1.setX(480);
                    projektil1.setY(540);
                    projektil2.setX(1440);
                    projektil2.setY(540);
                }
            }
        }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        background.draw();
        projektil1.draw(g);
        projektil2.draw(g);
        player2.draw(g);
        player1.draw(g);
        font.drawString(960, 25, "Player 1 => "+ hitPlayer1+":" + hitPlayer2 + "<= Player 2", Color.black);
        if (planecolission) {
            font.drawString(960, 540, "YOU DIED", Color.red);
        }
        if (plane1hit){
            font.drawString(960,540,"Player 1 HIT!!!", Color.red);
        }
        if (plane2hit) {
            font.drawString(960,540,"Player 2 HIT!!!", Color.red);
        }
    }
}



