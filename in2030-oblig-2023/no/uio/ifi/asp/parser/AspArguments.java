package no.uio.ifi.asp.parser;
import java.util.ArrayList;

import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspArguments extends AspPrimarySuffix{

    ArrayList<AspExpr> exprs = new ArrayList<>();

    AspArguments(int n) {
        super(n);
    
    }
    public static AspArguments parse(Scanner s) {
        enterParser("arguments");

        AspArguments aa = new AspArguments(s.curLineNum());
        
        skip(s, leftParToken);
        
        while(s.curToken().kind!= rightParToken){
            aa.exprs.add(AspExpr.parse(s));
            if(s.curToken().kind != commaToken) break;
            skip(s, commaToken);

        }
        skip(s, rightParToken);

        leaveParser("arguments");
        return aa;
    }

    @Override
    public void prettyPrint(){

        prettyWrite("(");
        int nPrinted = 0;
        for(AspExpr expr: exprs){
            if(nPrinted > 0){
                prettyWrite(", ");
            }
            expr.prettyPrint();
            nPrinted++;
        }
        prettyWrite(")");
    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
