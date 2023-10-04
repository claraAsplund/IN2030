package no.uio.ifi.asp.parser;
import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;

import no.uio.ifi.asp.runtime.RuntimeReturnValue;
import no.uio.ifi.asp.runtime.RuntimeScope;
import no.uio.ifi.asp.runtime.RuntimeValue;

public class AspFactor extends AspSyntax {
    ArrayList<AspFactorPrefix> factorPrefixs = new ArrayList<>();
    ArrayList<AspPrimary> primaries = new ArrayList<>();
    ArrayList<AspFactorOpr> factorOprs = new ArrayList<>();
    
    AspFactor(int n) {
        super(n);
    
    }
    public static AspFactor parse(Scanner s){
        enterParser("AspFactor");

        AspFactor af = new AspFactor(s.curLineNum());

        while(true){
          
            if(s.curToken().kind == TokenKind.plusToken ||
            s.curToken().kind == TokenKind.minusToken) {
                af.factorPrefixs.add(AspFactorPrefix.parse(s));
            }
            af.primaries.add(AspPrimary.parse(s));

            if(s.curToken().kind != TokenKind.astToken &&
            s.curToken().kind != TokenKind.slashToken &&
            s.curToken().kind != TokenKind.percentToken &&
            s.curToken().kind != TokenKind.doubleSlashToken) {
                break;
            }

            af.factorOprs.add(AspFactorOpr.parse(s));
            
        }

        leaveParser("AspFactor");
        return af;


    }

    @Override
    public void prettyPrint(){

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
}
