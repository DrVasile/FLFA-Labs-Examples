import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import grammar.Grammar;
import utils.GrammarReader;

public class Main
{
    public static void main(String[] args)
    {
        final File grammarInputFile = new File("resources/grammar_variants.txt");

        try (final Scanner scanner = new Scanner(grammarInputFile))
        {
            final Grammar grammar = GrammarReader.readNextGrammar(scanner);

            for (int i = 0; i < 15; i++)
                System.out.println(grammar.generateWord());

            System.out.println(grammar);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
