package no.uio.ifi.asp.parser;
import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class AspFactor extends AspSyntax {
    ArrayList<AspFactorPrefix> factorPrefixs = new ArrayList<>();
    ArrayList<AspPrimary> primaries = new ArrayList<>();
    ArrayList<AspFactorOpr> factorOprs = new ArrayList<>();
    
    
    AspFactor(int n) {
        super(n);
    
    }
    public static AspFactor parse(Scanner s){
        enterParser("factor");

        AspFactor af = new AspFactor(s.curLineNum());

        while(true){
          
            if(s.curToken().kind == TokenKind.plusToken ||
            s.curToken().kind == TokenKind.minusToken) {
                af.factorPrefixs.add(AspFactorPrefix.parse(s));
            } else {
                af.factorPrefixs.add(null);
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

        leaveParser("factor");
        return af;


    }

    @Override
    public void prettyPrint(){
        for (int i = 0; i < primaries.size(); i ++){
            if(i > 0){
                factorOprs.get(i-1).prettyPrint();
            }

            if(factorPrefixs.get(i) != null){
                factorPrefixs.get(i).prettyPrint();

            }
            primaries.get(i).prettyPrint();
            
        }
    
           


    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
}
