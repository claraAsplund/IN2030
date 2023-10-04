package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import no.uio.ifi.asp.scanner.TokenKind;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspNotTest extends AspSyntax{
    AspComparison comparison;


    AspNotTest(int n) {
        super(n);
    
    }
    public static AspNotTest parse(Scanner s){
        enterParser("AspNotTest");

        AspNotTest ant = new AspNotTest(s.curLineNum());
        if (s.curToken().kind == notToken) {
            skip(s, notToken);
        }
        ant.comparison = AspComparison.parse(s);

        leaveParser("AspNotTest");
        return ant;




    }
    @Override
    public void prettyPrint(){

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
