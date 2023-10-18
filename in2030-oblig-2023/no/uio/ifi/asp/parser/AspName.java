package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspName extends AspAtom{
    String savedNameToken;

    AspName(int n) {
        super(n);
    
    }
    public static AspName parse(Scanner s){
        enterParser("name");
        AspName an = new AspName(s.curLineNum());

        an.savedNameToken = s.curToken().name;
        skip(s, nameToken);
        leaveParser("name");
        return an;
    }


    @Override
    public void prettyPrint(){
        prettyWrite(savedNameToken);

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
