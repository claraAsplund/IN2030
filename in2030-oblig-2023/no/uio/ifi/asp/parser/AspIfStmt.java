package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;
import java.util.ArrayList;

public class AspIfStmt extends AspCompoundStmt{
    AspSuite suite;
    ArrayList<AspExpr> exprs = new ArrayList<>();
    ArrayList<AspSuite> suites = new ArrayList<>();


    AspIfStmt(int n) {
        super(n);
    }

    public static AspIfStmt parse(Scanner s){
        enterParser("if stmt");
        AspIfStmt ais = new AspIfStmt(s.curLineNum());

        skip(s, ifToken);
        ais.exprs.add(AspExpr.parse(s));
        skip(s, colonToken);
        ais.suites.add(AspSuite.parse(s));

        while(true){
            if(s.curToken().kind == elifToken){
                ais.exprs.add(AspExpr.parse(s));
                skip(s, colonToken);
                ais.suites.add(AspSuite.parse(s));
            }
            else{
                break;
            }
        }
        if(s.curToken().kind == elseToken){
            skip(s,elseToken);
            skip(s,colonToken);
            ais.suite = AspSuite.parse(s);
            
        }

        leaveParser("if stmt");
        return ais;
    }

    @Override
    public void prettyPrint(){
        prettyWrite("if ");

        for(int i = 0; i < exprs.size(); i++){
            if(i > 0 ){
                prettyWrite("elif ");
            }
            exprs.get(i).prettyPrint();
            prettyWrite(": ");
            suites.get(i).prettyPrint();
        }

        if (suite != null){
            prettyWrite("else:");
            suite.prettyPrint();
        }

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
