package pl.edu.agh.miss.main;

import pl.edu.agh.miss.model.automaton.Automaton;
import pl.edu.agh.miss.model.automaton.Compaction;
import pl.edu.agh.miss.model.automaton.factory.CellsFactory;
import pl.edu.agh.miss.model.automaton.factory.GeneralStateFactory;
import pl.edu.agh.miss.model.automaton.factory.SimpleCellsFactory;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class App2 {
    private static Automaton automaton;

    public static void main(String[] args) throws FileNotFoundException {

        for(int j=0 ; j<10;j++) {
            PrintWriter writter = new PrintWriter("data/stare"+j);
            writter.println("prey;predator;iteration");


            CellsFactory cellsFactory = new SimpleCellsFactory(new Dimension(Automaton.getSize(), Automaton.getSize()), new GeneralStateFactory(new Compaction(4)));
            automaton = new Automaton(cellsFactory);
            Integer i = 0;
            while (i < 2000) {
                automaton = automaton.nextState();
                writter.println(automaton.getPreyNumber() + ";" + automaton.getPredatorNumber() + ";"  + i);

                i++;
            }
            System.out.println("dare"+ j);
            writter.close();
        }
    }
}
