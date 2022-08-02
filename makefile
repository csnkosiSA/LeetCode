.SUFFIXES: .java .class
JAVAC = /usr/bin/javac
JAVA = /usr/bin/java

BINDIR = bin
SRCDIR = src



$(BINDIR)/%.class: $(SRCDIR)/%.java
        
        $(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<
        
classes: bin/VMeanFilterSerial.class

default: $(CLASSES)

