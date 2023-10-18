package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


public class AspNoneLiteral extends AspAtom{
    TokenKind savedNone;

    AspNoneLiteral(int n) {
        super(n);
    
    }

    public static AspNoneLiteral parse(Scanner s){
        enterParser("none literal");

        AspNoneLiteral anl = new AspNoneLiteral(s.curLineNum());
        if(s.curToken().kind == TokenKind.noneToken){
            anl.savedNone = s.curToken().kind;
            skip(s,noneToken);
        }

        leaveParser("none literal");
        return anl;
    }


    @Override
    public void prettyPrint(){
        prettyWrite(" none ");

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
