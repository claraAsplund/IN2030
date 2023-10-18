package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

import java.util.ArrayList;

public class AspListDisplay extends AspAtom{
    ArrayList<AspExpr> exprs = new ArrayList<>();
    
    AspListDisplay(int n) {
        super(n);
    
    }
    public static AspListDisplay parse(Scanner s){
        enterParser("list display");

        AspListDisplay ald = new AspListDisplay(s.curLineNum());
        skip(s,leftBracketToken);

        while(s.curToken().kind != rightBracketToken){
            ald.exprs.add(AspExpr.parse(s));
            if(s.curToken().kind!= commaToken)break;
            skip(s,commaToken);
        }
        
        skip(s,rightBracketToken);

        leaveParser("list display");
        return ald;


    }

    @Override
    public void prettyPrint(){
        prettyWrite("[");
        int nPrinted = 0;
        for(AspExpr expr: exprs){
            if(nPrinted > 0){
                prettyWrite(", ");
            }
            expr.prettyPrint();
            nPrinted++;
        }

        prettyWrite("]");

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
