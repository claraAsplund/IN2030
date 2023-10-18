package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


public class AspCompOpr extends AspSyntax{
    TokenKind savedCompOpr;

    AspCompOpr(int n) {
        super(n);
    
    }
    public static AspCompOpr parse(Scanner s){
        enterParser("comp opr");

        AspCompOpr aco = new AspCompOpr(s.curLineNum());
        switch(s.curToken().kind) {
            case lessToken:
                aco.savedCompOpr = s.curToken().kind;
                skip(s, TokenKind.lessToken); break;
                
            case greaterToken:
                aco.savedCompOpr = s.curToken().kind;
                skip(s, TokenKind.greaterToken); break;
            case doubleEqualToken:
                aco.savedCompOpr = s.curToken().kind;
                skip(s, TokenKind.doubleEqualToken); break;
            case greaterEqualToken:
                aco.savedCompOpr = s.curToken().kind;
                skip(s, TokenKind.greaterEqualToken); break;
            case lessEqualToken:
                aco.savedCompOpr = s.curToken().kind;
                skip(s, TokenKind.lessEqualToken ); break;
            case notEqualToken:
                aco.savedCompOpr = s.curToken().kind;
                skip(s, TokenKind.notEqualToken); break;
            
            default:
                parserError("Expected an AspCompOpr but a " + s.curToken().kind + "!"
                 , s.curLineNum());
            
        }
        leaveParser("comp opr");
        return aco;

    }

    @Override
    public void prettyPrint(){
        String savedCompOprStr = " " + savedCompOpr.toString() + " ";
        prettyWrite(savedCompOprStr); 

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
