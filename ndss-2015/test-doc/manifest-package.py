#!/usr/bin/python
import os
import sys
import optparse
import re

__version__ = "0.2"
USAGE = "%prog [options] <url>"
VERSION = "%prog v" + __version__


def parse_options():
	"""parse_options() -> opts, args

	Parse any command-line options given returning both
	the parsed options and arguments.
	"""

	parser = optparse.OptionParser(usage=USAGE, version=VERSION)

	opts, args = parser.parse_args()

	if len(args) < 2:
		print "Syntax: {} {} {} ".format(sys.argv[0], "<manifest>", "<package>")
		raise SystemExit, 1

	return opts, args

def setPackage(fileName, packageName):
	lines = []
	f = open(fileName, "r")
	for line in f:
		m = re.match(r'^(\s+package)=(.*)$', line)
		if (m):
			print "*** " + m.group(2)
			line = m.group(1) + '="' + packageName  + '"\n'
		lines.append(line)
	f.close()	

	f = open(fileName, "w")
	for line in lines:
		f.write(line)
		#print line
	f.close()

def main():
	print "Hello World "
	print sys.argv
	opts, args = parse_options()

	setPackage(args[0], args[1])


#########################################################################
#                                   Entry  
#########################################################################
if __name__ == "__main__":
	main()

