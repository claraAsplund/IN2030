package no.uio.ifi.asp.parser;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

import java.util.ArrayList;

public class AspDictDisplay extends AspAtom{
    ArrayList<AspStringLiteral> strings = new ArrayList<>();
    ArrayList<AspExpr> exprs = new ArrayList<>();

    AspDictDisplay(int n) {
        super(n);
    
    }
    public static AspDictDisplay parse(Scanner s){
        enterParser("dict display");

        AspDictDisplay add = new AspDictDisplay(s.curLineNum());
        skip(s,leftBraceToken);
        while(s.curToken().kind != rightBraceToken){
            add.strings.add(AspStringLiteral.parse(s));
            skip(s,colonToken);
            add.exprs.add(AspExpr.parse(s));
            if(s.curToken().kind != commaToken) break;
            skip(s, commaToken);

        }
        skip(s,rightBraceToken);
        leaveParser("dict display");
        return add;

    }

    @Override
    public void prettyPrint(){
        prettyWrite(" { ");

        for(int i = 0; i < strings.size(); i++){
            if(i > 0){
                prettyWrite(" , ");
            }
            strings.get(i).prettyPrint();
            prettyWrite(" : ");
            exprs.get(i).prettyPrint();

        }
        prettyWrite(" } ");


    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
