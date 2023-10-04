package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspTermOpr extends AspSyntax{

    AspTermOpr(int n) {
        super(n);
    
    }
    public static AspTermOpr parse(Scanner s){
        enterParser("AspTermOpr");

        AspTermOpr ato = new AspTermOpr(s.curLineNum());
       
        if (s.curToken().kind == TokenKind.plusToken){
            skip(s,plusToken);
        }
        else if(s.curToken().kind == TokenKind.minusToken){
            skip(s, minusToken);
        }
        else {
            parserError("Expected an expression AspTermOpr but found a " +
            s.curToken().kind + "!", s.curLineNum() );
        }

        leaveParser("AspTermOpr");
        return ato;
    
    
    }
    @Override
    public void prettyPrint(){

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
