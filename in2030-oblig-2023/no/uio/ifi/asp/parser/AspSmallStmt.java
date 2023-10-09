package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


abstract class AspSmallStmt extends AspSyntax {

    AspSmallStmt(int n) {
        super(n);
    
    }
    public static AspSmallStmt parse(Scanner s){
        enterParser("small stmt");
        AspSmallStmt ass = null;

        switch(s.curToken().kind){ 
            case integerToken:
            case floatToken:
            case stringToken:
            case trueToken:
            case falseToken:
            case noneToken:
            case notToken:
            case plusToken:
            case minusToken:
            case nameToken:
            case leftBraceToken:
            case leftBracketToken:
            case leftParToken:
                if(s.anyEqualToken()){
                    ass = AspAssignment.parse(s);
                    break;
                }else{
                    ass = AspExprStmt.parse(s);
                    break;
                }
            case globalToken: 
                ass = AspGlobalStmt.parse(s);
                break; 
            case passToken: 
                ass = AspPassStmt.parse(s);
                break;
            case returnToken: 
                ass = AspReturnStmt.parse(s);
                break;
            default:
                parserError("no case valid between return, pass, global, expr and assignemt case", s.curLineNum());
        }
        leaveParser("small stmt");
        return ass;




        /* 
        if (s.curToken().kind == globalToken){
            ass = AspGlobalStmt.parse(s);
        }
        else if(s.curToken().kind == passToken){
            ass = AspPassStmt.parse(s);
        }
        else if(s.curToken().kind == returnToken){
            ass = AspReturnStmt.parse(s);
        }
        else if(s.anyEqualToken()){
            ass = AspAssignment.parse(s);
        }
        
        else {
            ass = AspExprStmt.parse(s);
        }
        
        leaveParser("small stmt");
        return ass;*/
        
    }
   

    @Override
    public abstract void prettyPrint();
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
