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


	// - arbiedskode ->

	int pos = 0;
	while (pos < line.length()) {
		char c = line.charAt(pos++);
		if (Character.isWhitespace(c)) {
		
		} 
		else if(c == '#'){
			return;
		} 
		else if (isDigit(c)) {
			//private ArrayList<Type> arrayList= new ArrayList<>();
			//char next = line.charAt(pos++);
			//int myNum = 0;
			//while(isDigit(next)){
			//	myNum = myNum + next;	
			//}
			Token t = new Token(integerToken,curLineNum());
			t.integerLit = c - '0';
			curLineTokens.add(t);
			//System.out.println(myNum);
		} else if (c == '*'){
			curLineTokens.add(new Token(asToken,curLineNum()));
		} else if (c == '='){
			if(line.charAt(pos++) == '='){
				curLineTokens.add(new Token(doubleEqualToken,curLineNum()));
			} else
			curLineTokens.add(new Token(equalToken,curLineNum()));
		} else if (c == '>'){
			if(line.charAt(pos++) == '='){
				curLineTokens.add(new Token(greaterEqualToken,curLineNum()));
			} else
			curLineTokens.add(new Token(greaterToken,curLineNum()));
		} else if (c == '<'){
			if(line.charAt(pos++) == '='){
				curLineTokens.add(new Token(lessEqualToken,curLineNum()));
			} else
			curLineTokens.add(new Token(lessToken,curLineNum()));
		} else if (c == '-'){
			curLineTokens.add(new Token(minusToken,curLineNum()));
		} else if (c == '%'){
			curLineTokens.add(new Token(percentToken,curLineNum()));
		} else if (c == '+'){
			curLineTokens.add(new Token(plusToken,curLineNum()));
		} else if (c == '/'){
			if(line.charAt(pos++) == '/'){
				curLineTokens.add(new Token(doubleSlashToken,curLineNum()));
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
		} else if (c == '!' && line.charAt(pos++) == '='){
			curLineTokens.add(new Token(notEqualToken,curLineNum()));
		}
	}
 
    
    curLineTokens.add(new Token(newLineToken,curLineNum()));
	
	//ArrayList<String> testlist = new ArrayList<>(Arrays.asList(line.split("")));
	//System.out.println(testlist);

	// - slutt på arbeidskode <-

	
	//-- Must be changed in part 1:

	// Terminate line:

	for (Token t: curLineTokens) 
	    Main.log.noteToken(t);
		System.out.println(curLineTokens);
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
	return null;
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
