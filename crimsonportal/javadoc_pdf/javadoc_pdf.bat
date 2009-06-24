@echo off
set DOCLET=com.tarsec.javadoc.pdfdoclet.PDFDoclet
set JARS=jar/pdfdoclet-1.0.2-all.jar
set PDF=../docs/crimsonportal.pdf
set CFG=config_crimsonportal_pdf.properties
set SRC=../src
set PACKAGES="crimsonportal.googlecode.com"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.Controller"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.Factories"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.GameSettings"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.ObjectModel"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.ObjectModel.EnemyUnit"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.ObjectModel.Pickups"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.Observer"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.Observer.GameState"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.Observer.KeyPress"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.Observer.Player"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.Observer.Player.Move"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.Observer.Player.Shoot"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.Observer.Player.Turn"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.Proxy"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.gui"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.gui.Animations"
set PACKAGES=%PACKAGES% "crimsonportal.googlecode.com.terrain"
javadoc -doclet %DOCLET% -docletpath %JARS% -pdf %PDF% -config %CFG% -sourcepath %SRC% %PACKAGES%


