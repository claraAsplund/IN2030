package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


abstract class AspCompoundStmt extends AspStmt {

    AspCompoundStmt(int n) {
        super(n);
    
    }

    public static AspCompoundStmt parse(Scanner s){
        enterParser("compound stmt");
        AspCompoundStmt acs = null;

        switch(s.curToken().kind){
            case forToken: 
                acs = AspForStmt.parse(s);
                break;
            case ifToken:
                acs = AspIfStmt.parse(s);
                break;
            case whileToken:
                acs = AspWhileStmt.parse(s);
                break;
            case defToken:
                acs = AspFuncDef.parse(s);
                break;

        }
        leaveParser("compound stmt");
        return acs;

    }

    @Override
    public abstract void  prettyPrint();
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
