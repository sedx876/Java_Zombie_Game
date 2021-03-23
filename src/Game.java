import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
	
	public static int WIDTH = 800, HEIGHT = 600;
	public String title = "Zombie Game";
	
	private Thread thread;
	private boolean isRunning = false;
	
	public Game() {
		new Window(WIDTH, HEIGHT, title, this);
		start();
	}
	
	private synchronized void start() {
		if(isRunning) return;
		
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	private synchronized void stop() {
		if(!isRunning) return;
		
		try {
			thread.join();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		isRunning = false;
	}
	
	//GameLoop
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
			
	}
	
	private void tick() {
		//Updates game
	}
	
	private void render() {
		//renders the game
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.green);
		g.fillRect(0,  0,  WIDTH, HEIGHT);
		bs.show();
		g.dispose();
	}

	public static void main(String[] args) {
		new Game();

	}

}
