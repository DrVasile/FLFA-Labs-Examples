package automaton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import grammar.Grammar;
import grammar.Production;

public class FiniteAutomaton
{
    private static final String FINAL_STATE = "X";

    private final List<String> alphabet;
    private final String initialState;
    private final List<String> finalStates;
    private final List<String> possibleStates;
    private final List<Transition> transitions;

    public FiniteAutomaton(final List<String> alphabet,
                           final String initialState,
                           final List<String> finalStates,
                           final List<String> possibleStates,
                           final List<Transition> transitions)
    {
        this.alphabet = alphabet;
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.possibleStates = possibleStates;
        this.transitions = transitions;
    }

    public FiniteAutomaton(final Grammar grammar)
    {
        this.alphabet = new ArrayList<>(grammar.getTerminalVariables());
        this.possibleStates = new ArrayList<>(grammar.getNonTerminalVariables());
        this.possibleStates.add(FINAL_STATE);
        this.initialState = grammar.getStartingCharacter();
        this.finalStates = Collections.singletonList(FINAL_STATE);
        this.transitions = new ArrayList<>();

        for (Production production : grammar.getProductionList())
        {
            this.transitions.add(new Transition(production));
        }
    }

    public boolean wordIsValid(final String word)
    {
        for (char currentChar : word.toCharArray())
        {

        }

        return false;
    }

    public List<String> getAlphabet()
    {
        return alphabet;
    }

    public String getInitialState()
    {
        return initialState;
    }

    public List<String> getFinalStates()
    {
        return finalStates;
    }

    public List<String> getPossibleStates()
    {
        return possibleStates;
    }

    public List<Transition> getTransitions()
    {
        return transitions;
    }
}
