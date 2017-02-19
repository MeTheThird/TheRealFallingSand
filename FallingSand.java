package fallingSand;

import apcs.*;

public class FallingSand {

	static int[][] grid = new int[500][500];
	
	public static void main(String[] args) {
		Window.size(500,500);
		Window.setFrameRate(Integer.MIN_VALUE);
		while (true)
		{
			Window.frame();
			draw(grid);
			
			for(int i = 0; i < 10; i++)
			{
				if (Window.key.pressed("s"))
				{
					int x = Window.mouse.getX();
					x += Window.random(-10, 10);
					int y = Window.mouse.getY();
					y += Window.random(-10, 10);
					if (x < 500 && x >= 0 && y < 500 && y >= 0)
						grid[x][y] = 1;
				}
			}
			
			if (Window.key.pressed("c"))
			{
				for (int x = 0; x < 500; x++)
				{
					for (int y = 0; y < 500; y++)
					{
						grid[x][y] = 0;
					}
				}
			}
			
			move(grid);
		}
		
		
	}
	
	public static void draw(int[][] grid)
	{
		for (int x = 0; x < 500; x++)
		{
			for (int y = 0; y < 500; y++)
			{	
				if (grid[x][y] == 1)
				{
					Window.out.color("tan");
					Window.out.square(x, y, 1);
				}
			}
		}
	}
	
	public static void move(int[][] grid)
	{
		for (int x = 0; x < 500; x++)
		{
			for (int y = 499; y >= 0; y--)
			{
				if (grid[x][y] == 1 && y < 499 && grid[x][y + 1] == 0)
				{
					grid[x][y] = 0;
					grid[x][y + 1] = 1;
				}
				else if(y > 1 && grid[x][y] == 1 && grid[x][y - 1] == 1 && grid[x][y - 2] == 1)
				{
					if (x > 0 && x < 499 && grid[x - 1][y] == 0 && grid[x + 1][y] == 0)
					{
						if(Window.rollDice(2) == 1)
						{
							grid[x][y] = 0;
							grid[x + 1][y] = 1;
						}
						else
						{
							grid[x][y] = 0;
							grid[x - 1][y] = 1;
						}
					}
					else if (x > 0 && grid[x - 1][y] == 0)
					{
						grid[x][y] = 0;
						grid[x - 1][y] = 1;
					}
					else if(x < 499 && grid[x + 1][y] == 0)
					{
						grid[x][y] = 0;
						grid[x + 1][y] = 1;
					}
				}
			}
		}
	}

}
