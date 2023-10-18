package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspPrimarySuffix extends AspSyntax {

    AspPrimarySuffix(int n) {
        super(n);
    
    }
    public static AspPrimarySuffix parse(Scanner s){
       
        enterParser("primary suffix");
        AspPrimarySuffix aps = null;

        if(s.curToken().kind == TokenKind.leftParToken){
            aps = AspArguments.parse(s);
        }
        else if (s.curToken().kind == TokenKind.leftBracketToken){
            aps = AspSubscription.parse(s);
        } else{
            parserError("Expected an expression AspPrimarySuffix but found a " + 
            s.curToken().kind + "!", s.curLineNum());

        }    

        leaveParser("primary suffix");
        return aps;

    }   

    @Override
    public abstract void prettyPrint();
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
