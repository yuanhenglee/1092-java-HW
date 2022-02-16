#
# Makefile for java programming by Tsai-Yen Li 03/12/2021
#

SHELL = tcsh
JFLAGS = -g #-debug
SOURCE = ALife.java
OBJECT = ALife.class
JAVAC = javac
JAVA = java
JAVADOC = javadoc
PACKAGE = assign3
MAIN = ALife
PARAMETERS = 1001 1000 1 
S_DIR = src
C_DIR = bin
D_DIR = doc
SUBMIT = /usr/local/class/javap/bin/jsubmit

all: #./$(C_DIR)/$(PACKAGE)/$(OBJECT)
	$(JAVAC) $(JFLAGS) -d $(C_DIR) $(S_DIR)/$(PACKAGE)/*.java

.java.class:
	$(JAVAC) $(JFLAGS) -d $<

doc:
	$(JAVADOC) -sourcepath $(S_DIR) -d $(D_DIR) $(PACKAGE)

run:
	$(JAVA) -classpath $(C_DIR) $(PACKAGE)/$(MAIN) $(PARAMETERS)

submit:
	$(SUBMIT) 3

clean:
	\rm -f $(C_DIR)/$(PACKAGE)/*.class *~

