package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import no.uio.ifi.asp.scanner.TokenKind;

public class AspFactorPrefix extends AspSyntax{

    AspFactorPrefix(int n) {
        super(n);
    
    }
    public static AspFactorPrefix parse(Scanner s){
        enterParser("AspFactorPrefix");

        AspFactorPrefix afp = new AspFactorPrefix(s.curLineNum());
        if(s.curToken().kind == TokenKind.plusToken){
            skip(s,TokenKind.plusToken);
        }

        else if(s.curToken().kind == TokenKind.minusToken){
            skip(s,TokenKind.minusToken);

        } else {
            parserError("Expected an expression AspFactorPrefix but found a " +  s.curToken().kind + "!", s.curLineNum());
        }


        leaveParser("AspFactorPrefix");
        return afp; 

    }

    @Override
    public void prettyPrint(){

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
