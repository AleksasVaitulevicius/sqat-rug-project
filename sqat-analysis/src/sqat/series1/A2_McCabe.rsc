module sqat::series1::A2_McCabe

import lang::java::jdt::m3::AST;
import lang::java::m3::AST;
import IO;
import util::ResourceMarkers;

/*

Construct a distribution of method cylcomatic complexity. 
(that is: a map[int, int] where the key is the McCabe complexity, and the value the frequency it occurs)


Questions:
- which method has the highest complexity (use the @src annotation to get a method's location)

- how does pacman fare w.r.t. the SIG maintainability McCabe thresholds?

- is code size correlated with McCabe in this case (use functions in analysis::statistics::Correlation to find out)? 
  (Background: Davy Landman, Alexander Serebrenik, Eric Bouwers and Jurgen J. Vinju. Empirical analysis 
  of the relationship between CC and SLOC in a large corpus of Java methods 
  and C functions Journal of Software: Evolution and Process. 2016. 
  http://homepages.cwi.nl/~jurgenv/papers/JSEP-2015.pdf)
  
- what if you separate out the test sources?

Tips: 
- the AST data type can be found in module lang::java::m3::AST
- use visit to quickly find methods in Declaration ASTs
- compute McCabe by matching on AST nodes

Sanity checks
- write tests to check your implementation of McCabe

Bonus
- write visualization using vis::Figure and vis::Render to render a histogram.

*/
set[Declaration] jpacmanASTs() = createAstsFromEclipseProject(|project://jpacman-framework|, true); 

alias CC = rel[loc method, int cc];

CC cc(set[Declaration] decls) {
	CC result = {};
	
	visit(decls){
		case m:\method(_, _, _, _, Statement impl): {
			result = result + {<m.src, mcCabe(impl)>};
			}
		case m:\constructor(_, _, _, _, Statement impl):
			result = result + {<m.src, mcCabe(impl)>};
	}
	
	return result;
}

alias CCDist = map[int cc, int freq];

CCDist ccDist(CC cc) {
	complexities = cc.cc;
	transformIntoDist();
	return {};
}

//CCDist transformIntoDist(set[int]){
//
//}

int mcCabe(Statement impl) {
	int result = 1;
	visit(impl){
		case \if(Expression condition, Statement thenBranch, Statement elseBranch): 
			result = result + countEdgesOfNode(condition);
		case \if(Expression condition, Statement thenBranch):
			result = result + countEdgesOfNode(condition);
		case \while(Expression condition, Statement body):
			result = result + countEdgesOfNode(condition);
		case \do(Statement body, Expression condition):
			result = result + countEdgesOfNode(condition);
		case \for(_, Expression condition, _, Statement body):{
			result = result + countEdgesOfNode(condition);
			println("DEBUG <result>");
		}
		case \foreach(_, _, Statement body): result = result + mcCabe(body);
		case \for(_, _, Statement body): result = result + mcCabe(body);
	}
	return result;
}

int countEdgesOfNode(Expression condition){
	int result = 1;
	
	visit(condition){
		case infix(_, "&&", _): result = result + 1;
		case infix(_, "||", _): result = result + 1;
	}
	
	return result;
}

test bool testcc()
	= cc(createAstsFromEclipseProject(|project://sqat-analysis|, true))
		== {<|project://sqat-analysis/src/sqat/series1/McCabeTest.java|(53,84,<4,1>,<9,2>), 3>};
