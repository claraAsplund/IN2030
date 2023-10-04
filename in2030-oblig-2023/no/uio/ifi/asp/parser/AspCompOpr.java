package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import no.uio.ifi.asp.scanner.TokenKind;

public class AspCompOpr extends AspSyntax{

    AspCompOpr(int n) {
        super(n);
    
    }
    public static AspCompOpr parse(Scanner s){
        enterParser("AspCompOpr");

        AspCompOpr aco = new AspCompOpr(s.curLineNum());
        switch(s.curToken().kind) {
            case lessToken:
                skip(s, TokenKind.lessToken); break;
            case greaterToken:
                skip(s, TokenKind.greaterToken); break;
            case doubleEqualToken:
                skip(s, TokenKind.doubleEqualToken); break;
            case greaterEqualToken:
                skip(s, TokenKind.greaterEqualToken); break;
            case lessEqualToken:
                skip(s, TokenKind.lessEqualToken ); break;
            case notEqualToken:
                skip(s, TokenKind.notEqualToken); break;
            
            default:
                parserError("Expected an AspCompOpr but a " + s.curToken().kind + "!"
                 , s.curLineNum());
            
        }
        leaveParser("AspCompOpr");
        return aco;



    }

    @Override
    public void prettyPrint(){

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
