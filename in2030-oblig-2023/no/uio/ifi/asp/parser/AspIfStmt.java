package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspIfStmt extends AspCompoundStmt{
    AspExpr expr;
    AspSuite suite;

    AspIfStmt(int n) {
        super(n);
    
    }

    public static AspIfStmt parse(Scanner s){
        enterParser("if stmt");
        AspIfStmt ais = new AspIfStmt(s.curLineNum());

        skip(s, ifToken);
        ais.expr = AspExpr.parse(s);
        skip(s, colonToken);
        ais.suite = AspSuite.parse(s);

        while(true){
            if(s.curToken().kind == elifToken){
                ais.expr = AspExpr.parse(s);
                skip(s, colonToken);
                ais.suite = AspSuite.parse(s);

            }
            else{
                break;
            }
            
        }

        if(s.curToken().kind == elseToken){
            skip(s,colonToken);
            ais.suite = AspSuite.parse(s);
        }

        leaveParser("if stmt");
        return ais;



    }

    @Override
    public void prettyPrint(){

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
