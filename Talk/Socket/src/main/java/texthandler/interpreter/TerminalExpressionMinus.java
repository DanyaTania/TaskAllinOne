package texthandler.interpreter;

public class TerminalExpressionMinus extends AbstractMathExpression {
    @Override
    public void interpret(RPNContext context) {
        context.pushValue(-context.popValue() + context.popValue());
    }
}
