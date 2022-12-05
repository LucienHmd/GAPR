// ********************************************************
// Class: CS225
// Name: Lucien Hammond
// Date: 12/2/22
//
// Purpose: Is an individual with a given number of values that
//          can change its values and calculate its fitness given 
//          an array of points
//           
//
// Attributes: -values: double[]
//             -fitness: double
//
// Methods: +Chromosome(int)
//          +calcFitness(double[][]): void
//          +getFitness(): double
//          +setRandomValue(int): void
//          +setValue(int): void
//          +getValue(int): double
//
// ********************************************************
public class Chromosome {
    private double[] values;
    private double fitness = 0;

    Chromosome(int polynomialSize) {
        values = new double[polynomialSize + 1];
    }

    public void calcFitness(double[][] points) {
        double num = 0;
        double yPrediction = 0;
        double yMean = 0;
        double dem = 0;
        
        for(int i = 0; i < points.length; i++) {
            yMean = yMean + points[i][1];
        }
        yMean = yMean / points.length;

        for(int i = 0; i < points.length; i++) {
            yPrediction = 0;
            for(int j = 0; j < values.length; j++) {
                yPrediction = yPrediction + values[j] * Math.pow(points[i][0], j);
            }
            
            num = num + Math.pow(points[i][1] - yPrediction, 2);
            dem = dem + Math.pow(points[i][1] - yMean, 2);
        }

        fitness = 1 - (num/dem);

    
    }

    public double getFitness() { 
        return fitness;
    }

    public void setRandomValue(int position) {
        values[position] = Math.floor((Math.random() * 41) - 20);
    }

    public void setValue(int position, double value) {
        values[position] = value;
    }

    public double getValue(int position) { 
        return values[position];
    }



}
