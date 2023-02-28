# Intro to formal languages. Regular grammars. Finite Automata.

### University: Technical University of Moldova
### Course: Formal Languages & Finite Automata
### Author: Vasile Drumea

----

## Theory
&ensp;&ensp;&ensp; A formal language can be considered to be the media or the format used to convey information from a 
sender entity to the one that receives it. The usual components of a language are:

* The alphabet: Set of valid characters;
* The vocabulary: Set of valid words;
* The grammar: Set of rules/constraints over the lang.

&ensp;&ensp;&ensp; Now these components can be established in an infinite amount of configurations, which actually means
that whenever a language is being created, it's components should be selected in a way to make it as appropriate for 
its use case as possible. Of course sometimes it is a matter of preference, that's why we ended up with lots of 
natural/programming/markup languages which might accomplish the same thing.


## Objectives:

* Get familiar with formal languages, regular grammars & finite automata.
* Implement functionality for regular grammars and finite automata.
* Showcase the execution of the program.


## Implementation description

&ensp;&ensp;&ensp; In this project, at least at the current state of it, there are 2 main classes which are used to 
represent 2 types from the domain of formal languages. One is the Grammar class used to represent a regular grammar 
object.
```
public class Grammar
{
    private final List<String> nonTerminalVariables;
    private final List<String> terminalVariables;
    private final List<Production> productionList;
    private final String startingCharacter;
}
```

&ensp;&ensp;&ensp; The second is the FiniteAutomaton class used to represent a finite automaton.
```
public class FiniteAutomaton
{
    private final List<String> alphabet;
    private final String initialState;
    private final List<String> finalStates;
    private final List<String> possibleStates;
    private final List<Transition> transitions;
}
```

&ensp;&ensp;&ensp; The classes have all arguments constructors implemented so that the client class could instantiate 
objects of these types by reading the attributes from the provided .txt files, but also they have constructors that can
be used to convert a grammar to automaton and vice-versa;

```
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
```

&ensp;&ensp;&ensp; In order to implement the generation of words that belong to the language expressed by a particular 
grammar, the corresponding method called __*generateWord()*__ uses a BFS like approach and just traverses through the 
possible words generating them in batches. This way, when a class requires a new word to be generated if in the current
batch there are words left, then it will return an existing one. On the other hand, if the batch is exhausted it will do
the generation again.

```
public String generateWord()
{
    if (!wordsQueue.isEmpty())
    {
        return wordsQueue.poll();
    }

    while (wordsQueue.size() < 10)
    {
        // Generate words by BFS-ing the grammar productions.
    }

    return wordsQueue.poll();
}
```


## Conclusions / Screenshots / Results
Some conclusions...


## References
No references for this one...




















