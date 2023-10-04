package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import no.uio.ifi.asp.scanner.TokenKind;

public class AspFactorOpr extends AspSyntax {

    AspFactorOpr(int n) {
        super(n);
    
    }
   
    public static AspFactorOpr parse(Scanner s){
        enterParser("AspFactorOpr");

        AspFactorOpr afo = new AspFactorOpr(s.curLineNum());

        switch (s.curToken().kind){
            case astToken:
                skip(s, TokenKind.astToken);    break;
            case slashToken:
                skip(s, TokenKind.slashToken);  break;
            case percentToken:
                skip(s, TokenKind.percentToken);    break;
            case doubleSlashToken:
                skip(s, TokenKind.doubleSlashToken);    break;
            default:
                parserError("Expected an expression AspFactorOpr but found a " + 
                            s.curToken() + "!", s.curLineNum() );
        }
        leaveParser("AspFactorOpr");
        return afo;
    }
    
    @Override
    public void prettyPrint(){

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
