import java.util.Scanner;
import java.io.File;

public class Driver {
    public static void main(String [] args) throws Exception{
		double [] a1 = {3.0, 4.0, 5.0};
		int [] a2 = {0, 1, 2};
        Polynomial p = new Polynomial(a1, a2);
        System.out.println(p.evaluate(3.0));
        System.out.println("----------------------------------------------------");
		
		double [] c1 = {6.0, 3.0, 5.0};
		int [] c2 = {0, 1, 2};
        Polynomial p1 = new Polynomial(c1, c2);
        double [] c3 = {1.0, 2.0, 3.0, 4.0};
		int [] c4 = {2, 4, 5, 6};
        Polynomial p2 = new Polynomial(c3, c4);
        Polynomial s = p1.add(p2);
		for (int i = 0; i < s.exponents.length; i ++) {
		    System.out.println(s.coefficients[i]);
            System.out.println(s.exponents[i]);
			System.out.println();
		}
		System.out.println("----------------------------------------------------");
		
		double [] b1 = {1.0, -2.0, 1.0};
		int [] b2 = {0, 1, 2};
		Polynomial d = new Polynomial(b1, b2);
		if(d.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
		System.out.println("----------------------------------------------------");

        Polynomial result = new Polynomial();
		result = p1.multiply(p2);
		for (int i = 0; i < result.exponents.length; i ++) {
			System.out.println(result.coefficients[i]);
            System.out.println(result.exponents[i]);
			System.out.println();
		}
		System.out.println("----------------------------------------------------");
		
		Scanner scanner1 = new Scanner(System.in);
		System.out.println("Enter the Filename: ");
		String message1 = scanner1.nextLine();

		Polynomial s1 = new Polynomial(new File(message1));
		for (int i = 0; i < s1.exponents.length; i ++) {
		System.out.println(s1.coefficients[i]);
        System.out.println(s1.exponents[i]);
		System.out.println();
		}
		System.out.println("----------------------------------------------------");
		
		Scanner scanner2 = new Scanner(System.in);
		System.out.println("Enter the Filename: ");
		String message2 = scanner2.nextLine();
		double [] e1 = {3.0, 4.0, 5.0};
		int [] e2 = {3, 2, 1};
		Polynomial s2 = new Polynomial(e1, e2);
		s2.saveToFile(message2);
		

    }
}