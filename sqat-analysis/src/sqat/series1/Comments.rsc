module sqat::series1::Comments

import IO;
import String;

list[str] removeOneLineComments(list[str] lines){
	return [ line | str line <- lines, !startsWith(line, "//")];
}

list[str] removeComments(list[str] lines){
	list[str] result = [];
	bool multilineComment = false;
	
	for(line <- lines){
		if(multilineComment){
			if(contains(line, "*/")){
				multilineComment = false;
				if(endsWith(line, "*/"))
					continue;
				line = substring(line, findFirst(line, "*/") + 2, size(line));
			}
			else {
				continue;
			}
		}
		if(contains(line, "/*")){
			if(substring(line, 0, 2) != "/*")
				result = result + line;
			if(!contains(line, "*/"))
				multilineComment = true;
			continue;
		}
		result = result + line;
	}
	return result;
}

test bool testremoveComments() 
	= removeComments(["//asd", "as", "/*asd", "qwe1", "qwe2", "*/bs1", "bs"]) 
		== ["//asd", "as", "bs1", "bs"];