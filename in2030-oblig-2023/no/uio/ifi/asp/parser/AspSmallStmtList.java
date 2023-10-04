package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspSmallStmtList extends AspStmt {
    ArrayList<AspSmallStmt> smallStmts = new ArrayList<>();
    
    AspSmallStmtList(int n) {
        super(n);
    
    }

    public static AspSmallStmtList parse(Scanner s) {
        enterParser("AspSmallStmtList");

        AspSmallStmtList assl = new AspSmallStmtList(s.curLineNum());

        while(true){
            assl.smallStmts.add(AspSmallStmt.parse(s));
            skip(s, semicolonToken);
            if(s.curToken().kind == newLineToken) break;
        }

        leaveParser("AspSmallStmtList");
        return assl;
    }

    @Override
    public void prettyPrint(){

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
