package syntax;

import java.util.*;

/**
 * Lexical Analyzer for CS354 programming language
 */
public class Lexer {

    private String program; // source program being interpreted
    private int position; // index of next char in program

    private Set<String> whitespace = new HashSet<>();
    private Set<String> letters = new HashSet<>();
    private Set<String> keywords = new HashSet<>();
    private Set<String> numbers = new HashSet<>();
    private Set<String> operators = new HashSet<>();
    private Set<String> comments = new HashSet<>();

    /**
     * Creates a new lexical analyzer
     *
     * @param program - the program text to scan
     */
    public Lexer(String program) {
        this.program = program;
        position = 0;
        initWhitespace(whitespace);
        initLetters(letters);
        initKeywords(keywords);
        initNumbers(numbers);
        initOperators(operators);
        initComments(comments);
    }

    private void initKeywords(Set<String> keywords2) {
        // .... no keywords yet
    }

    private void initComments(Set<String> c) {
        c.add("#"); // inline
        c.add("`"); // multiline
    }

    private void initNumbers(Set<String> n) {
        fill(n, '0', '9');
        n.add(".");
    }

    private void initLetters(Set<String> s) {
        fill(s, 'A', 'Z');
        fill(s, 'a', 'z');
    }

    private void initOperators(Set<String> o) {
        o.add("+");
        o.add("-");
        o.add("*");
        o.add("/");
        o.add(";");
        o.add("=");
        o.add("(");
        o.add(")");
    }

    private void fill(Set<String> s, char lo, char hi) {
        for (char c = lo; c <= hi; c++) {
            s.add(c + "");
        }
    }

    private void initWhitespace(Set<String> s) {
        s.add(" ");
        s.add("\n");
        s.add("\t");
    }

    private void advance() {
        this.position++;
    }

    private String peek() {
        if (hasChar()) {
            return program.charAt(position) + "";
        } else {
            return null;
        }
    }

    private Token nextKwOp() {
        int old = this.position;

        advance();
        String lexeme = program.substring(old, position);
        return new Token(lexeme, lexeme);
    }

    private Token nextKwID() {
        int old = this.position;
        advance();
        while (hasChar() && letters.contains(peek())) {
            advance();
        }
        String lexeme = program.substring(old, position);
        if (keywords.contains(lexeme))
            return new Token(lexeme, lexeme);
        else
            return new Token("id", lexeme);
    }

    private Token nextKwNum() {
        int decimalCount = 0;
        int old = this.position;
        advance();
        while (hasChar() && numbers.contains(peek())) {
            if (Objects.equals(peek(), ".")) {
                decimalCount += 1;
            }
            // return early if a second decimal is found as #.#.# is not valid
            // and is instead treated as 2 numbers next to eachother
            if (decimalCount == 2) {
                String lexeme = program.substring(old, position);
                return new Token("num", lexeme);
            }
            advance();
        }
        String lexeme = program.substring(old, position);
        return new Token("num", lexeme);

    }

    private Token commentParse() {
        if (Objects.equals(peek(), "#")) {
            advance();
            while (!Objects.equals(peek(), "#") && !Objects.equals(peek(), "\n")) {
                advance(); 
            }
        } else if (Objects.equals(peek(), "`")) {
            advance();
            String test = peek();
            while (!Objects.equals(peek(), "`")) {
                advance();
            }
        }
        advance();
        return next();
    }

    /**
     * Determines the kind of the next token (e.g., "id") and calls the
     * appropriate method to scan the token's lexeme (e.g., "foo").
     *
     * @return the scanned token.
     */
    public Token next() {
        // skips through until a new token start is found
        while (hasChar() && whitespace.contains(peek())) {
            advance();
        }
        // logic for when a new token or the EOF is found
        if (!hasChar()) {
            return new Token("EOF");
        } else if (hasChar() && comments.contains(peek())) {
            return commentParse();
        } else if (hasChar() && letters.contains(peek())) {
            return nextKwID();
        } else if (hasChar() && numbers.contains(peek())) {
            return nextKwNum();
        } else if (hasChar() && operators.contains(peek())) {
            return nextKwOp();
        } else {
            System.err.println("illegal character at position " + position);
            position++;
            return next();
        }
    }

    /**
     * Determines if the current position of the lexer is in the bounds of the
     * program
     * 
     * @return true if there are more characters in program
     */
    public boolean hasChar() {
        return position < program.length();
    }

    /**
     * Getter for position of the lexer in the program
     * 
     * @return index of the current position of the scanner
     */
    public int getPosition() {
        return position;
    }
}