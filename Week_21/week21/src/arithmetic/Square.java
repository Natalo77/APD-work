package arithmetic;

import javax.swing.JOptionPane;

public class Square {
	public static int square(int n) {
		  int i = 0, square = 0, twoN = 0;
	      assert square == i*i : square;
	      assert twoN == 2*i : twoN;
		  while (i < n) {
		      assert square == i*i : square;
		      assert twoN == 2*i : twoN;
		      square = square + twoN + 1;
		      assert square == (i+1)*(i+1);
		      assert twoN == 2*i;
		      twoN = twoN + 2;
		      assert(square == (i+1)*(i+1));
		      assert(twoN == 2*(i + 1));
		      i++;
		      assert(square == i*i);
		      assert(twoN == 2*i);
		  }
		   assert square == n*n : square;
		   assert twoN == 2*n;
		   return square;
	}
	
	public static void main(String[] args) {
		int x = ReadInt.read("Please input a number to be squared");
		int result = square(x);
		JOptionPane.showMessageDialog(null,x + "^2=" + result,"Function result",JOptionPane.INFORMATION_MESSAGE);
	}
}
