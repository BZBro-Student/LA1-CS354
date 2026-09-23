package syntax;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * To assist you in creating correct parse trees.
 */
class ParserTest {

    @Test
    void testIdentifier() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "x";

        System.out.println(parser.parse(prg).toString());
    }

    @Test
    void testAddition() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "x + 3";

        System.out.println(parser.parse(prg).toString());
    }

    @Test
    void testSubtraction() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "10-4-3";

        System.out.println(parser.parse(prg).toString());
    }

    @Test
    void testComplexEquation() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "(x+2) / 4";

        System.out.println(parser.parse(prg).toString());
    }

    @Test
    void testParenthesis() throws SyntaxException {
        Parser parser = new Parser();
        String prg = "((x))";

        System.out.println(parser.parse(prg).toString());
    }

    @Test
    void testSyntax_1() {

        Parser parser = new Parser();
        String prg = "x * *";

        assertThrows(SyntaxException.class, () -> {
            parser.parse(prg);
        });
    }

    @Test
    void testSyntax_2() {

        Parser parser = new Parser();
        String prg = "((x)";

        assertThrows(SyntaxException.class, () -> {
            parser.parse(prg);
        });
    }

    @Test
    void testSyntax_3() {

        Parser parser = new Parser();
        String prg = "x+ -3";

        assertThrows(SyntaxException.class, () -> {
            parser.parse(prg);
        });
    }
}