package arithmetic;

import javax.swing.JOptionPane;

public class Cube {
	public static int cube(int n) {
		  int cube = 0, threeNsquared = 0, threeN = 0;

		  int i = 0;

          assert cube == i*i*i : cube;
          assert threeNsquared == 3*i*i : threeNsquared;
          assert threeN == 3*i : threeN;

		  while (i < n) {
		      assert cube == i*i*i : cube;
		      assert threeN == 3*i : threeN;
		      assert threeNsquared == 3*i*i : threeNsquared;

			  cube = cube + threeNsquared + threeN + 1;
              assert cube == (i+1)*(i+1)*(i+1) : cube;
              assert threeN == 3*i : threeN;
              assert threeNsquared == 3*i*i : threeNsquared;

			  threeNsquared = threeNsquared + 2*threeN + 3;
              assert cube == (i+1)*(i+1)*(i+1) : cube;
              assert threeN == 3*i : threeN;
              assert threeNsquared == 3*(i+1)*(i+1) : threeNsquared;

			  threeN = threeN + 3;
			  assert cube == (i+1)*(i+1)*(i+1) : cube;
			  assert threeN == 3*(i+1) : threeN;
			  assert threeNsquared == 3*(i+1)*(i+1) : threeNsquared;

			  i++;
			  assert cube == i*i*i : cube;
              assert threeN == 3*(i) : threeN;
              assert threeNsquared == 3*(i)*(i) : threeNsquared;
		  }
		  assert i == n : i;
		  assert cube == i*i*i : cube;
		  assert cube == n*n*n : cube;
		  return cube;
	}
	
	public static void main(String[] args) {
		int x = ReadInt.read("Please input a number to be cubed");
		int result = cube(x);
		JOptionPane.showMessageDialog(null,x + "^3=" + result,"Function result",JOptionPane.INFORMATION_MESSAGE);
	}

}
