package sqat.series1;

public class McCabeTest {
	public int test1(int width, int height) {
		int a = 0;
		for (int x = 0; x < width || x < height; x++) {
			a++;
		}
		return a;
	}
}
