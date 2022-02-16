#
# Makefile for java programming by Tsai-Yen Li 03/12/2021
#

# SHELL = tcsh
JFLAGS = --module-path "C:\Program Files\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml #-debug
SOURCE = ALife.java
OBJECT = ALife.class
JAVAC = javac
JAVA = java
JAVADOC = javadoc
HW = 4
PACKAGE = assign$(HW)
MAIN = gobang
PARAMETERS = 
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
	$(JAVA) $(JFLAGS) -classpath $(C_DIR) $(PACKAGE).$(MAIN) $(PARAMETERS)

submit:
	$(SUBMIT) $(HW)

clean:
	rm ./$(C_DIR)/$(PACKAGE)/*.class *~

