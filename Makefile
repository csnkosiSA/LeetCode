
.SUFFIXES: .java .class
JAVAC = /usr/bin/javac
JAVA = /usr/bin/java

BINDIR = bin
SRCDIR = src



$(BINDIR)/%.class: $(SRCDIR)/%.java
	
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<
	
classes:bin/MedianFilterSerial.class\
		bin/MeanFilterSerial.class\


default: $(CLASSES)



run: $(CLASSES)
	 $(JAVA) -cp $(BINDIR) MedianFilterSerial 
run_2: $(CLASSES)
	 $(JAVA) -cp $(BINDIR) MeanFilterSerial 
	
run_javadoc:
	javadoc -d javadoc src/*.java	
clean: 
	rm $(BINDIR)/*class  

clean_images:
	rm *.jpg
	



