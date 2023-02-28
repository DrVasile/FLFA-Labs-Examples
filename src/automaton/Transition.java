package automaton;

import grammar.Production;

public class Transition
{
    private final String currentState;
    private final String nextState;
    private final String transitionLabel;

    public Transition(final Production production)
    {
        currentState = production.getLeftSide();
        nextState = String.valueOf(production.getRightSide().charAt(production.getRightSide().length() - 1));
        transitionLabel = production.getRightSide().substring(0, production.getRightSide().length() - 1);
    }

    public String getCurrentState()
    {
        return currentState;
    }

    public String getNextState()
    {
        return nextState;
    }

    public String getTransitionLabel()
    {
        return transitionLabel;
    }
}
