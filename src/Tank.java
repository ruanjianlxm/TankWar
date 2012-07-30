import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.nio.Buffer;


public class Tank {
 public static final int XSPEAD = 5;
 public static final int YSPEAD = 5;
	int x,y;
	
	private boolean bL = false, bU = false,bR = false , bD = false;
	enum Direction {L,LU,U,RU,R,RD,D,LD,STOP};
	private Direction dir = Direction.STOP;
	
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, 30, 30);
		g.setColor(c);
		move();
		
	}
	
	void move(){
		switch (dir) {
		case L:
			x-=XSPEAD;
			break;
		case LU:
			x-=XSPEAD;
			y-=YSPEAD;
			break;
		case U:
		
			y-=YSPEAD;
			break;
		case RU:
			x+=XSPEAD;
			y-=YSPEAD;
			break;
		case R:
			x+=XSPEAD;
		
			break;
		case RD:
			x+=XSPEAD;
			y+=YSPEAD;
			break;
		case D:
		
			y+=YSPEAD;
			break;
		case LD:
			x-=XSPEAD;
			y+=YSPEAD;
			break;
		case STOP:
			break;
		
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
	      switch (key) {
		   case KeyEvent.VK_LEFT:
			   bL=true;
			
		       break;
		   case KeyEvent.VK_UP:
			   bU=true;
			 
		        break;
		   case KeyEvent.VK_RIGHT:
			   bR=true;
			 
		        break;
		   case KeyEvent.VK_DOWN:
			   bD=true;
			 
		   break;		
		
		}
	      locateDirection();
	}
	void locateDirection(){
		if(bL && !bU && !bR && !bD) dir= Direction.L;
		else if(bL &&  bU && !bR && !bD) dir= Direction.LU;
		else if(!bL && bU && !bR && !bD) dir= Direction.U;
		else if(!bL && bU && bR && !bD) dir= Direction.RU;
		else if(!bL && !bU && bR && !bD) dir= Direction.R;
		else if(!bL && !bU && bR && bD) dir= Direction.RD;
		else if(!bL && !bU && !bR && bD) dir= Direction.D;
		else if(bL && !bU && !bR && bD) dir= Direction.LD;
		else if(!bL && !bU && !bR && !bD) dir= Direction.STOP;
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
	      switch (key) {
		   case KeyEvent.VK_LEFT:
			   bL=false;
			
		       break;
		   case KeyEvent.VK_UP:
			   bU=false;
			 
		        break;
		   case KeyEvent.VK_RIGHT:
			   bR=false;
			 
		        break;
		   case KeyEvent.VK_DOWN:
			   bD=false;
			 
		   break;		
		
		}
	      locateDirection();
		
		
	}

}
