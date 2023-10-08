package no.uio.ifi.asp.parser;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

import java.util.ArrayList;

public class AspAssignment extends AspSmallStmt {
    AspName name;
    ArrayList<AspSubscription>subscriptions = new ArrayList<>();
    AspExpr expr;

    AspAssignment(int n) {
        super(n);
    
    }
    public static AspAssignment parse(Scanner s){
        enterParser("assignment");

        AspAssignment aa = new AspAssignment(s.curLineNum());
        aa.name = AspName.parse(s);
        while(s.curToken().kind != equalToken){
            aa.subscriptions.add(AspSubscription.parse(s));
        }
        skip(s,equalToken);
        aa.expr= AspExpr.parse(s);

        leaveParser("assignment");
        return aa;

    }

    @Override
    public void prettyPrint(){
        name.prettyPrint();
        for(AspSubscription as: subscriptions ){
            as.prettyPrint();
        }
        prettyWrite(" = ");
        expr.prettyPrint();

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
