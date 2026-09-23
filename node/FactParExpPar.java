package node;

import syntax.Token;

public class FactParExpPar extends Fact{
    protected Expr expr;

    public FactParExpPar(Expr expr) {
        this.expr = expr;
    }
}
