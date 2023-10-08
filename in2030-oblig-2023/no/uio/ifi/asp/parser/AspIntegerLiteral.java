package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspIntegerLiteral extends AspAtom{
    Long savedIntegerlit;

    AspIntegerLiteral(int n) {
        super(n);
    
    }
    public static AspIntegerLiteral parse(Scanner s){
        enterParser("integer literal");

        AspIntegerLiteral ail = new AspIntegerLiteral(s.curLineNum());

        ail.savedIntegerlit = s.curToken().integerLit;
        skip(s,integerToken);

        leaveParser("integer literal");
        return ail;


    }
    @Override
    public void prettyPrint(){
        String savedIntegerlitStr = savedIntegerlit.toString();
        prettyWrite(savedIntegerlitStr);

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
