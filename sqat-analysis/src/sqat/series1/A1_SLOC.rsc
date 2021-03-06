module sqat::series1::A1_SLOC

import IO;
import ParseTree;
import String;
import util::FileSystem;
import List;
import sqat::series1::Comments;

/* 

Count Source Lines of Code (SLOC) per file:
- ignore comments
- ignore empty lines

Tips
- use locations with the project scheme: e.g. |project:///jpacman/...|
- functions to crawl directories can be found in util::FileSystem
- use the functions in IO to read source files

Answer the following questions:
- what is the biggest file in JPacman?
- what is the total size of JPacman?
- is JPacman large according to SIG maintainability?
- what is the ratio between actual code and test code size?

Sanity checks:
- write tests to ensure you are correctly skipping multi-line comments
- and to ensure that consecutive newlines are counted as one.
- compare you results to external tools sloc and/or cloc.pl

Bonus:
- write a hierarchical tree map visualization using vis::Figure and 
  vis::Render quickly see where the large files are. 
  (https://en.wikipedia.org/wiki/Treemapping) 

*/

alias SLOC = map[loc file, int sloc];

loc project() = |project://jpacman-framework|;

SLOC sloc(loc project, int threshold = 66000) {
  	SRC = iterateSloc(project + "src" + "main");
  	TEST = iterateSloc(project + "src" + "test");
  	
  	projectSize = SRC.size + TEST.size;
  	ratio = SRC.size / TEST.size;
  	
  	if(SRC.biggestFileSize > TEST.biggestFileSize)
  		printAnswersOfAssignmentQuestions(projectSize, ratio, SRC.biggestFile, SRC.biggestFileSize, threshold);
	else
  		printAnswersOfAssignmentQuestions(projectSize, ratio, TEST.biggestFile, TEST.biggestFileSize, threshold);
  	
	return SRC.result + TEST.result;
}             

void printAnswersOfAssignmentQuestions(int projectSize, int ratio, loc biggestFile, int biggestFileSize, int threshold){
	println("Size of project=<projectSize> lines");
	println("Biggest file=<biggestFile>");
	println("Size of biggest file=<biggestFileSize> lines");
	println("Ratio between src code and test code=<ratio>:1");
	if(projectSize < threshold)
		println("JPacman-framework is not large according to SIG maintainability.");
	else
		println("JPacman-framework is large according to SIG maintainability.");
}

tuple[SLOC result, loc biggestFile, int biggestFileSize, int size] iterateSloc(loc project){
  	SLOC result = ();
  	int sizeOfProject = 0;
  	int biggestFileSize = 0;
  	loc biggestFile = |project://s|;
  	
	for (f <- files(project), f.extension == "java") {
		lines = readFileLines(f);
		lines = removeWhiteSpaces(lines);
		lines = removeMultiLineComments(lines);
		lines = removeOneLineComments(lines);
		int amountOfLines = size(lines);
		if(biggestFileSize < amountOfLines){
			biggestFileSize = amountOfLines;
			biggestFile = f;
		}
		result[f] = amountOfLines;
		sizeOfProject = sizeOfProject + amountOfLines;
	}
	
	return <result, biggestFile, biggestFileSize, sizeOfProject>;
}

list[str] removeWhiteSpaces(list[str] lines){
	lines = [ replaceAll(replaceAll(line, "\t", ""), " ", "") | str line <- lines];
	lines = [ line | str line <- lines, line != ""];
	return lines;
}

test bool testremoveWhiteSpaces() = removeWhiteSpaces(["a", "", "		", "b", " "]) == ["a", "b"];