// ********************************************************
// Class: CS225
// Name: Lucien Hammond
// Date: 12/2/22
//
// Purpose: To have all overloaded methods of the steady state
//          genetic algorithm which are used to control the evolutionary 
//          process of this genetic algorithm type
//           
//
// Attributes: none
//
// Methods: +selectParents(): Chromosome[]
//          +insertOffspring(Chromosome): void
//          +resetPopulation(): void
//          +printSolution(): void
//
// ********************************************************
public class SteadyGA extends GenericGA {

    public Chromosome[] selectParents() {
        Chromosome[] parents = new Chromosome[2];
        
        parents[0] = population[(int) Math.floor(Math.random() * (population.length))];
        parents[1] = population[(int) Math.floor(Math.random() * (population.length))];

        return parents;
    }


    public void insertOffspring(Chromosome offspring) {
        population[population.length - 1] = offspring;
    }

    public void resetPopulation() {
        population = calcPopulationFitness(population);
        population = orderPopulation(population);
        cycles++;
    }

    public void printSolution() {
        System.out.println("");
        System.out.println("Steady-State GA:");
        System.out.print("The solution is: ");
        System.out.print(Math.round(population[0].getValue(0) * 1000.0) / 1000.0);
        for(int i = 1; i < polynomialSize; i++) {
            System.out.print(" + " + Math.round(population[0].getValue(i) * 1000.0) / 1000.0 + "x^" + i);
        }
        System.out.println("");
        System.out.println("R^2 Value: " + population[0].getFitness());
        System.out.println("Steady-State GA reached a solution in: " + cycles + " cycles.");
        
    }

}
