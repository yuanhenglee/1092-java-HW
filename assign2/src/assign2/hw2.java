package assign2;

// import java.util.Random;
import java.util.*;
import java.lang.Math;

/**
 * 
 * The hw2 class is used to do the second homework assignment. This class will
 * contain another class called Rect and a main function that is used to test
 * the Rect class by generating a list of rectangles in a region, computing
 * their union, the intersection of every two rectangles. The union and the list
 * of sorted intersections are printed to the standard output. The command line
 * arguments include the size of bounding region for the random rectangle to be
 * generated, the number of rectangles, and a seed for random number generator.
 * 
 * @author li
 *
 */
public class hw2 {

	public static void main(String[] args) {

		if (args.length != 3) {
			System.out.println("Usage: hw2 size num seed");
			return;
		}
		int size = Integer.valueOf(args[0]);
		int n = Integer.valueOf(args[1]);
		long seed = Integer.valueOf(args[2]);

		// create instance of Random class
		Random rand = new Random(seed);

		// TODO: Fill in the testing code here.
		// You are asked to create a list of random rectangles
		System.out.println("List of " + n + " randomly generated rectangles:");
		Rect[] generatedList = new Rect[n];
		for (int i = 0; i < n; i++) {
			generatedList[i] = Rect.getRandomRect(rand, size);
		}
		for (int i = 0; i < n; i++) {
			System.out.println(generatedList[i]);
		}
		// within the square of [0,0,size,size].
		// Then you are asked to compute their union and the area of this union.
		Rect union = Rect.union(generatedList);
		System.out.println("Union of these rectangles: " + union);

		System.out.println("The area of this union is " + union.getArea() + ".");

		// Finally, you are asked to computer the intersections of every two rectangles
		// and sort them according to their coordinates.
		List<Rect> intersectionsList = new ArrayList<Rect>();
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				Rect intersection = generatedList[i].intersection(generatedList[j]);
				if( intersection != null ){
					intersectionsList.add( intersection );
				}
			}
		}
		Rect[] intersectionsArray = intersectionsList.toArray(new Rect[intersectionsList.size()]);
		Rect.sortRect(intersectionsArray);
		System.out.println("Intersection of every two rectangles (sorted):" );
		for (Rect rect : intersectionsArray) {
			System.out.println(rect);
		}
		// Please refer to the sample output for the required output format

		System.out.println("ID: 107703004");
		return;
	}

	/**
	 * This is a class used to represent a rectangle parallel to the X and Y axes in
	 * the plane. In this class a rectangle is represented by four integer number
	 * (x1,y1, x2, y2) where (x1,y1) is the smallest x and y coordinates and (x2,y2)
	 * the largest x and y coordinates of all points (x,y) in the rectangle.
	 * 
	 * @author li
	 *
	 */

	public static class Rect {

		public int x1, y1; // smallest x and y coordinates
		public int x2, y2; // largest x and y coordinates

		/**
		 * Constructor of the Rect class
		 * 
		 * @param mx1 minimal x
		 * @param my1 minimal y
		 * @param mx2 maximal x
		 * @param my2 maximal y
		 */
		public Rect(int mx1, int my1, int mx2, int my2) {
			// TODO: put your code here.
			x1 = mx1;
			x2 = mx2;
			y1 = my1;
			y2 = my2;
		}

		/**
		 * Constructor of the Rect class
		 * 
		 * @param p with the coordinates of two points (minmal and maximal) in an array
		 */
		public Rect(int[] p) {
			// TODO: put your code here.
			if (p.length == 4) {
				x1 = p[0];
				x2 = p[2];
				y1 = p[1];
				y2 = p[3];
			}
		}

		/**
		 * Find the width of the rectangle.
		 * 
		 * @return an integer representing the width of the rectangle
		 */
		public int getWidth() {
			// TODO: put your code here.
			return Math.abs(x1 - x2);
		}

		/**
		 * Find the height of the rectangle.
		 * 
		 * @return an integer representing the height of the rectangle
		 */
		public int getHeight() {
			// TODO: put your code here.
			return Math.abs(y1 - y2);
		}

		/**
		 * Find the area of this rectangle.
		 * 
		 * @return an integer representing the area of the rectangle
		 */
		public int getArea() {
			// TODO: put your code here.
			return getWidth() * getHeight();
		}

		/**
		 * Get a random legal rectangle within the rectangle bounded by [0,0] and
		 * [size,size]
		 *
		 * @param rand a random number generator
		 * @param size size of the bounded region where the generated rectangles will
		 *             reside
		 * @return a legal rectangle (a rectangle is legal only if its minimal x and y
		 *         is greater than its maximal x and y, respectively. )
		 */
		public static Rect getRandomRect(Random rand, int size) {

			int[] p = new int[4];

			do {
				for (int i = 0; i < 4; i++) {
					p[i] = rand.nextInt(size);

				}
				if (p[0] > p[2]) {
					int temp = p[0];
					p[0] = p[2];
					p[2] = temp;
				}
				if (p[1] > p[3]) {
					int temp = p[1];
					p[1] = p[3];
					p[3] = temp;
				}
			} while ((p[0] == p[2]) || (p[1] == p[3]));

			return new Rect(p);

		}

		/**
		 * Return the rectangle which is the intersection of itself and another
		 * rectangle.
		 * 
		 * @param r2 another Rect instance
		 * @return the intersection rectangle of this rectangle and r2 if there exists a
		 *         legal one Otherwise, return null.
		 */
		public Rect intersection(Rect r2) {

			// TODO: put your code here.
			int interX1, interX2, interY1, interY2;
			if (x1 <= r2.x1 && x2 <= r2.x2 && x2 >= r2.x1) {
				interX1 = r2.x1;
				interX2 = x2;
			} else if (x1 >= r2.x1 && x2 >= r2.x2 && x1 <= r2.x2) {
				interX1 = x1;
				interX2 = r2.x2;
			} else if (x1 <= r2.x1 && x2 >= r2.x2) {
				interX1 = r2.x1;
				interX2 = r2.x2;
			} else if (x1 >= r2.x1 && x2 <= r2.x2) {
				interX1 = x1;
				interX2 = x2;
			} else
				return null;
			if (y1 <= r2.y1 && y2 <= r2.y2 && y2 >= r2.y1) {
				interY1 = r2.y1;
				interY2 = y2;
			} else if (y1 >= r2.y1 && y2 >= r2.y2 && y1 <= r2.y2) {
				interY1 = y1;
				interY2 = r2.y2;
			} else if (y1 <= r2.y1 && y2 >= r2.y2) {
				interY1 = r2.y1;
				interY2 = r2.y2;
			} else if (y1 >= r2.y1 && y2 <= r2.y2) {
				interY1 = y1;
				interY2 = y2;
			}else
				return null;

			if( interX1 == interX2 || interY1 == interY2 ) 
				return null;

			return new Rect(interX1, interY1, interX2, interY2);
		}

		/**
		 * Return the smallest rectangle which includes all input rectangles.
		 * 
		 * @param rs a non-empty array of rectangles.
		 * @return the smallest rectangle including all input rectangles .
		 */
		public static Rect union(Rect[] rs) {

			// TODO: put your code here
			int minX = Integer.MAX_VALUE;
			int maxX = 0;
			int minY = Integer.MAX_VALUE;
			int maxY = 0;
			for (Rect rect : rs) {
				minX = minX < rect.x1 ? minX : rect.x1;
				maxX = maxX > rect.x2 ? maxX : rect.x2;
				minY = minY < rect.y1 ? minY : rect.y1;
				maxY = maxY > rect.y2 ? maxY : rect.y2;
			}

			return new Rect(minX, minY, maxX, maxY);
		}

		/**
		 * Swap two rectangles
		 * 
		 * @param r1 first rectangle
		 * @param r2 second rectangle
		 */
		private void swap(Rect r1, Rect r2) {
			Rect temp = r1;
			r1 = r2;
			r2 = temp;
		}

		/**
		 * compare itself with another rectangle. A rectangle is greater than another
		 * one if and only if the four numbers describing the rectangle (x1, y1, x2, y2)
		 * are greater than the other one lexicographically. Compare x1. If equals,
		 * compare y1. If equals, compare x2, and then compare y2.
		 * 
		 * @param r2 another rectangle
		 * @return boolean showing if itself is greater than r2.
		 */
		private boolean greater(Rect r2) {

			// TODO: put your code here
			if (x1 > r2.x1)
				return true;
			if (x1 < r2.x1)
				return false;

			if (y1 > r2.y1)
				return true;
			if (y1 < r2.y1)
				return false;

			if (x2 > r2.x2)
				return true;
			if (x2 < r2.x2)
				return false;

			if (y2 > r2.y2)
				return true;

			return false;

		}

		/**
		 * sort a list of rectangles lexicographically according to their coordinates.
		 * 
		 * @param rs a list of rectangles
		 */
		public static void sortRect(Rect[] rs) {

			// TODO: sort the list of rectangle.
			int n = rs.length;

			for (int i = 0; i < n; i++) {
				for (int j = 1; j < (n - i); j++) {
					if ( rs[j-1].greater(rs[j])) {
						Rect tmp = rs[j-1];
						rs[j-1] = rs[j];
						rs[j] = tmp;
					}

				}
			}
		}

		/**
		 * print the lower left and higher right corner of a rectangle.
		 */
		public String toString() {
			return String.format("(%d,%d,%d,%d)", x1, y1, x2, y2);
		}

	}
}
