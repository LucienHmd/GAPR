// ********************************************************
// Class: CS225
// Name: Lucien Hammond
// Date: 12/2/22
//
// Purpose: Is in charge of loading solution points from a 
//          file, user Input, and containing the main method
//           
//
// Attributes: -points: double[][]
//             -GAs: GenericGA[]
//             -polynomialSize: int
//
// Methods: +loadGAs(): void
//          +loadPoints(): void 
//          +userPolySize(): void
//          +run(int, double[][]): void
//          +main(): void
//
// ********************************************************

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


public class Controller {
    private double[][] points;
    private GenericGA[] GAs = new GenericGA[4];
    int polynomialSize;

    public void loadGAs() {
        GAs[0] = new GenerationalGA();
        GAs[1] = new SteadyGA();
        GAs[2] = new SteadyGenGA();
        GAs[3] = new MuPlusMuGA();
    }

    public void loadPoints() { 
        File file = new File("points.csv");

        try {
            FileReader fr = new FileReader(file);
            BufferedReader counter = new BufferedReader(fr);
            

            String line;
            int lineNumber = 0;
            int count = 0;

            while ((line = counter.readLine()) != null) {
                count++;
            }

            points = new double[count][2];

            counter.close();

            FileReader fileRead = new FileReader(file);
            BufferedReader br = new BufferedReader(fileRead);

            while ((line = br.readLine()) != null) {
                
                String[] row;
                row = line.split(",");
                
                points[lineNumber][0] = Double.parseDouble(row[0]);
                points[lineNumber][1] = Double.parseDouble(row[1]);

                lineNumber = lineNumber + 1;
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void userPolySize() {  // 
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Highest Polynomial Power: ");

        try {
            polynomialSize = Integer.parseInt(input.nextLine());
        } catch(Exception e) {
            System.out.println("INVALID INPUT");
            polynomialSize = 2;
        }
        System.out.println("");
        input.close();
        
    }

    public void run(int GA_ID, double points[][]) {
        GAs[GA_ID].setPolySize(polynomialSize);
        GAs[GA_ID].setPoints(points);
        GAs[GA_ID].initPopulation(10);
        
        while(GAs[GA_ID].checkForSolution() == false) {

            for(int i = 0; i < GAs[GA_ID].getPopulationSize(); i++) {
                GAs[GA_ID].insertOffspring(GAs[GA_ID].generateOffspring(GAs[GA_ID].selectParents()));
            }

            GAs[GA_ID].resetPopulation();
            
        }

        GAs[GA_ID].printSolution();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();

        controller.userPolySize();
        controller.loadGAs();
        controller.loadPoints();




        controller.run(0,controller.points);
        controller.run(1,controller.points);
        controller.run(2, controller.points);
        controller.run(3, controller.points);
        

    }


}