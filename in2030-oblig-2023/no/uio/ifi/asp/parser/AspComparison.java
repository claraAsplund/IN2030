package no.uio.ifi.asp.parser;
import java.util.ArrayList;

import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;
import no.uio.ifi.asp.scanner.Scanner;
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
        enterParser("AspComparison");

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
        leaveParser("AspComparison");
        return ac;
        
    }

    @Override
    public void prettyPrint(){

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
