import automaton.FiniteAutomaton;
import grammar.Grammar;

public class Main
{
    public static void main(String[] args)
    {
        Grammar grammar = new Grammar()
        {
            @Override
            public String generateWord()
            {
                return "Vasile";
            }

            @Override
            public FiniteAutomaton toFiniteAutomaton()
            {
                return null;
            }
        };

//        System.out.println(grammar.generateWord());
//        grammar.generateWord();
//        grammar.generateWord();
//        grammar.generateWord();
//        grammar.generateWord();

        // Show example of converting the grammar to FA.
    }
}
