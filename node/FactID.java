package node;

import syntax.Token;

public class FactID extends Fact{
    protected Token id;
    public FactID(Token token) {
        this.id = token;
    }
}
