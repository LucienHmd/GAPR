// ********************************************************
// Class: CS225
// Name: Lucien Hammond
// Date: 12/2/22
//
// Purpose: To have all generic methods of each genetic algorithm
//          which are used to control the evolutionary process
//           
//
// Attributes: #population: Chromosome[] 
//             #bufferPopulation: Chromosome[]
//             #bufferPointer: int
//             #cycles: int
//             #polynomialSize: int
//             #points: double[][]
//
// Methods: +setPoints(double[][]): void
//          +setPolySize(int): void
//          +initPopulation(int): void
//          +calcPopulationFitness(Chromosome[]): Chromosome[]
//          +orderPopulation(Chromosome[]): Chromosome[]
//          +selectParents(): Chromosome[]
//          +generateOffspring(Chromosome[]): Chromosome
//          +insertOffspring(Chromosome): void
//          +checkForSolution(): boolean
//          +printSolution(): void
//          +getPopulationSize(): int
//          +resetPopulation(): void
//
// ********************************************************
public class GenericGA {
    protected Chromosome[] population;
    protected Chromosome[] bufferPopulation;

    protected int bufferPointer = 0;
    protected int cycles = 0;
    protected int polynomialSize;
    protected double points[][];
    
    public void setPoints(double points[][]) {
        this.points = points;
    }

    public void setPolySize(int polynomialSize) {
        this.polynomialSize = polynomialSize + 1;
    }

    public void initPopulation(int size) { 
        population = new Chromosome[size];
        bufferPopulation = new Chromosome[size];

        for(int i = 0; i < population.length; i++) {
            population[i] = new Chromosome(polynomialSize);
            for(int j = 0; j < polynomialSize; j++) {
                population[i].setRandomValue(j);
            }
            
        }
        population = calcPopulationFitness(population);
        population = orderPopulation(population);


    }

    public Chromosome[] calcPopulationFitness(Chromosome[] population) {
        for(int i = 0; i < population.length; i++) {
            population[i].calcFitness(points);
        }
        return population;
    }

    public Chromosome[] orderPopulation(Chromosome[] population) { // Uses bubble sorting
        Chromosome bufferChromosome;
        int numMoves = 1;

        while(numMoves != 0) {
            numMoves = 0;
            for(int i = 0; i < population.length - 1; i++) {
                if(population[i].getFitness() < population[i+1].getFitness()) {
                    bufferChromosome = population[i+1];
                    population[i+1] = population[i];
                    population[i] = bufferChromosome;
                    numMoves = numMoves + 1;
                }
            }
        }


        return population;

    }

    public Chromosome[] selectParents() {
        Chromosome[] parents = new Chromosome[2];
        parents[0] = population[0];
        parents[1] = population[1];

        return parents;
    }

    public Chromosome generateOffspring(Chromosome[] parents) {
        Chromosome offspring = new Chromosome(polynomialSize);
        for(int i = 0; i < polynomialSize; i++) {
            if((Math.floor(Math.random() * 10) + 1) == 1) {
                offspring.setRandomValue(i);
            } else {
                offspring.setValue(i, (parents[0].getValue(i) + parents[1].getValue(i))/2);
            }
            
        }

        return offspring;

    }

    public void insertOffspring(Chromosome offspring) {

    }

    public boolean checkForSolution() {
        boolean solutionMet = false;
        if(population[0].getFitness() >= 1) {
            solutionMet = true;
        } else if(cycles > 500000) {
            solutionMet = true;
        }

        return solutionMet;
    }

    public boolean checkForSolutionTEST(double r, int cycles) { // WILL BE REMOVED FOR FINAL PRODUCT
        boolean solutionMet = false;
        if(r >= 1) {
            solutionMet = true;
        } else if(cycles > 500000) {
            solutionMet = true;
        }

        return solutionMet;
    }

    public void printSolution() {
        System.out.println("");
        System.out.print("The solution is: ");
        System.out.print(Math.round(population[0].getValue(0) * 1000.0) / 1000.0);
        for(int i = 1; i < polynomialSize; i++) {
            System.out.print(" + " + Math.round(population[0].getValue(i) * 1000.0) / 1000.0 + "x^" + i);
        }
        System.out.println("");
        System.out.println("R^2 Value: " + population[0].getFitness());
        System.out.println("GA reached a solution in: " + cycles + " cycles.");
        
    }

    public int getPopulationSize() {
        return population.length;
    }

    public void resetPopulation() {
        for(int i = 0; i < population.length; i++) {
            population[i] = bufferPopulation[i];
        }
        population = calcPopulationFitness(population);
        population = orderPopulation(population);
        cycles++;
    }
}
