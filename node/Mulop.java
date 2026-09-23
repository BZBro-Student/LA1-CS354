package node;

import syntax.Token;

public class Mulop extends Node {
    protected Token mulop;

    public Mulop(int position, Token mulop) {

        this.position = position;
        this.mulop = mulop;
    }

}
