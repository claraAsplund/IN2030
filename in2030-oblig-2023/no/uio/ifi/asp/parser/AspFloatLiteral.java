package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFloatLiteral extends AspAtom {
    Double savedFloat;

    AspFloatLiteral(int n) {
        super(n);
    
    }
    public static AspFloatLiteral parse(Scanner s){
        enterParser("float literal");

        AspFloatLiteral afl = new AspFloatLiteral(s.curLineNum());

        afl.savedFloat = s.curToken().floatLit;
        skip(s,floatToken);

        leaveParser("float literal");
        return afl;


    }

    @Override
    public void prettyPrint(){
        String savedFloatStr = savedFloat.toString();
        prettyWrite(savedFloatStr);

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
