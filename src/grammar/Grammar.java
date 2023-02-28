package grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import automaton.FiniteAutomaton;
import automaton.Transition;
import utils.StringUtils;

public class Grammar
{
    private final List<String> nonTerminalVariables;
    private final List<String> terminalVariables;
    private final List<Production> productionList;
    private final String startingCharacter;
    private final Map<String, List<String>> productionMap = new HashMap<>();
    private final Queue<String> wordsQueue = new LinkedList<>();
    private final Queue<String> breadthSearchQueue = new LinkedList<>();

    public Grammar(final List<String> nonTerminalVariables,
                   final List<String> terminalVariables,
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

    public Grammar(final FiniteAutomaton automaton)
    {
        final Set<String> finalStatesSet = new HashSet<>(automaton.getFinalStates());
        nonTerminalVariables = automaton.getPossibleStates().stream().filter(value -> !finalStatesSet.contains(value)).collect(Collectors.toList());
        terminalVariables = automaton.getAlphabet();
        startingCharacter = automaton.getInitialState();
        productionList = new ArrayList<>();

        for (Transition transition : automaton.getTransitions())
        {
            productionList.add(new Production(transition));
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

    public List<String> getNonTerminalVariables()
    {
        return nonTerminalVariables;
    }

    public List<String> getTerminalVariables()
    {
        return terminalVariables;
    }

    public List<Production> getProductionList()
    {
        return productionList;
    }

    public String getStartingCharacter()
    {
        return startingCharacter;
    }

    public Map<String, List<String>> getProductionMap()
    {
        return productionMap;
    }

    @Override
    public String toString()
    {
        return "Grammar :\n" +
            "nonTerminalVariables = " + nonTerminalVariables.toString() + "\n" +
            "terminalVariables = " + terminalVariables.toString() + "\n" +
            "productionList = " + productionList + "\n" +
            "startingCharacter = " + startingCharacter + '\n';
    }
}
