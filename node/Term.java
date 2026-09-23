package node;

/**
 * TODO:
 */
public class Term extends Node {

    protected Fact fact;
    protected Mulop mulop;
    protected Term term;

    public Term(Fact fact, Mulop mulop, Term term) {
        this.fact = fact;
        this.mulop = mulop;
        this.term = term;
    }

    public Term(Fact fact) {
        this.fact = fact;
        this.mulop = null;
        this.term = null;        

    }

    public void append(Term term) {
        if (this.term == null) {
            this.mulop=term.mulop;
            this.term=term;
            term.mulop=null;
        } else {
            this.term.append(term);
        }
    }
}