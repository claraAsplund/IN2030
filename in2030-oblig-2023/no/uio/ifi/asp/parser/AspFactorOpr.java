package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
import no.uio.ifi.asp.scanner.TokenKind;

public class AspFactorOpr extends AspSyntax {
    TokenKind savedFactorOpr;

    AspFactorOpr(int n) {
        super(n);
    
    }
   
    public static AspFactorOpr parse(Scanner s){
        enterParser("factor opr");

        AspFactorOpr afo = new AspFactorOpr(s.curLineNum());

        switch (s.curToken().kind){
            case astToken:
                afo.savedFactorOpr = s.curToken().kind;
                skip(s, TokenKind.astToken);    break;
            case slashToken:
                afo.savedFactorOpr = s.curToken().kind;
                skip(s, TokenKind.slashToken);  break;
            case percentToken:
                afo.savedFactorOpr = s.curToken().kind;
                skip(s, TokenKind.percentToken);    break;

            case doubleSlashToken:
                afo.savedFactorOpr = s.curToken().kind;
                skip(s, TokenKind.doubleSlashToken);    break;
            default:
                parserError("Expected an expression AspFactorOpr but found a " + 
                            s.curToken() + "!", s.curLineNum() );
        }
        leaveParser("factor opr");
        return afo;
    }
    
    @Override
    public void prettyPrint(){
        String savedFactorOprStr = savedFactorOpr.toString();
        prettyWrite(savedFactorOprStr);

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
