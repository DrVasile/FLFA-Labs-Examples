package grammar;

import automaton.FiniteAutomaton;

public abstract class Grammar
{
    private char[] nonTerminalVariables;
    private char[] terminalVariables;
    private Production[] productions;
    private char startingCharacter;

    public abstract String generateWord();
    public abstract FiniteAutomaton toFiniteAutomaton();
}
