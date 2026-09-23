package syntax;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for syntax.Lexer
 *
 * Uses Junit5.
 */
public class LexerTest {

    /**
     * Simply creates a 'program' that has only one token. When scanned,
     * the test checks to see that the current token is of the correct type.
     *
     * Try more than one Token in a different test case!
     * 
     * @throws SyntaxException - This suppresses the need for a try/catch block.
     */
    @Test
    public void testOneNumber() throws SyntaxException {

        String prg = "4";
        Lexer lexer = new Lexer(prg);
        assertEquals(new Token("num", "4"), lexer.next());
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }

    @Test
    public void testTwoNumber() throws SyntaxException {

        String prg = "4 3";
        Lexer lexer = new Lexer(prg);
        assertEquals(new Token("num", "4"), lexer.next());
        assertEquals(new Token("num", "3"), lexer.next());
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }

    @Test
    public void testTwoNumberTwoLines() throws SyntaxException {

        String prg = "4\n3";
        Lexer lexer = new Lexer(prg);
        assertEquals(new Token("num", "4"), lexer.next());
        assertEquals(new Token("num", "3"), lexer.next());
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }

    

    @Test
    public void testOneDec() throws SyntaxException {

        String prg = ".";
        Lexer lexer = new Lexer(prg);
        assertEquals(new Token("num", "."), lexer.next());
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }

    @Test
    public void testOneDecNumber() throws SyntaxException {

        String prg = "4.3";
        Lexer lexer = new Lexer(prg);
        assertEquals(new Token("num", "4.3"), lexer.next());
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }

    @Test
    public void testOneDecNumberTwoLines() throws SyntaxException {

        String prg = "4.\n3";
        Lexer lexer = new Lexer(prg);
        assertEquals(new Token("num", "4."), lexer.next());
        assertEquals(new Token("num", "3"), lexer.next());
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }
    

    @Test
    public void testTwoDecOneNumber() throws SyntaxException {

        String prg = "4.3.1";
        Lexer lexer = new Lexer(prg);
        assertEquals(new Token("num", "4.3"), lexer.next());
        assertEquals(new Token("num", ".1"), lexer.next());
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }

    @Test
    public void testMixNumAndID() throws SyntaxException {

        String prg = "4.3D";
        Lexer lexer = new Lexer(prg);
        assertEquals(new Token("num", "4.3"), lexer.next());
        assertEquals(new Token("id", "D"), lexer.next());
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }

    /**
     * Tests that the lexer can recognize an identifier
     *
     * @throws SyntaxException
     */
    @Test
    public void testOneIdentifier() throws SyntaxException {

        String prg = "x";
        Lexer lexer = new Lexer(prg);
        assertEquals(new Token("id", "x"), lexer.next());

        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }

    @Test 
    public void testIDWithNum() throws SyntaxException {
        String prg = "a1bcd";
        Lexer lexer = new Lexer(prg);
        assertEquals(new Token("id", "a1bcd"), lexer.next());
    }

    /**
     * Tests that the lexer can recognize an operator (the semicolon)
     * 
     * @throws SyntaxException
     */
    @Test
    public void testOneOperator() throws SyntaxException {

        String prg = ";";
        Lexer lexer = new Lexer(prg);

        assertEquals(new Token(";", ";"), lexer.next());
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }

    @Test
    public void testTwoOperator() throws SyntaxException {

        String prg = ";+";
        Lexer lexer = new Lexer(prg);

        assertEquals(new Token(";", ";"), lexer.next());
        assertEquals(new Token("+", "+"), lexer.next());
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }

    @Test
    public void testNumOperator() throws SyntaxException {

        String prg = "3+";
        Lexer lexer = new Lexer(prg);

        assertEquals(new Token("num", "3"), lexer.next());
        assertEquals(new Token("+", "+"), lexer.next());
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }

    @Test
    public void testIDOperator() throws SyntaxException {

        String prg = "D+";
        Lexer lexer = new Lexer(prg);

        assertEquals(new Token("id", "D"), lexer.next());
        assertEquals(new Token("+", "+"), lexer.next());
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }

    @Test
    public void testInlineCommentOneLine() throws SyntaxException {
        String prg = "#abcd#";
        Lexer lexer = new Lexer(prg);
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }

    @Test
    public void testInlineCommentTwoLine() throws SyntaxException {
        String prg = "#abcd\nabcd";
        Lexer lexer = new Lexer(prg);
        assertEquals(new Token("id", "abcd"), lexer.next());
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }
    
    @Test
    public void testBlockCommentOneLine() throws SyntaxException {
        String prg = "`hello`";
        Lexer lexer = new Lexer(prg);
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }
    
    @Test
    public void testBlockCommentTwoLine() throws SyntaxException {
        String prg = "`hello\nworld\nthe\nworld\nsays\nhello`";
        Lexer lexer = new Lexer(prg);
        assertEquals(new Token("EOF", "EOF"), lexer.next());
    }

    @Test 
    public void testIllegalCharacter() throws SyntaxException {
        String prg = "@abcd";
        Lexer lexer = new Lexer(prg);
        assertEquals(new Token("id", "abcd"), lexer.next());
    }




}