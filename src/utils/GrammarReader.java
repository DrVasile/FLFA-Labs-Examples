package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import grammar.Grammar;
import grammar.Production;

public class GrammarReader
{
    public static final String ARROW = "→";

    public static Grammar readNextGrammar(final Scanner scanner)
    {
        System.out.println("This is the grammar for " + scanner.nextLine().replace(':', '.'));
        String currentLine = "";
        do
        {
            currentLine = removeSpaces(scanner.nextLine());
        } while (currentLine.isEmpty());

        final String[] nonTerminalVariables = currentLine.substring(currentLine.indexOf('{') + 1, currentLine.indexOf('}')).split(",");

        currentLine = removeSpaces(scanner.nextLine());
        final String[] terminalVariables = currentLine.substring(currentLine.indexOf('{') + 1, currentLine.indexOf('}')).split(",");

        scanner.nextLine();
        System.out.println("Reading the productions...");
        List<Production> productionList = new ArrayList<>();

        do
        {
            currentLine = removeSpaces(scanner.nextLine());

            if ("}".equals(currentLine))
                break;

            productionList.add(parseProduction(currentLine));
        } while (true);


        return new Grammar(Arrays.asList(nonTerminalVariables), Arrays.asList(terminalVariables), productionList, "S");
    }

    private static Production parseProduction(final String currentLine)
    {
        return new Production(currentLine.substring(0, currentLine.indexOf(ARROW)), currentLine.substring(currentLine.indexOf(ARROW) + 1));
    }

    private static String removeSpaces(final String initialString)
    {
        return initialString.replaceAll("\\s+", "");
    }
}
