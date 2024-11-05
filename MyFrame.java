package multi.threading;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MyFrame extends JFrame implements Runnable, KeyListener{
	int barPosX = 0;
	int barPosY = 0;
	MyFrame(){
		Thread t =new Thread(this);
		t.start();
	}
	@Override
	public void run() {
		while(true) {
			for(;barPosX<=454;barPosX+=5) {
				repaint();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLUE);
		BufferedImage image = null, image2 = null;
		
		try {
			image = ImageIO.read(new File("apple.png"));
//			image2 = ImageIO.read(new File("plane.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		g.drawImage(image, barPosY, barPosX, 50, 50, null);
		g.drawImage(image2,  barPosX, 50, 100, 100, null);
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if(e.getKeyCode()==e.VK_LEFT)
			barPosY -=5;
		else if(e.getKeyCode()==e.VK_RIGHT)
			barPosY +=5;
		else if(e.getKeyCode()==e.VK_UP)
			barPosX -=5;
		else if(e.getKeyCode()==e.VK_DOWN)
			barPosX +=5;
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//When close on cross button then program will terminate
		frame.addKeyListener(frame);
		frame.setVisible(true);
	}
}
