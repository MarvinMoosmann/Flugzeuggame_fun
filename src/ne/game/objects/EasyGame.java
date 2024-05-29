package ne.game.objects;
import org.newdawn.slick.*;

public class EasyGame extends BasicGame {

    private Image background;
    private Player player1;
    private Player player2;
    private Projektil projektil1;
    private Projektil projektil2;
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
            projektil1 = new Projektil(-300,-100,new Image("assets/pics/Projektil1klein.png"),container.getInput());
            projektil2 = new Projektil (-400,-100,new Image("assets/pics/Projektil2klein.png"),container.getInput());
            player1 = new PlaneA(480,540,new Image("assets/pics/Player1klein.png"),container.getInput(),projektil1);
            player2 = new PlaneB(1440, 540,new Image("assets/pics/Player2klein.png"),container.getInput(),projektil2);
            music = new Music("testdata/testloop.ogg");
            sound = new Sound("testdata/burp.aif");
            music.loop();

        }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        Input input = container.getInput();
        if (planecolission){
            //animation hier einfügen
            timer += delta;
            if (timer>=2000) {
                planecolission = false;
                timer = 0;
            }
        } else if (plane2hit){
            //animation hier einfügen
            plane2hit=false;
        } else if (plane1hit) {
            //animation hier einfügen
            plane1hit = false;
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
                    projektil1.setX(-300);
                    projektil1.setY(-100);
                    projektil2.setX(-400);
                    projektil2.setY(-100);
                    sound.play();
                }
                if (projektil1.intersects(player2.getShape())) {
                    plane2hit = true;
                    hitPlayer1++;
                    player2.setX(1440);
                    player2.setY(540);
                    projektil1.setX(-300);
                    projektil1.setY(-100);
                    projektil2.setX(-400);
                    projektil2.setY(-100);
                    sound.play();
                    projektil1.setVisible(false);
                }
                if (projektil2.intersects(player1.getShape())) {
                    plane1hit = true;
                    hitPlayer2++;
                    player1.setX(480);
                    player1.setY(540);
                    projektil1.setX(-300);
                    projektil1.setY(-100);
                    projektil2.setX(-400);
                    projektil2.setY(-100);
                    sound.play();
                    projektil2.setVisible(false);
                }
                if(projektil1.intersects(projektil2.getShape())){
                    projektil1.setX(-300);
                    projektil1.setY(-100);
                    projektil2.setX(-400);
                    projektil2.setY(-100);
                    sound.play();
                    projektil2.setVisible(false);
                    projektil1.setVisible(false);
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
        font.drawString(790, 25, "Player 1 => "+ hitPlayer1+":" + hitPlayer2 + "<= Player 2", Color.red);
        if (planecolission) {
            font.drawString(900, 540, "YOU DIED", Color.red);
        }
    }
}



