import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
	
	private float _acc = 1f;
	private float _dcc = 0.5f;
	private KeyInput input;

	public Player(float x, float y, ID id, KeyInput input) {
		super(x, y, id);
		this.input = input;
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(input.keys[0]) velX += _acc;
		else if(input.keys[1]) velX -= _acc;
		else if(!input.keys[0] && !input.keys[1]) {
			if(velX > 0) velX -= _dcc;
			else if(velX < 0) velX += _dcc;
		}
		
		if(input.keys[2]) velY += _acc;
		else if(input.keys[3]) velY -= _acc;
		else if(!input.keys[2] && !input.keys[3]) {
			if(velY > 0) velY -= _dcc;
			else if(velY < 0) velY += _dcc;
		}
		velX = clamp(velX, 5, -5);
		
	}
	
	private float clamp(float value, float max, float min) {
		if(value > max) value = max;
		else if(value <= min) value = min;
		return value;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 32, 32);
		
	}

}
