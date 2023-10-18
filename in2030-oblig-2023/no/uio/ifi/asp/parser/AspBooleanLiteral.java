package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspBooleanLiteral extends AspAtom{
    
    boolean val = false;

    AspBooleanLiteral(int n) {
        super(n);
    
    }
    public static AspBooleanLiteral parse(Scanner s){
        enterParser("boolean literal");

        AspBooleanLiteral abl = new AspBooleanLiteral(s.curLineNum());
        if(s.curToken().kind == TokenKind.trueToken){
            skip(s, trueToken);
            abl.val = true;
        }else if(s.curToken().kind == TokenKind.falseToken){
            skip(s,falseToken);
        }
        
        leaveParser("boolean literal");
        return abl;

    }

    @Override
    public void prettyPrint(){

        String valStr;

        if (val) {
            valStr = "True";
        } else {
            valStr = "False";
        }
        prettyWrite(valStr);

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;
        //return new RuntimeBoolVale(val);
    }
    
}
