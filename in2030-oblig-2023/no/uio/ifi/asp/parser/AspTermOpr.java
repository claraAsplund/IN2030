package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspTermOpr extends AspSyntax{
    Boolean isPlus;

    AspTermOpr(int n) {
        super(n);
    
    }
    public static AspTermOpr parse(Scanner s){
        enterParser("term opr");

        AspTermOpr ato = new AspTermOpr(s.curLineNum());
       
        if (s.curToken().kind == TokenKind.plusToken){
            ato.isPlus = true;
            skip(s,plusToken);
        }
        else if(s.curToken().kind == TokenKind.minusToken){
            ato.isPlus = false;
            skip(s, minusToken);
        }
        else {
            parserError("Expected an expression AspTermOpr but found a " +
            s.curToken().kind + "!", s.curLineNum() );
        }

        leaveParser("term opr");
        return ato;
    
    
    }
    @Override
    public void prettyPrint(){
       
       if(isPlus){
            prettyWrite(" + ");
       }else {
            prettyWrite(" - ");

       }
    }

    
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
