package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


import java.util.ArrayList;

public class AspFuncDef extends AspCompoundStmt{
    ArrayList<AspName> names = new ArrayList<>();
    AspAtom name = null;
    AspSuite suite= null;

    AspFuncDef(int n) {
        super(n);
    
    }
    public static AspFuncDef parse(Scanner s){
        enterParser("fund def");
        AspFuncDef afd = new AspFuncDef(s.curLineNum());

        skip(s, defToken);
            
        afd.name = AspName.parse(s);
        skip(s, leftParToken);

        while(s.curToken().kind != rightParToken){
            afd.names.add(AspName.parse(s));
            if(s.curToken().kind != commaToken)break;
                skip(s,commaToken);

        }
        skip(s,colonToken);
        afd.suite = AspSuite.parse(s);
        
        leaveParser("fund def");
        return afd;

    }

    @Override
    public void prettyPrint(){

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
