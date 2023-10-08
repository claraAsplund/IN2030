package no.uio.ifi.asp.parser;
import java.util.ArrayList;
import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.scanner.*;
import no.uio.ifi.asp.runtime.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;


public class AspTerm extends AspSyntax{
    ArrayList<AspFactor> factors = new ArrayList<>();
    ArrayList <AspTermOpr> termOprs = new ArrayList<>();

    AspTerm(int n) {  
        super(n);
    
    }
    public static AspTerm parse(Scanner s){
        enterParser("term");

        AspTerm at = new AspTerm(s.curLineNum());
       
        while(true){
            at.factors.add(AspFactor.parse(s));
            if (s.curToken().kind != plusToken &&
            s.curToken().kind != minusToken) break;
            
            at.termOprs.add(AspTermOpr.parse(s));
        }

        leaveParser("term");
        return at;

    }

    @Override
    public void prettyPrint(){
       
        for (int i = 0; i < factors.size(); i ++){
            if(i > 0){
                termOprs.get(i-1).prettyPrint();
            }
            factors.get(i).prettyPrint();

        }

    }
    
    @Override
    public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue{
     
        return null;

    }
    
}
