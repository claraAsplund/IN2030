package no.uio.ifi.asp.parser;
import java.util.ArrayList;

import no.uio.ifi.asp.scanner.TokenKind;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspComparison extends AspSyntax{
    ArrayList<AspTerm> terms =  new ArrayList<>();
    ArrayList<AspCompOpr> compOprs = new ArrayList<>();

    AspComparison(int n) {
        super(n);
    
    }
    public static AspComparison parse(Scanner s){
        enterParser("comparison");

        AspComparison ac = new AspComparison(s.curLineNum());

        while(true){
            ac.terms.add(AspTerm.parse(s));
            if(s.curToken().kind != lessToken &&
            s.curToken().kind != greaterToken &&
            s.curToken().kind != doubleEqualToken &&
            s.curToken().kind != greaterEqualToken &&
            s.curToken().kind != lessEqualToken &&
            s.curToken().kind != notEqualToken
            ) break;
            ac.compOprs.add(AspCompOpr.parse(s));
            
        }
        leaveParser("comparison");
        return ac;
        
    }

    @Override
    public void prettyPrint(){

        for(int i = 0; i < terms.size(); i++){
            if(i > 0){
                compOprs.get(i-1).prettyPrint();
            }
            
            terms.get(i).prettyPrint();

        }

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
