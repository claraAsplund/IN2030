package no.uio.ifi.asp.parser;
import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;
import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;


public class AspTerm extends AspSyntax{
    ArrayList<AspFactor> factors = new ArrayList<>();
    ArrayList <AspTermOpr> termOprs = new ArrayList<>();

    AspTerm(int n) {  
        super(n);
    
    }
    public static AspTerm parse(Scanner s){
        enterParser("AspTerm");

        AspTerm at = new AspTerm(s.curLineNum());
       
        while(true){
            at.factors.add(AspFactor.parse(s));
            if (s.curToken().kind != plusToken &&
            s.curToken().kind != minusToken) break;
            at.termOprs.add(AspTermOpr.parse(s));
        }
        

        leaveParser("AspTerm");
        return at;

    }


    @Override
    public void prettyPrint(){

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
