import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Missile {
	public static final int XSPEAD = 10;
	public static final int YSPEAD = 10;
	 public static final int WIDTH = 10;
	 public static final int HEIGHT = 10;

	int x, y;
	Tank.Direction dir;
    private boolean Live = true;
	private TankClient tc; 
	public boolean isLive() {
		return Live;
	}

	public Missile(int x, int y, Tank.Direction dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public Missile (int x, int y, Tank.Direction dir, TankClient tc) {
		this(x, y, dir);
		this.tc=tc;
		
		
	}

	public void draw(Graphics g) {
		
		if(!Live){
			tc.missiles.remove(this);
			return;
			
		}
		Color c = g.getColor();
		g.setColor(Color.black);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		move();
	}

	private void move() {

		switch (dir) {
		case L:
			x -= XSPEAD;
			break;
		case LU:
			x -= XSPEAD;
			y -= YSPEAD;
			break;
		case U:

			y -= YSPEAD;
			break;
		case RU:
			x += XSPEAD;
			y -= YSPEAD;
			break;
		case R:
			x += XSPEAD;

			break;
		case RD:
			x += XSPEAD;
			y += YSPEAD;
			break;
		case D:

			y += YSPEAD;
			break;
		case LD:
			x -= XSPEAD;
			y += YSPEAD;
			break;

		}
		if(x<0||y<0||x>TankClient.GAME_WIDTH||y>TankClient.GAME_HEIGHT){
			Live = false;
			
		}

	}
	public Rectangle getRect(){
		return new Rectangle(x,y,WIDTH,HEIGHT);
	}
	
	public boolean hitTank(Tank t){
		if(this.getRect().intersects(t.getRect()) &&t.isLive() ){
			t.setLive(false);
			this.Live = false;
			
			Explode e =new Explode(x, y, tc);
			tc.explodes.add(e);
			
			return true;
		}
		return false;
		
	}

}
