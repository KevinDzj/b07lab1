import java.lang.Math;

public class Polynomial {
    double [] coefficients;

    public Polynomial() {
        coefficients = new double[]{0};
    }

    public Polynomial(double [] arr) {
        coefficients = arr;
    }
    
    public Polynomial add(Polynomial p) {
        int Maxlen = 0;
        if (coefficients.length >= p.coefficients.length) {
            Maxlen = coefficients.length;
        } else {
            Maxlen = p.coefficients.length;
        }
        double [] result = new double[Maxlen]; 
        for (int i = 0; i < coefficients.length; i ++) {
            result[i] = result[i] + coefficients[i];
        }
        for (int i = 0; i < p.coefficients.length; i ++) {
            result[i] = result[i] + p.coefficients[i];
        }

        return new Polynomial(result);
    }

    public double evaluate(double x) {
        double presult = 0;
        for (int i = 0; i < coefficients.length; i ++) {
            presult = presult + coefficients[i] * Math.pow(x, i);
        }

        return presult;
    }

    public boolean hasRoot(double y) {
        double answer = 0;
        for (int i = 0; i < coefficients.length; i ++) {
            answer = answer + coefficients[i] * Math.pow(y, i);
        }
        if (answer == 0) {
            return true;
        } else {
            return false;
        }
    }
	
}