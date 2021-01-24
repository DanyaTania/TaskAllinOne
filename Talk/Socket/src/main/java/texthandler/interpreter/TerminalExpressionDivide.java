package texthandler.interpreter;


public class TerminalExpressionDivide extends AbstractMathExpression{
    @Override
    public void interpret(RPNContext context) {
        int first = context.popValue();
        context.pushValue(context.popValue() / first);
    }
}
