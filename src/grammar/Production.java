package grammar;

import automaton.Transition;

public class Production
{
    private String leftSide;
    private String rightSide;

    public Production(final String leftSide, final String rightSide)
    {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }

    public Production(final Transition transition)
    {
        this.leftSide = transition.getCurrentState();
        this.rightSide = transition.getTransitionLabel() + transition.getNextState();
    }

    public String getLeftSide()
    {
        return leftSide;
    }

    public void setLeftSide(String leftSide)
    {
        this.leftSide = leftSide;
    }

    public String getRightSide()
    {
        return rightSide;
    }

    public void setRightSide(String rightSide)
    {
        this.rightSide = rightSide;
    }

    @Override
    public String toString()
    {
        return "{" + " leftSide = " + leftSide + ", rightSide = " + rightSide + " }";
    }
}
