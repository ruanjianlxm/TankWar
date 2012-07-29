import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.*;


public class TankClient extends Frame{
 
	public void paint(Graphics g) {
	Color c = g.getColor();
	g.setColor(Color.RED);
	g.fillOval(50, 50, 30, 30);
	g.setColor(c);
	}

	public void launchFrame() {
    	this.setLocation(400,300);
    	this.setSize(800,600);
    	this.setBackground(Color.GREEN);
    	setVisible(true); 
    	this.setTitle("TankWar");
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		
	}
	
	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.launchFrame();
		
	}

}
