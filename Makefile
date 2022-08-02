
.SUFFIXES: .java .class
JAVAC = /usr/bin/javac
JAVA = /usr/bin/java

BINDIR = bin
SRCDIR = src



$(BINDIR)/%.class: $(SRCDIR)/%.java
	
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<
	
classes:bin/MeanFilterSerial.class\


default: $(CLASSES)



run: $(CLASSES)
	 $(JAVA) -cp $(BINDIR) MeanFilterSerial
	
run_javadoc:
	javadoc -d javadoc src/*.java	
clean: 
	rm $(BINDIR)/*class  
	



