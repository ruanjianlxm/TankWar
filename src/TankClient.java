import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;

 
public class TankClient extends Frame{
  public static final  int GAME_WIDTH = 800;
  public static final  int GAME_HEIGHT = 600;
  
	  Tank myTank = new Tank(50, 550,true,Tank.Direction.STOP,this);
	  List<Missile>  missiles = new ArrayList<Missile>();
	  List<Explode>  explodes = new ArrayList<Explode>();
	  List<Tank>  tanks= new ArrayList<Tank>();
	  Image offScreenImage =null;
	  Wall w1 =new Wall(100, 200, 30, 150, this),w2= new Wall(300, 500, 300, 20, this);
	  
	public void paint(Graphics g) {
		g.drawString("missiles count:"+missiles.size(), 10, 50);
		g.drawString("explodes count:"+explodes.size(), 10, 70);
		g.drawString("Tanks count:"+tanks.size(), 10, 90);
		g.drawString("Tanks life:"+myTank.getLife(), 10, 110);
		for( int i= 0;i<missiles.size();i++){
			Missile m = missiles.get(i);
		    m.hitTanks(tanks);
		    m.hitTank(myTank);
		    m.hitWall(w2);
			m.hitWall(w1);
			m.draw(g);
			
		}
		
		for(int i=0; i<explodes.size();i++){
			Explode e =explodes.get(i);
			e.draw(g);
		}
		for(int i=0;i<tanks.size();i++){
			Tank t = tanks.get(i);
			t.collidesWithWall(w1);
			t.collidesWithWall(w2);
			t.collideWithTanks(tanks);
			t.draw(g);
		}
		myTank.draw(g);
		w1.draw(g);
		w2.draw(g);
	

	}
	
    public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.GREEN);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	public void lauchFrame() {
		
		for(int i=0;i<=10;i++){
			tanks.add(new Tank(50+40*(i+1),50,false,Tank.Direction.D,this));
		}
		
    	this.setLocation(400,300);
    	this.setSize(GAME_WIDTH,GAME_HEIGHT);
    	this.setTitle("TankWar");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
    	this.setBackground(Color.GREEN);
		this.addKeyListener(new KeyMonitor());
		setVisible(true); 
		new Thread(new PaintThread()).start();
		
	}
	
	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.lauchFrame();
		
	}
	private class PaintThread implements Runnable {
		
		public void run() {
		  while(true){
			  repaint();
			  try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
			
		}
		
		
	}

	private class KeyMonitor extends KeyAdapter{
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			myTank.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			 myTank.keyPressed(e);
			 
		}
		
	}
}
