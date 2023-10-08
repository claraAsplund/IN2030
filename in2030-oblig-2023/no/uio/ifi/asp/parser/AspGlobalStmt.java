package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

import java.util.ArrayList;

public class AspGlobalStmt extends AspSmallStmt {
    ArrayList<AspName> names = new ArrayList<>();
    
    AspGlobalStmt(int n) {
        super(n);
    
    }
    public static AspGlobalStmt parse(Scanner s){
        enterParser("global stmt");
        AspGlobalStmt abs = new AspGlobalStmt(s.curLineNum());

        skip(s,globalToken);
        
        while(true){
            abs.names.add(AspName.parse(s));
            if(s.curToken().kind != commaToken) break;
            skip(s, commaToken);
        }
        leaveParser("global stmt");
        return abs;
            

    }

    @Override
    public void prettyPrint(){
        prettyWrite("global ");
        for (AspName name: names){
            name.prettyPrint();
        }

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
