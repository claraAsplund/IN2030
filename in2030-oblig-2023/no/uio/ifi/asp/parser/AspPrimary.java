package no.uio.ifi.asp.parser;
import java.util.ArrayList;

import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspPrimary extends AspSyntax{
    ArrayList<AspPrimarySuffix> suffixs = new ArrayList<>();
    AspAtom atom;

    AspPrimary(int n) {
        super(n);
    
    }
    public static AspPrimary parse(Scanner s){
        
        enterParser("primary");
        AspPrimary ap = new AspPrimary(s.curLineNum());
        
       
        while(s.curToken().kind  ==TokenKind.leftParToken || 
            s.curToken().kind  ==TokenKind.leftBracketToken) {
                ap.suffixs.add(AspPrimarySuffix.parse(s));
        }

        leaveParser("primary");
        return ap;

    }

    @Override
    public void prettyPrint(){

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
