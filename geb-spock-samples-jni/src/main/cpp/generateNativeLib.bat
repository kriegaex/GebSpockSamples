REM Create the header with javac -h . ClassName.java
REM Remember to set your JAVA_HOME env var
REM For MSYS2, remember to install mingw-64 and put it on the path:
REM   pacman -S base-devel mingw-w64-x86_64-toolchain
REM   path c:\msys64\mingw64\bin;%PATH%
g++ -c -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" com_baeldung_jni_HelloWorldJNI.cpp -o com_baeldung_jni_HelloWorldJNI.o
g++ -c -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" com_baeldung_jni_ExampleParametersJNI.cpp -o com_baeldung_jni_ExampleParametersJNI.o
g++ -c -I"%JAVA_HOME%\include" -I"%JAVA_HOME%\include\win32" com_baeldung_jni_ExampleObjectsJNI.cpp -o com_baeldung_jni_ExampleObjectsJNI.o
REM g++ -shared -o ..\..\..\native\win32\native.dll com_baeldung_jni_HelloWorldJNI.o com_baeldung_jni_ExampleParametersJNI.o com_baeldung_jni_ExampleObjectsJNI.o -Wl,--add-stdcall-alias
g++ -shared -o ../../../native.dll com_baeldung_jni_HelloWorldJNI.o com_baeldung_jni_ExampleParametersJNI.o com_baeldung_jni_ExampleObjectsJNI.o -Wl,--add-stdcall-alias
