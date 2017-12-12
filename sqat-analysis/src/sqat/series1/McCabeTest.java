package sqat.series1;

public class McCabeTest {
	public int test1() {
		for (int x = 0; x < width; x++) {
		}
		for (int y = 0; y < height; y++) {
			char c = map[x][y];
			addSquare(grid, ghosts, startPositions, x, y, c);
		}

	}
}
