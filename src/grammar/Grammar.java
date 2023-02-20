package grammar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import automaton.FiniteAutomaton;
import utils.StringUtils;

public class Grammar
{
    private final String[] nonTerminalVariables;
    private final String[] terminalVariables;
    private final List<Production> productionList;
    private final String startingCharacter;
    private final Map<String, List<String>> productionMap = new HashMap<>();
    private final Queue<String> wordsQueue = new LinkedList<>();
    private final Queue<String> breadthSearchQueue = new LinkedList<>();

    public Grammar(final String[] nonTerminalVariables,
                   final String[] terminalVariables,
                   final List<Production> productionList,
                   final String startingCharacter)
    {
        this.nonTerminalVariables = nonTerminalVariables;
        this.terminalVariables = terminalVariables;
        this.productionList = productionList;
        this.startingCharacter = startingCharacter;
        this.breadthSearchQueue.add("S");

        for (Production production : productionList)
        {
            if (!productionMap.containsKey(production.getLeftSide()))
                productionMap.put(production.getLeftSide(), new ArrayList<>());

            productionMap.get(production.getLeftSide()).add(production.getRightSide());
        }
    }

    public String generateWord()
    {
        if (!wordsQueue.isEmpty())
        {
            return wordsQueue.poll();
        }

        while (wordsQueue.size() < 10)
        {
            String currentString = breadthSearchQueue.poll();

            if (StringUtils.isAllLower(currentString))
            {
                wordsQueue.add(currentString);
                continue;
            }

            assert currentString != null;
            for (int i = 0; i < currentString.length(); i++)
            {
                char currentChar = currentString.charAt(i);
                if (Character.isUpperCase(currentChar))
                {
                    for (String substitute : productionMap.get(String.valueOf(currentChar)))
                    {
                        breadthSearchQueue.add(currentString.substring(0, i) + substitute + currentString.substring(i + 1));
                    }
                }
            }
        }

        return wordsQueue.poll();
    }
    public FiniteAutomaton toFiniteAutomaton()
    {
        return null;
    }

    @Override
    public String toString()
    {
        return "Grammar :\n" +
            "nonTerminalVariables = " + Arrays.toString(nonTerminalVariables) + "\n" +
            "terminalVariables = " + Arrays.toString(terminalVariables) + "\n" +
            "productionList = " + productionList + "\n" +
            "startingCharacter = " + startingCharacter + '\n';
    }
}
