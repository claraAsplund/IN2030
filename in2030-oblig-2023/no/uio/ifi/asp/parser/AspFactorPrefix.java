package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFactorPrefix extends AspSyntax{
    Boolean isPlus;

    AspFactorPrefix(int n) {
        super(n);
    
    }
    public static AspFactorPrefix parse(Scanner s){
        enterParser("factor prefix");

        AspFactorPrefix afp = new AspFactorPrefix(s.curLineNum());

        if(s.curToken().kind == TokenKind.plusToken){
            afp.isPlus = true;
            skip(s,TokenKind.plusToken);
            
        }

        else if(s.curToken().kind == TokenKind.minusToken){
            afp.isPlus = false;
            skip(s,TokenKind.minusToken);

        } else {
            parserError("Expected an expression AspFactorPrefix but found a " +  s.curToken().kind + "!", s.curLineNum());
        }


        leaveParser("factor prefix");
        return afp; 

    }

    @Override
    public void prettyPrint(){
        if(isPlus){
            prettyWrite("+");
        }else {
            prettyWrite("-");

       }


    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
