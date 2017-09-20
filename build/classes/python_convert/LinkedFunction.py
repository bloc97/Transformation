# 
# * To change this license header, choose License Headers in Project Properties.
# * To change this template file, choose Tools | Templates
# * and open the template in the editor.
# 
from System import *
from System.Collections.Generic import *
from System.Collections import *
# *
# *
# * @author bowen
# 
class LinkedFunction(object):
	def __init__(self, size):
		self._compositionHistory = LinkedList()
		self._isRoot = True
		self._isIdentity = True
		mapping = LinkedList()
		i = 0
		while i < size:
			mapping.addLast(i + 1)
			i += 1
		self._currentAnswer = Function(mapping)

	def __init__(self, size):
		self._compositionHistory = LinkedList()
		self._isRoot = True
		self._isIdentity = True
		mapping = LinkedList()
		i = 0
		while i < size:
			mapping.addLast(i + 1)
			i += 1
		self._currentAnswer = Function(mapping)

	def __init__(self, size):
		self._compositionHistory = LinkedList()
		self._isRoot = True
		self._isIdentity = True
		mapping = LinkedList()
		i = 0
		while i < size:
			mapping.addLast(i + 1)
			i += 1
		self._currentAnswer = Function(mapping)

	def isRoot(self):
		return self._isRoot

	def getCurrentAnswer(self):
		return self._currentAnswer

	def getComposition(self, f2):
		return LinkedFunction(self, f2)

	def getCompositionReverse(self, f2):
		return LinkedFunction(f2, self)

	def equals(self, o):
		if o:
			instanceof
		return f.currentAnswer.getMapping().equals(self._currentAnswer.getMapping())