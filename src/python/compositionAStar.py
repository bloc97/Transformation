import py_common_subseq
from random import randrange, uniform
import math

#Composition function using efficient A* Graph traversal
def composition(functionSet, solution):
	map = {} #Maps compositions of functions to if they are visited, used for graph traversal
	
	for func in functionSet: #Set all functions to 'not visited'
		map[func] = False
	
	currentFunctionList = list() #Keep a list for backtracking
	currentFunctionList.append(getIdentity(solution)) #Add the identity function as starting point of graph
	
	notFinished = True
	
	while (notFinished):
		while len(currentFunctionList) > 0: #While there are functions to backtrack
			unvisitedSet = set()
			n = 0
			#Graph building algorithm
			func1 = currentFunctionList[-1] #Get the last function
			for func2 in map: #Compute all the compositions, this is the branches of one point of the 'graph'
				
				if n > 50: 	#Use probabilistic decay when the composition set is very big
							#Sometimes it is better to search deeper than wider. Skipping some compositions actually accelerates the search
							#Using probabilistic decay will prevent the algorithm from finding the shortest path, but is quicker
					if uniform(0, 1) > 1/math.sqrt(n-40):
						continue
				
				fog = comp(func1, func2)
				gof = comp(func2, func1)
				
				if checkIsAlive(fog, solution): #Prune dead paths from graph
					if not map.get(fog, False): #Only If 'f o g' doesn't exist or was not visited
						unvisitedSet.add(fog)   #Add as a possible path
						
				if checkIsAlive(gof, solution): #Prune dead paths from graph
					if not map.get(gof, False): #Only If 'g o f' doesn't exist or was not visited
						unvisitedSet.add(gof)   #Add as a possible path
						
				n += 1
			
			for func in unvisitedSet: #Add them back to the map
				map[func] = False
				
			
			if len(unvisitedSet) == 0: #If there are no new and valid compositions, this is a dead end
				currentFunctionList = currentFunctionList[:-1] #Backtrack, Remove last element from list
			else:
				#A* graph traversal algorithm
				bestScore = -1
				bestFunction = None
				
				for thisFunction in unvisitedSet: #Find the best scoring function
					thisScore = score(thisFunction, solution)
					if thisScore > bestScore:
						bestFunction = thisFunction
						bestScore = thisScore
				
				if bestFunction == solution: #Found the solution!
					return True
				
				currentFunctionList.append(bestFunction) #Add to the list for backtracking
				map[bestFunction] = True #Set the function to 'visited'
				
		#One round of brute force composition to make sure we didn't miss anything while backtracking
		unvisitedFullSet = set()
		
		for func1 in map:
			for func2 in map:
				fog = comp(func1, func2)
				gof = comp(func2, func1)
				
				if checkIsAlive(fog, solution): #Prune dead paths from graph
					if not map.get(fog, False): #If 'f o g' doesn't exist or was not traversed
						unvisitedFullSet.add(fog)
						
				if checkIsAlive(gof, solution): #Prune dead paths from graph
					if not map.get(gof, False): #If 'g o f' doesn't exist or was not traversed
						unvisitedFullSet.add(gof)
		
		for func in unvisitedFullSet:
			map[func] = False
			
		if len(unvisitedFullSet) == 0:
			break
		
		
	return solution in map
	
	
	
#Checks if all numbers exist in 'f' and in 'solution',
#Returns false if 'solution' cannot be a composition of 'f'
#Improved algorithm for O(n) time instead of O(n^2)
def checkIsAlive(f, solution): 
	fB = [False] * len(f)
	sB = [False] * len(solution)
	
	for i, y in enumerate(f):
		fB[y - 1] = True
		
	for i, y in enumerate(solution):
		sB[y - 1] = True
		
	for i, b in enumerate(sB):
		if b and not fB[i]:
			return False
			
	return True
	
	
#Returns the identity function of same size as 'f'
def getIdentity(f):
	tempList = list()
	
	for i, j in enumerate(f):
		tempList.append(i + 1)
	
	return tuple(tempList)

#Returns the compsition of (f1 o f2)
def comp(f1, f2):
	tempList = list()
	
	for i, y in enumerate(f2):
		tempList.append(f1[y - 1])
	
	return tuple(tempList)
	
#The score used to search the graph is the count of subsequences between 'f' and 'solution'
def score(f, solution):
	return py_common_subseq.count_common_subsequences(f, solution)
	
	

f11 = tuple([3, 1, 3, 4]) # 1->3 2->1 3->3 4->4
f12 = tuple([3, 4, 2, 1]) # tuples car une liste ne peut pas
s1 = set([f11,f12])       # servir d'élément d'un ensemble
f1 = tuple([1, 1, 1, 2])

f21 = tuple([3, 5, 4, 2, 6, 8, 1, 7])
f22 = tuple([1, 4, 3, 2, 5, 6, 7, 8])
s2 = set([f21,f22])
f2 = tuple([8, 7, 6, 5, 4, 3, 2, 1])

f31 = tuple([3, 3, 4, 4, 5, 5])
f32 = tuple([3, 4, 2, 1, 6, 6])
f33 = tuple([4, 4, 4, 4, 5, 5])
s3 = set([f31,f32,f33])
f3 = tuple([2, 1, 4, 3, 5, 5])

f41 = tuple([1, 2, 4, 4, 5, 6, 7, 8, 9, 10, 11, 12])
f42 = tuple([2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1])
f43 = tuple([2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12])
s4 = set([f41,f42,f43])
f4 = tuple([2, 1, 4, 3, 5, 7, 7, 7, 7, 7, 7, 7])

f51 = tuple([3, 3, 5, 5, 7, 7, 1, 1, 11, 12, 12, 1])
f52 = tuple([3, 3, 1, 1, 5, 5, 7, 7, 7, 4, 12, 11])
f53 = tuple([1, 1, 3, 3, 5, 5, 9, 7, 9, 9, 10, 12])
s5 = set([f51,f52,f53])
f5 = tuple([7, 7, 3, 3, 1, 1, 5, 5, 12, 11, 10, 4])

f61 = tuple([3, 4, 4, 2, 6, 8, 1, 7, 3])
f62 = tuple([4, 4, 4, 4, 4, 5, 5, 8, 1])
s6 = set([f61,f62])
f6 = tuple([2, 1, 4, 4, 5, 6, 8, 7, 3])

print(composition(s1, f1))
print(composition(s2, f2))
print(composition(s3, f3))
print(composition(s4, f4))
print(composition(s5, f5))
print(composition(s6, f6))


