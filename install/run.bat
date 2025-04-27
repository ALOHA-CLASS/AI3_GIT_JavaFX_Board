@echo off
set FX_LIB=lib\javafx-sdk-23.0.2\lib

java --module-path "%FX_LIB%" ^
     --add-modules javafx.controls,javafx.fxml ^
     -jar App.jar

pause
