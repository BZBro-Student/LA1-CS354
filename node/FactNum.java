package node;

import syntax.Token;

public class FactNum extends Fact {
    protected Token num;
    public FactNum(Token token) {
        this.num = token;
    }
    
}
