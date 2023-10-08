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
        enterParser("small stmt list");

        AspSmallStmtList assl = new AspSmallStmtList(s.curLineNum());
        //assl.smallStmts.add(AspSmallStmt.parse(s));
        
        while(true){
            assl.smallStmts.add(AspSmallStmt.parse(s));
            if (s.curToken().kind == semicolonToken) {
                skip(s, semicolonToken);
            }
            
            if(s.curToken().kind == newLineToken) {
                skip(s, newLineToken);
                break;
            }
        }
        
          
        /* 
        while(true){
            if(s.curToken().kind == newLineToken){
                skip(s,newLineToken);
                break;
            }else if(s.curToken().kind == semicolonToken){
                skip(s,semicolonToken);
                if(s.curToken().kind == newLineToken){
                    skip(s,newLineToken);   
                    break;
        
                }else{
                    assl.smallStmts.add(AspSmallStmt.parse(s));
                }
            }
        }
        */
        
        
        leaveParser("small stmt list");
        return assl;
    }

    @Override
    public void prettyPrint(){
        
        for(AspSmallStmt smallStmt : smallStmts) {
 
            smallStmt.prettyPrint();
            prettyWrite(";");

            
        }
        prettyWrite("\n");

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
