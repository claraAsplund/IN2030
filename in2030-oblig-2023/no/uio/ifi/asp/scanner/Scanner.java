// © 2021 Dag Langmyhr, Institutt for informatikk, Universitetet i Oslo

package no.uio.ifi.asp.scanner;

import java.io.*;
import java.util.*;

import no.uio.ifi.asp.main.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class Scanner {
    private LineNumberReader sourceFile = null;
    private String curFileName;
    private ArrayList<Token> curLineTokens = new ArrayList<>();
    private Stack<Integer> indents = new Stack<>();
    private final int TABDIST = 4;


    public Scanner(String fileName) {
	curFileName = fileName;
	indents.push(0);

	try {
	    sourceFile = new LineNumberReader(
			    new InputStreamReader(
				new FileInputStream(fileName),
				"UTF-8"));
	} catch (IOException e) {
	    scannerError("Cannot read " + fileName + "!");
	}
    }


    private void scannerError(String message) {
	String m = "Asp scanner error";
	if (curLineNum() > 0)
	    m += " on line " + curLineNum();
	m += ": " + message;

	Main.error(m);
    }


    public Token curToken() {
	while (curLineTokens.isEmpty()) {
	    readNextLine();
	}
	return curLineTokens.get(0);
    }


    public void readNextToken() {
	if (! curLineTokens.isEmpty())
	    curLineTokens.remove(0);
    }


    private void readNextLine() {
	curLineTokens.clear();

	// Read the next line:
	String line = null;
	try {
	    line = sourceFile.readLine();
	    if (line == null) {
		sourceFile.close();
		sourceFile = null;
	    } else {
		Main.log.noteSourceLine(curLineNum(), line);
	    }
	} catch (IOException e) {
	    sourceFile = null;
	    scannerError("Unspecified I/O error!");
	}
	if(line == null){
		while(indents.pop() > 0){
			curLineTokens.add(new Token(dedentToken,curLineNum()));
		}
		curLineTokens.add(new Token(eofToken,curLineNum()));
		for (Token t: curLineTokens) 
	    Main.log.noteToken(t);
		//System.out.println(curLineTokens);
		return;
	}

	// - arbiedskode ->
	if(line.isBlank() == true){ //sjekker om linje er tom
		return;
	}
	//Omform alle innedende TAB-er til blanke ved exp
	line = expandLeadingTabs(line);
	//Tell antall innledende blanke 
	
	if (line.replaceAll(" ", "").charAt(0) == '#'){
		return;

	}

	int n;
	n = findIndent(line);
	if (n > indents.peek()){
		indents.push(n);
		curLineTokens.add(new Token(indentToken,curLineNum()));

	}
	while (n < indents.peek()){
		indents.pop();
		curLineTokens.add(new Token(dedentToken,curLineNum()));
	}
	if(n != indents.peek()){
		scannerError("indenteringsfeil!");
	}
	

	int pos = 0;
	while (pos < line.length()) {
		char c = line.charAt(pos);
		
		if (Character.isWhitespace(c)) { //ignorer blanke 
		
		}
	
		else if(c == '#'){
			break;
		} 
		else if (isDigit(c)) {
			String tallstreng = "";

			if(line.charAt(pos-1) == '.'){
				scannerError("float number can not start with '.'");
			}
			
			if(c == '0'){
				curLineTokens.add(new Token(integerToken,curLineNum()));
				//break;
				
			} else	{
				
				while((isDigit(c) || c == '.') && pos < line.length()){
					c = line.charAt(pos);
					if(isDigit(c) || c == '.'){
						tallstreng = tallstreng + c;
						pos++;
						//System.out.println(tallstreng);
						//System.out.println("pos: " + pos);
						//System.out.println(line.length());
					}
					else{
						break;
					}	
				}
				pos--;

			if(tallstreng.contains(".")){

				if(tallstreng.charAt(tallstreng.length()-1) == '.'){
					scannerError("float number can not end with '.'");
				}
				float myNum = Float.parseFloat(tallstreng); 
				Token t = new Token(TokenKind.floatToken,curLineNum());
				t.floatLit = myNum;
				curLineTokens.add(t);
				tallstreng = "";
			
			}else{
				int myNum =Integer.parseInt(tallstreng); 
				Token t = new Token(TokenKind.integerToken,curLineNum());
				t.integerLit = myNum;
				curLineTokens.add(t);
				tallstreng = "";
				}
			
			}
		} else if (c == '*'){
			curLineTokens.add(new Token(astToken,curLineNum()));
		} else if (c == '='){
			if(pos+1 < line.length() && line.charAt(pos+1) == '='){
				curLineTokens.add(new Token(doubleEqualToken,curLineNum()));
				pos++;
			} else
			curLineTokens.add(new Token(equalToken,curLineNum()));
		} else if (c == '>'){
			if(pos+1 < line.length() && line.charAt(pos+1) == '='){
				curLineTokens.add(new Token(greaterEqualToken,curLineNum()));
				pos++;
			} else
			curLineTokens.add(new Token(greaterToken,curLineNum()));
		} else if (c == '<'){
			if(pos+1 < line.length() && line.charAt(pos+1) == '='){
				curLineTokens.add(new Token(lessEqualToken,curLineNum()));
				pos++;
			} else
			curLineTokens.add(new Token(lessToken,curLineNum()));
		} else if (c == '-'){
			curLineTokens.add(new Token(minusToken,curLineNum()));
		} else if (c == '%'){
			curLineTokens.add(new Token(percentToken,curLineNum()));
		} else if (c == '+'){
			curLineTokens.add(new Token(plusToken,curLineNum()));
		} else if (c == '/'){
			if(pos+1 < line.length() && line.charAt(pos+1) == '/'){
				curLineTokens.add(new Token(doubleSlashToken,curLineNum()));
				pos++;
			} else
			curLineTokens.add(new Token(slashToken,curLineNum()));
		} else if (c == ':'){
			curLineTokens.add(new Token(colonToken,curLineNum()));
		} else if (c == ','){
			curLineTokens.add(new Token(commaToken,curLineNum()));
		} else if (c == '{'){
			curLineTokens.add(new Token(leftBraceToken,curLineNum()));
		} else if (c == '['){
			curLineTokens.add(new Token(leftBracketToken,curLineNum()));
		} else if (c == '('){
			curLineTokens.add(new Token(leftParToken,curLineNum()));
		} else if (c == '}'){
			curLineTokens.add(new Token(rightBraceToken,curLineNum()));
		} else if (c == ']'){
			curLineTokens.add(new Token(rightBracketToken,curLineNum()));
		} else if (c == ')'){
			curLineTokens.add(new Token(rightParToken,curLineNum()));
		} else if (c == ';'){
			curLineTokens.add(new Token(semicolonToken,curLineNum()));
		} else if (c == '!' && pos+1 < line.length() && line.charAt(pos+1) == '='){
			curLineTokens.add(new Token(notEqualToken,curLineNum()));
			pos++;
		}
		else if(isLetterAZ(c)){
			//int pos = 0;
			//for(pos = 0; pos <line.length(); pos++){
			//    char c = line.charAt(pos)
			//}

			String s = "" + c;

			while( (pos+1) <line.length() && (isLetterAZ(line.charAt(pos+1)) || isDigit(line.charAt(pos+1))) ){
				pos++;
				c = line.charAt(pos);
				s = s+c;

			}
			//curLineTokens.add(new Token(newLineToken,curLineNum()));
			if (s.equals("and")){
				Token andToken = new Token(TokenKind.andToken,curLineNum());
				curLineTokens.add(andToken);
				andToken.showInfo();

				//Token andToken = new Token(TokenKind.andToken);
				//curLineTokens.add(new Token(andToken,curLineNum()));

			}else if(s.equals("def")){
				Token defToken = new Token(TokenKind.defToken,curLineNum());
				curLineTokens.add(defToken);

			}else if(s.equals("elif")){
				Token elifToken = new Token(TokenKind.elifToken,curLineNum());
				curLineTokens.add(elifToken);

			}else if(s.equals( "else")){
				Token elseToken = new Token(TokenKind.elseToken,curLineNum());
				curLineTokens.add(elseToken);
			}else if(s.equals("False")){
				Token falseToken = new Token(TokenKind.falseToken,curLineNum());
				curLineTokens.add(falseToken);

			}else if(s.equals( "for")){
				Token forToken = new Token(TokenKind.forToken,curLineNum());
				curLineTokens.add(forToken);

			}else if(s.equals("global")){
				Token globalToken = new Token(TokenKind.globalToken,curLineNum());
				curLineTokens.add(globalToken);

			}else if(s.equals( "if")){
				Token ifToken = new Token(TokenKind.ifToken,curLineNum());
				curLineTokens.add(ifToken);

			}else if(s.equals("in")){
				Token inToken = new Token(TokenKind.inToken,curLineNum());
				curLineTokens.add(inToken);

			}else if(s.equals("None")){
				Token noneToken = new Token(TokenKind.noneToken,curLineNum());
				curLineTokens.add(noneToken);

			}else if(s.equals( "not")){
				Token notToken = new Token(TokenKind.notToken,curLineNum());
				curLineTokens.add(notToken);

			}else if(s.equals("or")){
				Token orToken = new Token(TokenKind.orToken,curLineNum());
				curLineTokens.add(orToken);

			}else if(s.equals("pass")){
				Token passToken = new Token(TokenKind.passToken,curLineNum());
				curLineTokens.add(passToken);
			}else if(s.equals("return")){
				Token returnToken = new Token(TokenKind.returnToken,curLineNum());
				curLineTokens.add(returnToken);

			}else if(s.equals("True")){
				Token trueToken = new Token(TokenKind.trueToken,curLineNum());
				curLineTokens.add(trueToken);

			}else if(s.equals("while")){
				Token whileToken = new Token(TokenKind.whileToken,curLineNum());
				curLineTokens.add(whileToken);

			}

			else{
				Token nameToken = new Token(TokenKind.nameToken,curLineNum());
				nameToken.name = s;
				curLineTokens.add(nameToken);
			}
		}
		else if(c == '"' || c == '\''){  

			String strLit = "" + c;
			while((pos+1)< line.length() && (line.charAt(pos+1) != '"' || line.charAt(pos+1) != '\'')){
				c = line.charAt(pos+1);
				strLit = strLit + c;
				pos++;
				if(line.charAt(pos) == '"' || line.charAt(pos) == '\''){
					pos++; 
					break;
				}
		
				if((pos+1) >= line.length()){
					scannerError("string does not end or does not end on cur line");
				} else if(line.charAt(pos+1) == '"'){
					strLit = strLit + '"';
					pos++; 
					break;
				} else if(line.charAt(pos+1) == '\''){
					strLit = strLit + '\'';
					pos++; 
					break;
				}
			}
			
			if(strLit.charAt(0) == strLit.charAt(strLit.length()-1)){
				String str = strLit.substring(1, strLit.length() - 1);
				//System.out.println(str);
				Token stringToken = new Token(TokenKind.stringToken,curLineNum());
				stringToken.stringLit = str;
				curLineTokens.add(stringToken);
			} else{
				scannerError("there is a mismatch between opening and closing symbols for stringLit token");
			} 
		} else {
			scannerError("Illegal character: '" +c+ "'!");
		}
	pos++;
	}
    
    curLineTokens.add(new Token(newLineToken,curLineNum()));

	// - slutt på arbeidskode <-

	
	//-- Must be changed in part 1:


	for (Token t: curLineTokens) 
	    Main.log.noteToken(t);
		//System.out.println(curLineTokens);
    }


    public int curLineNum() {
	return sourceFile!=null ? sourceFile.getLineNumber() : 0;
    }

    private int findIndent(String s) {
	int indent = 0;

	while (indent<s.length() && s.charAt(indent)==' ') indent++;
	return indent;
    }

    private String expandLeadingTabs(String s) {
		//-- Must be changed in part 1:
			int indent = 0;
			int e = 0;
	
			for (e = 0; e < s.length(); e ++){
				if(s.charAt(e) == ' '){
					indent ++;
				}
				else if(s.charAt(e) == '\t'){
					indent += 4 - (indent % 4);
	
				}
				else {
					break;
				}
	
			} 
			return " ".repeat(indent) + s.substring(e);
		}


    private boolean isLetterAZ(char c) {
	return ('A'<=c && c<='Z') || ('a'<=c && c<='z') || (c=='_');
    }


    private boolean isDigit(char c) {
	return '0'<=c && c<='9';
    }


    public boolean isCompOpr() {
	TokenKind k = curToken().kind;
	//-- Must be changed in part 2:
	return false;
    }


    public boolean isFactorPrefix() {
	TokenKind k = curToken().kind;
	//-- Must be changed in part 2:
	return false;
    }


    public boolean isFactorOpr() {
	TokenKind k = curToken().kind;
	//-- Must be changed in part 2:
	return false;
    }
	

    public boolean isTermOpr() {
	TokenKind k = curToken().kind;
	//-- Must be changed in part 2:
	return false;
    }


    public boolean anyEqualToken() {
	for (Token t: curLineTokens) {
	    if (t.kind == equalToken) return true;
	    if (t.kind == semicolonToken) return false;
	}
	return false;
    }
}
