@echo off
set DOCLET=com.tarsec.javadoc.pdfdoclet.PDFDoclet
set JARS=jar/pdfdoclet-1.0.2-all.jar
set PACKAGES="crimsonportal.googlecode.com"
set PDF=crimsonportal.pdf
set CFG=confirg_crimsonportal_pdf.properties
set SRC=../src
javadoc -doclet %DOCLET% -docletpath %JARS% -pdf %PDF% -config %CFG% -sourcepath %SRC% %PACKAGES%
