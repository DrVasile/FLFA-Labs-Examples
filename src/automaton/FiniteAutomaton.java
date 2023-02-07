package automaton;

public abstract class FiniteAutomaton
{
    private char[] possibleStates;
    private char[] alphabet;
    private Transition[] transitions;
    private char initialState;
    private char[] finalStates;

    public abstract boolean wordIsValid();
}
