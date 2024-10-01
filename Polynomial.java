import java.lang.Math;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Polynomial {
    double[] coefficients;
	int[] exponents;

    public Polynomial() {
        this.coefficients = new double[]{0};
		this.exponents = new int[]{0};
    }

    public Polynomial(double [] coefficients, int [] exponents) {
        this.coefficients = new double[coefficients.length];
		this.exponents = new int[exponents.length];
		for (int i = 0; i < coefficients.length; i++) {
			this.coefficients[i] = coefficients[i];
			this.exponents[i] = exponents[i];
		}
    }
	
	public Polynomial(File file) throws Exception{
		BufferedReader b = new BufferedReader(new FileReader(file));
		String line = b.readLine();
		b.close();
		
		String[] tem = line.split("(?=[-+])", -1);

		double[] result1 = new double[tem.length];
		int[] result2 = new int[tem.length];

		for (int i = 0; i < tem.length; i ++) {
			String temp = tem[i];
			if (temp.contains("x") == false) {
				result1[i] = Double.parseDouble(temp);
				result2[i] = 0;
			}
			else {
				String[] temp1 = temp.split("x");
				if (temp1.length > 1 && temp1[1].equals("") == false) {
					result2[i] = Integer.parseInt(temp1[1]);
				}
				else {
					result2[i] = 1;
				}
				if (temp1[0].equals("") || temp1[0].equals("+")) {
					result1[i] = 1;
				}
				else if (temp1[0].equals("-")) {
					result1[i] = -1;
				}
				else {
					result1[i] = Double.parseDouble(temp1[0]);
				}
			}
		}
		
		for (int i = 0; i < result1.length - 1; i ++) {
			for (int j = 0; j < result1.length - i - 1; j ++) {
				if (result2[j] > result2[j + 1]) {
					int newex = result2[j];
					result2[j] = result2[j + 1];
					result2[j + 1] = newex;
					double newc = result1[j];
					result1[j] = result1[j + 1];
					result1[j + 1] = newc;
				}
			}
		}
		
		this.coefficients = result1;
		this.exponents = result2;
	}
    
    public Polynomial add(Polynomial that) {
		if (that.coefficients == null) {
			return this;
		}

        int[] tem = new int[this.coefficients.length + that.coefficients.length];
	    for (int i = 0; i < this.exponents.length; i ++) {
			tem[i] = this.exponents[i];
	    }
		int num = 0;
		for (int j = 0; j < that.exponents.length; j ++) {
			int justify = 0;
			for (int k = 0; k < this.exponents.length; k ++) {
				if (that.exponents[j] == tem[k]) {
					justify = justify + 1;
				}
			}
			if (justify == 0) {
				tem[this.exponents.length + num] = tem[this.exponents.length + num] + that.exponents[j];
				num = num + 1;
			}
		}

		int[] result1 = new int[this.exponents.length + num];
		for (int g = 0; g < this.exponents.length + num; g ++) {
			result1[g] = tem[g];
		}
		Arrays.sort(result1);
		double[] result2 = new double[this.exponents.length + num];
		for (int i = 0; i < this.exponents.length; i ++) {
			for (int j = 0; j < this.exponents.length + num; j ++) {
				if (result1[j] == this.exponents[i]) {
					result2[j] = result2[j] + this.coefficients[i];
				}
			}
		}
		
		for (int i = 0; i < that.exponents.length; i ++) {
			for (int j = 0; j < this.exponents.length + num; j ++) {
				if (result1[j] == that.exponents[i]) {
					result2[j] = result2[j] + that.coefficients[i];
				}
			}
		}
	    return new Polynomial(result2, result1);
	}

	public Polynomial multiply(Polynomial s) {
		if (s.coefficients == null) {
			return s;
		}

		int Maxlen = this.exponents[this.exponents.length - 1] + s.exponents[s.exponents.length - 1];

		double[] tem = new double[Maxlen + 1];
		for (int i = 0; i < this.exponents.length; i ++) {
			for (int j = 0; j < s.exponents.length; j ++) {
				int rx = this.exponents[i] + s.exponents[j];
				tem[rx] = tem[rx] + this.coefficients[i] * s.coefficients[j];
			}
		}

		int count = 0;
		for (int k = 0; k < tem.length; k ++) {
			if (tem[k] != 0) {
				count = count + 1;
			}
		}
		
		double[] result1 = new double[count];
		int[] result2 = new int[count];
		for (int g = 0; g < count; g ++) {
			for (int h = 0; h < tem.length; h ++) {
				if (tem[h] != 0) {
					result2[g] = h;
					result1[g] = tem[h];
					tem[h] = 0.0;
					break;
				}
			}
		}
	    return new Polynomial(result1, result2);
	}

    public double evaluate(double x) {
		double result = 0;
		for (int i = 0; i < this.coefficients.length; i ++) {
			result = result + this.coefficients[i] * Math.pow(x, this.exponents[i]);
		}
		return result;
	}

    public boolean hasRoot(double y) {
		double answer = 0;
        for (int i = 0; i < this.coefficients.length; i ++) {
            answer = answer + this.coefficients[i] * Math.pow(y, this.exponents[i]);
        }
        if (answer == 0) {
            return true;
        } else {
            return false;
        }
	}
	
	public void saveToFile(String name) throws Exception{
		for (int i = 0; i < this.coefficients.length - 1; i ++) {
			for (int j = 0; j < this.coefficients.length - i - 1; j ++) {
				if (this.exponents[j] > this.exponents[j + 1]) {
					int newex = this.exponents[j];
					this.exponents[j] = this.exponents[j + 1];
					this.exponents[j + 1] = newex;
					double newc = this.coefficients[j];
					this.coefficients[j] = this.coefficients[j + 1];
					this.coefficients[j + 1] = newc;
				}
			}
		}
		
		FileWriter a = new FileWriter(name);

		for (int k = 0; k < this.coefficients.length; k ++) {
			if (k > 0 && this.coefficients[k] > 0) {
				a.write("+");
			}

			if (this.exponents[k] == 0 || Math.abs(this.coefficients[k]) != 1) {
				a.write(String.valueOf(this.coefficients[k]));
			}
			else if (this.coefficients[k] == -1){
				a.write("-");
			}
			
			if (this.exponents[k] != 0) {
				a.write("x");
			}
			
			if (this.exponents[k] > 0) {
				a.write(String.valueOf(this.exponents[k]));
			}
		}
		a.close();
	}

}