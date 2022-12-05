// ********************************************************
// Class: CS225
// Name: Lucien Hammond
// Date: 12/2/22
//
// Purpose: To have all overloaded methods of the generational
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
import java.util.ArrayList;

public class GenerationalGA extends GenericGA {

    public Chromosome[] selectParents() {
        Chromosome[] parents = new Chromosome[2];
        ArrayList<Chromosome> tournament = new ArrayList<Chromosome>();
        for(int i = 0; i < population.length; i++) {
            tournament.add(population[i]);
        }

        while(tournament.size() > 5) {
            tournament.remove((int) Math.floor(Math.random() * tournament.size()));
        }

        parents[0] = tournament.get(0);
        parents[1] = tournament.get(1);

        return parents;
    }

    public void insertOffspring(Chromosome offspring) {
        bufferPopulation[bufferPointer] = offspring;
        bufferPointer++;
        if(bufferPointer == population.length) {
            bufferPointer = 0;
        }
    }


    public void resetPopulation() {
        for(int i = 0; i < population.length; i++) {
            population[i] = bufferPopulation[i];
            bufferPopulation[i] = null;
        }
        population = calcPopulationFitness(population);
        population = orderPopulation(population);
        cycles++;

    }

    public void printSolution() {
        System.out.println("");
        System.out.println("Generational GA:");
        System.out.print("The solution is: ");
        System.out.print(Math.round(population[0].getValue(0) * 1000.0) / 1000.0);
        for(int i = 1; i < polynomialSize; i++) {
            System.out.print(" + " + Math.round(population[0].getValue(i) * 1000.0) / 1000.0 + "x^" + i);
        }
        System.out.println("");
        System.out.println("R^2 Value: " + population[0].getFitness());
        System.out.println("Generational GA reached a solution in: " + cycles + " cycles.");
        
    }
}
