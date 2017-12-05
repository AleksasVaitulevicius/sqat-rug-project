module sqat::series1::Comments

import IO;
import String;
import List;

list[str] removeOneLineComments(list[str] lines){
	return [ line | str line <- lines, !startsWith(line, "//")];
}

list[str] removeMultiLineComments([]) = [];
list[str] removeMultiLineComments([str line, *str lines]){
	if(contains(line, "/*")){
		if(!startsWith(line, "/*"))
			return line + removeMultiLineComment(line + lines);
		return removeMultiLineComment(line + lines);
	}
	return line + removeMultiLineComments(lines);
}

list[str] removeMultiLineComment([]) = [];
list[str] removeMultiLineComment([str line, *str lines]){
	if(contains(line, "*/")){
		if(!endsWith(line, "*/"))
			return substring(line, findFirst(line, "*/") + 2) + removeMultiLineComments(lines);
		return removeMultiLineComments(lines);
	}
	return removeMultiLineComment(lines);
}

test bool testremoveMultiLineComments() 
	= removeMultiLineComments([
		"//one line comment", 
		"line1", 
		"line2/*Multiline comment line 1", 
		"Multiline comment line 2", 
		"Multiline comment line 3", 
		"*/line3", 
		"line4"]) 
		== ["//one line comment", "line1", "line2/*Multiline comment line 1", "line3", "line4"];