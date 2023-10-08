package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

import java.util.ArrayList;

public class AspSuite extends AspSyntax{
    ArrayList<AspStmt> stmts = new ArrayList<>();
    AspSmallStmtList smallStmtList;

    AspSuite(int n) {
        super(n);
    
    }
    public static AspSuite parse(Scanner s){
        enterParser("suite");
        AspSuite as = new AspSuite(s.curLineNum());

        if(s.curToken().kind != newLineToken){
            as.smallStmtList = AspSmallStmtList.parse(s);
            
        }
        else if(s.curToken().kind == newLineToken){
            skip(s, newLineToken);
            skip(s, indentToken);

            as.stmts.add(AspStmt.parse(s));
            while(true){
                if(s.curToken().kind == dedentToken){
                   
                    break;
                }
                else{
                    as.stmts.add(AspStmt.parse(s));
                }
            }
            skip(s,dedentToken);
        }
        
        leaveParser("suite");
        return as;
    }

    @Override
    public void prettyPrint(){
        if (smallStmtList != null){
            smallStmtList.prettyPrint();
        }
        else{
            prettyWrite("\n");

            for(AspStmt stmt: stmts){
                prettyWrite("  ");
                stmt.prettyPrint();
            }
        }

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
