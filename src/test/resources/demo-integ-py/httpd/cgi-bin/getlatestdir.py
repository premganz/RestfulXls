#!C:/Python27-32/Python.exe
# -*- coding: UTF-8 -*-

# enable debugging
import cgitb
cgitb.enable()
import sys
sys.path.insert(0,'C:\Python\AutoTest_Desktop_1')
sys.path.insert(0,'C:\Python\pywinauto-0.4.2')
sys.path.insert(0,'C:/Python27-32/Lib')
import Tester
from Tester import PywinController
var=PywinController.function_findLatestDirectory()
print "Content-Type: application/json"
print
print '{"header":"PywinController.function_findLatestDirectory","payload":"'+var+'"}'